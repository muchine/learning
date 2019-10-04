package com.muchine.chapter2_8.db.drug;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.muchine.chapter2_8.db.gen.FileRecordCreator;
import com.muchine.chapter2_8.db.logger.DatabaseLoggable;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugDetailGenerator {

    private static final String SOURCE = "/sdcard/details.dat";
    private static final String TABLE_NAME = "DETAIL";

    private final SQLiteDatabase db;
    private final DatabaseLoggable logger;

    public DrugDetailGenerator(SQLiteDatabase db, DatabaseLoggable logger) {
        this.db = db;
        this.logger = logger;
    }
    
    public void generate() {
        createTable();
        insertRecords();
        queryRecords();
    }

    private void createTable() {
        logger.print("creating detail table [" + TABLE_NAME + "]");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE " + TABLE_NAME + " (");
        builder.append("DRUGCODE text, CLASSCODE text, CLASSNAME text, DETAILS text");
        builder.append(")");

        db.execSQL(builder.toString());
    }

    private void insertRecords() {
        String statement = "INSERT INTO " + TABLE_NAME + " (DRUGCODE, CLASSCODE, CLASSNAME, DETAILS) VALUES (?, ?, ?, ?)";

        FileRecordCreator.Context context = new FileRecordCreator.Context(statement, 4, SOURCE);
        FileRecordCreator creator = new FileRecordCreator(db, context, logger);

        creator.insert();
    }

    private void queryRecords() {
        logger.print("querying detail table for 'ACAR'...");

        String statement = "SELECT DRUGCODE, CLASSNAME, DETAILS FROM " + TABLE_NAME + " WHERE DRUGCODE = ?";
        String[] args = new String[]{"ACAR"};

        Cursor cursor = db.rawQuery(statement, args);

        int recordCount = cursor.getCount();
        logger.print("cursor count: " + recordCount);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            String className = cursor.getString(1);
            String details = cursor.getString(2);

            logger.print("#" + i + " " + className + ": " + details);
        }

        cursor.close();
    }
    
}
