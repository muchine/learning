package com.muchine.chapter2_8.db.gen;

import android.database.sqlite.SQLiteDatabase;

import com.muchine.chapter2_8.db.logger.DatabaseLoggable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class FileRecordCreator {

    private final SQLiteDatabase db;

    private final DatabaseLoggable logger;

    private final Context context;

    public FileRecordCreator(SQLiteDatabase db, Context context, DatabaseLoggable logger) {
        this.db = db;
        this.context = context;
        this.logger = logger;
    }

    public void insert() {
        try {
            File source = new File(context.source);
            FileInputStream in = new FileInputStream(source);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            int count = 0;
            int recordCount = 0;

            while ((line = reader.readLine()) != null) {
                logger.print("processing line #" + count++);

                boolean isInserted = insertRecord(context.statement, line);
                if (isInserted) recordCount++;
            }

            reader.close();

            logger.print("Done reading master.dat -> " + count + " lines, " + recordCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean insertRecord(String statement, String line) {
        String[] tokens = line.split("\\|");
        if (tokens == null || tokens.length != context.columnCount) {
            logger.print("the input line is invalid.");
            return false;
        }

        logger.print("length of tokens: " + tokens.length);
        db.execSQL(statement, tokens);

        return true;
    }

    public static final class Context {

        private final String statement;

        private final int columnCount;

        private final String source;

        public Context(String statement, int columnCount, String source) {
            this.statement = statement;
            this.columnCount = columnCount;
            this.source = source;
        }
    }

}
