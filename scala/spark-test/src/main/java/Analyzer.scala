import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.util.Sorting

/**
  * Created by sejoonlim on 03/01/2017.
  */

object ExecutionMode extends Enumeration {
  type ExecutionMode = Value
  val LOCAL, PRODUCTION = Value
}

import ExecutionMode._

class Analyzer(_executionMode: ExecutionMode = ExecutionMode.LOCAL, _inputPath: String, _outputPath: String) {

  private val executionMode: ExecutionMode = _executionMode
  private val inputPath: String = _inputPath
  private val outputPath: String = _outputPath

  def analyze(args: Array[String]): Unit = {
    val logFile = "hdfs://sejoon.rememberapp.co.kr:9000/input"
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)

    val logData = sc.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"Lines with a: $numAs, Lines with b: $numBs")
    sc.stop()
  }

  def analyzeDuoLog(): Unit = {
    val input = Array(
      """ {"type":"ROOT", "file":"/usr/local/rvm/gems/ruby-2.3.0/gems/redis-3.3.0/lib/redis.rb", "line":52, "root":"vm", "references":["0x15222a8", "0x5d3a428", "0x5a03bb0", "0x5a039f8", "0x5a03610", "0x647b318", "0x60ea6b8", "0x15202c8", "0x1553f88", "0x15299e0", "0x1529990", "0x64704b8", "0x15298c8", "0x15298a0", "0x154e650", "0x1553fb0", "0x63d2768", "0x63d2790", "0x5a3d2c0", "0x5a3d1d0", "0x5a3d158", "0x5a3d248", "0x5a3d0e0"]} """,
      """ {"type":"ROOT", "file":"/usr/local/rvm/gems/ruby-2.3.0/gems/redis-3.3.0/lib/redis.rb", "line":52, "root":"machine_context", "references":["0x5a3d0e0", "0x1a3ad80", "0x5a3d0e0", "0x5a3d0e0", "0x1a3ad80", "0x63d2d58", "0x15261c8", "0x1a3ad80", "0x1a3b7f8", "0x1a3b7f8", "0x16a4b30", "0x63d2d58", "0x1526128", "0x63d2d58", "0x1526128", "0x15261c8", "0x1e89260", "0x60ea6e0", "0x154e650", "0x15261c8", "0x154e650", "0x154e650", "0x60eacd0", "0x154e650", "0x60eacd0", "0x154e650", "0x154e650", "0x60ea618"]} """
    )

    val conf = new SparkConf().setAppName("Duo Log Analyzer")
    if (isLocal) conf.setMaster("local[1]")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val json = if (isLocal)
      sc.parallelize(input)
    else
      sc.textFile(inputPath)

    val df: DataFrame = sqlContext.read.json(json)
    df.write.parquet("log-parquet")

    val sql = "select file, line, count(*) as cnt from logs group by file, line order by cnt desc"
//    val sql = "select * from logs"
//    val result = sqlContext.sql(sql)
//
//    result.show()
//    result.rdd
//      .coalesce(1, true).map(_.mkString(" | "))
//      .saveAsTextFile(outputPath)

    sc.stop()
  }

  def isLocal: Boolean = {
    executionMode == ExecutionMode.LOCAL
  }

}

object AnalyzerRunner {
  def main(args: Array[String]): Unit = {

    /*
      args(0) : Execution Mode
      args(1) : input filepath
      args(2) : output filepath
     */

    val executionMode = if (args.isEmpty || args(0).toUpperCase == "LOCAL")
      ExecutionMode.LOCAL
    else
      ExecutionMode.PRODUCTION

    val inputPath = if (args.isEmpty) "" else args(1)
    val outputPath = if (args.isEmpty) "log-result" else args(2)
    val analyzer = new Analyzer(executionMode, inputPath, outputPath)

    analyzer.analyzeDuoLog()
  }
}
