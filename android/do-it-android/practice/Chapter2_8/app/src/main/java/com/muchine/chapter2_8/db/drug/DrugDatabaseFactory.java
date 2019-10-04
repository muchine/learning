package com.muchine.chapter2_8.db.drug;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugDatabaseFactory {

    private static final String DATABASE_FILE = "/sdcard/druginfo.db";

    public static SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(DATABASE_FILE, null,
                SQLiteDatabase.OPEN_READWRITE + SQLiteDatabase.CREATE_IF_NECESSARY);
    }

}
