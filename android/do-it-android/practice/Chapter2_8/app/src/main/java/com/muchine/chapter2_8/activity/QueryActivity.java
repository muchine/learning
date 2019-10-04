package com.muchine.chapter2_8.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.db.DatabaseHelper;
import com.muchine.chapter2_8.db.DatabaseManifest;
import com.muchine.chapter2_8.db.logger.DatabaseLoggable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QueryActivity extends AppCompatActivity {

    private static final String TAG = "QueryActivity";

    private static final String TABLE_NAME = "employees";
    private static final int DATABASE_VERSION = 1;

    @Bind(R.id.queryInput)
    EditText databaseNameInput;

    @Bind(R.id.queryStatus)
    TextView status;

    private DatabaseLoggable logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);

        this.logger = newDatabaseLogger();

        initQueryButton();
    }

    private void initQueryButton() {
        Button button = (Button) findViewById(R.id.queryButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = databaseNameInput.getText().toString();
                SQLiteDatabase db = openDatabase(databaseName);

                queryCount(db);
                queryRecords(db);
                queryRecordsWithMethod(db);
            }
        });
    }

    private SQLiteDatabase openDatabase(String databaseName) {
        DatabaseManifest manifest = new DatabaseManifest(databaseName, TABLE_NAME, DATABASE_VERSION);
        manifest.setLogger(logger);

        DatabaseHelper dbHelper = new DatabaseHelper(this, manifest);
        return dbHelper.getWritableDatabase();
    }

    private void queryCount(SQLiteDatabase db) {
        logger.print("\nqueryCount called.");

        Cursor cursor = db.rawQuery("SELECT COUNT(*) AS TOTAL FROM " + TABLE_NAME, null);
        logger.print("cursor count: " + cursor.getCount());

        cursor.moveToNext();
        logger.print("record count: " + cursor.getInt(0));

        cursor.close();
    }

    private void queryRecords(SQLiteDatabase db) {
        logger.print("\nqueryRecords called.");

        String sql = "SELECT NAME, AGE, PHONE FROM " + TABLE_NAME + " WHERE AGE > ?";
        String[] args = new String[] {"30"};

        Cursor cursor = db.rawQuery(sql, args);
        printRecords(cursor);

        cursor.close();
    }

    private void queryRecordsWithMethod(SQLiteDatabase db) {
        logger.print("\nqueryRecordsWithMethod called.");

        String[] columns = new String[]{"name", "age", "phone"};
        String where = "age > ?";
        String[] whereArgs = new String[]{"30"};

        Cursor cursor = db.query(TABLE_NAME, columns, where, whereArgs, null, null, null);
        printRecords(cursor);

        cursor.close();
    }

    private void printRecords(Cursor cursor) {
        int recordCount = cursor.getCount();
        logger.print("cursor count: " + recordCount);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            String name = cursor.getString(0);
            int age = cursor.getInt(1);
            String phone = cursor.getString(2);

            logger.print("Record #" + i + " : " + name + ", " + age + ", " + phone);
        }
    }

    private DatabaseLoggable newDatabaseLogger() {
        return new DatabaseLoggable() {
            @Override
            public void print(String message) {
                Log.d(TAG, message);
                status.append("\n" + message);
            }
        };
    }

}
