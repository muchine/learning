package com.muchine.chapter2_8.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.muchine.chapter2_8.db.logger.DatabaseLoggable;
import com.muchine.chapter2_8.model.Employee;

/**
 * Created by sejoonlim on 9/13/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private final DatabaseManifest manifest;
    private final DatabaseLoggable logger;

    public DatabaseHelper(Context context, DatabaseManifest manifest) {
        super(context, manifest.getDatabaseName(), null, manifest.getVersion());
        this.manifest = manifest;
        this.logger = manifest.getLogger();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        logger.print("creating table [" + manifest.getTableName() + "].");

        dropTable(db);
        createTable(db);
        insertRecords(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(TAG, "Upgrading database from version" + i + " to " + i1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        logger.print("opened database [" + manifest.getDatabaseName() + "].");
    }

    private void createTable(SQLiteDatabase db) {
        try {
            db.execSQL(newTableCreationQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dropTable(SQLiteDatabase db) {
        try {
            String sql = "DROP TABLE IF EXISTS " + manifest.getTableName();
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertRecords(SQLiteDatabase db) {
        new Employee("John", 20, "010-1234-2345").save(db, manifest.getTableName());
        new Employee("Sejoon", 37, "010-1234-2346").save(db, manifest.getTableName());
        new Employee("Hyewon", 35, "010-1234-2347").save(db, manifest.getTableName());
    }

    private String newTableCreationQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE " + manifest.getTableName() + " (");
        builder.append("_id integer PRIMARY KEY AUTOINCREMENT,");
        builder.append("name text,");
        builder.append("age integer,");
        builder.append("phone text);");

        return builder.toString();
    }

}
