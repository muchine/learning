package com.muchine.chapter2_8.db.drug;

import android.database.sqlite.SQLiteDatabase;

import com.muchine.chapter2_8.db.logger.DatabaseLoggable;
import com.muchine.chapter2_8.db.logger.NullLogger;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugDatabaseGenerator {

    private static final String DATABASE_FILE = "/sdcard/druginfo.db";

    private final DatabaseLoggable logger;

    public DrugDatabaseGenerator(DatabaseLoggable logger) {
        this.logger = logger;
    }

    public void generate() {
        SQLiteDatabase db = openDatabase();

        generateMaster(db);
        generateDetail(db);
    }

    private SQLiteDatabase openDatabase() {
        logger.print("creating or opening database [" + DATABASE_FILE + "].");
        return DrugDatabaseFactory.openDatabase();
    }

    private void generateMaster(SQLiteDatabase db) {
        DrugMasterGenerator generator = new DrugMasterGenerator(db, NullLogger.getInstance());
        generator.generate();
    }

    private void generateDetail(SQLiteDatabase db) {
        DrugDetailGenerator generator = new DrugDetailGenerator(db, logger);
        generator.generate();
    }

}
