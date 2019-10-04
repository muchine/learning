package com.muchine.chapter2_8.db.drug;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.muchine.chapter2_8.db.gen.FileRecordCreator;
import com.muchine.chapter2_8.db.logger.DatabaseLoggable;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugMasterGenerator {

    private static final String SOURCE = "/sdcard/master.dat";
    private static final String TABLE_NAME = "MASTER";

    private final SQLiteDatabase db;

    private final DatabaseLoggable logger;

    public DrugMasterGenerator(SQLiteDatabase db, DatabaseLoggable logger) {
        this.db = db;
        this.logger = logger;
    }

    public void generate() {
        createTable();
        insertRecords();
        queryRecords();
    }

    private void createTable() {
        logger.print("creating master table [" + TABLE_NAME + "]");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE " + TABLE_NAME + " (");
        builder.append("DRUGCODE text, DRUGNAME text, PRODENNM text, PRODKRNM text, PHRMNAME text, DISTRNAME text, REPDGID text, REPDGNAME text");
        builder.append(")");

        db.execSQL(builder.toString());
    }

    private void insertRecords() {
        String statement = "INSERT INTO " + TABLE_NAME + " (DRUGCODE, DRUGNAME, PRODENNM, PRODKRNM, PHRMNAME, DISTRNAME, REPDGID, REPDGNAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        FileRecordCreator.Context context = new FileRecordCreator.Context(statement, 8, SOURCE);
        FileRecordCreator creator = new FileRecordCreator(db, context, logger);

        creator.insert();
    }

    private void queryRecords() {
        logger.print("querying master table for 'Aspirin%'...");

        String statement = "SELECT DRUGCODE, DRUGNAME, PRODKRNM FROM " + TABLE_NAME + " WHERE DRUGNAME LIKE ?";
        String[] args = new String[]{"Aspirin%"};

        Cursor cursor = db.rawQuery(statement, args);

        int recordCount = cursor.getCount();
        logger.print("cursor count: " + recordCount);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            String productName = cursor.getString(1);
            logger.print("#" + i + " 제품명: " + productName);
        }

        cursor.close();
    }

}
