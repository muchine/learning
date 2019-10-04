import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.util.Sorting

/**
  * Created by sejoonlim on 03/01/2017.
  */

class Analyzer {

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

  def analyzeDuoLog(filepath : String): Unit = {
    val input = Array(
      """ {"type":"ROOT", "file":"/usr/local/rvm/gems/ruby-2.3.0/gems/redis-3.3.0/lib/redis.rb", "line":52, "root":"vm", "references":["0x15222a8", "0x5d3a428", "0x5a03bb0", "0x5a039f8", "0x5a03610", "0x647b318", "0x60ea6b8", "0x15202c8", "0x1553f88", "0x15299e0", "0x1529990", "0x64704b8", "0x15298c8", "0x15298a0", "0x154e650", "0x1553fb0", "0x63d2768", "0x63d2790", "0x5a3d2c0", "0x5a3d1d0", "0x5a3d158", "0x5a3d248", "0x5a3d0e0"]} """,
      """ {"type":"ROOT", "file":"/usr/local/rvm/gems/ruby-2.3.0/gems/redis-3.3.0/lib/redis.rb", "line":52, "root":"machine_context", "references":["0x5a3d0e0", "0x1a3ad80", "0x5a3d0e0", "0x5a3d0e0", "0x1a3ad80", "0x63d2d58", "0x15261c8", "0x1a3ad80", "0x1a3b7f8", "0x1a3b7f8", "0x16a4b30", "0x63d2d58", "0x1526128", "0x63d2d58", "0x1526128", "0x15261c8", "0x1e89260", "0x60ea6e0", "0x154e650", "0x15261c8", "0x154e650", "0x154e650", "0x60eacd0", "0x154e650", "0x60eacd0", "0x154e650", "0x154e650", "0x60ea618"]} """
    )

    val conf = new SparkConf().setAppName("Duo Log Analyzer")
    conf.setMaster("local[1]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val json = sc.parallelize(input)
//    val json = sc.textFile(filepath).cache()

    val df = sqlContext.read.json(json)
    df.registerTempTable("logs")
    val result = sqlContext.sql("select file, count(*) as cnt from logs group by file")

    result.show()
//    val result = data.map()

//    val result = data.map(parse(_)
//      .extract[Map[String, Any]])
//      .groupBy(d => d("file") + ":" + d("line"))
//
//    println(s"map: ${result.toString()}")

    sc.stop()
  }

}

object SimpleApp {
  def main(args: Array[String]): Unit = {
    val analyzer = new Analyzer()
//    analyzer.analyzeDuoLog(args(0))
    analyzer.analyzeDuoLog("aaaa")
  }
}
