package com.muchine.chapter2_8.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.db.DatabaseHelper;
import com.muchine.chapter2_8.db.DatabaseManifest;
import com.muchine.chapter2_8.db.logger.DatabaseLoggable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CursorActivity extends AppCompatActivity {

    private static final String TAG = "CursorActivity";

    private static final String DATABASE_NAME = "employee.db";
    private static final String TABLE_NAME = "employees";
    private static final int DATABASE_VERSION = 1;

    @Bind(R.id.cursorStatus)
    TextView status;

    private DatabaseLoggable logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor);
        ButterKnife.bind(this);

        this.logger = newDatabaseLogger();
        initCursorList();
    }

    private void initCursorList() {
        ListView view = (ListView) findViewById(R.id.cursorList);

        SQLiteDatabase db = openDatabase();
        Cursor cursor = queryRecords(db);
        startManagingCursor(cursor);

        String[] columns = new String[]{"name", "age", "phone"};
        int[] to = new int[]{R.id.cursorName, R.id.cursorAge, R.id.cursorPhone};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.employee_item, cursor, columns, to);
        view.setAdapter(adapter);
    }

    private SQLiteDatabase openDatabase() {
        DatabaseManifest manifest = new DatabaseManifest(DATABASE_NAME, TABLE_NAME, DATABASE_VERSION);
        manifest.setLogger(logger);

        DatabaseHelper dbHelper = new DatabaseHelper(this, manifest);
        return dbHelper.getWritableDatabase();
    }

    private Cursor queryRecords(SQLiteDatabase db) {
        logger.print("queryRecords called.");

        String sql = "SELECT _id, name, age, phone FROM " + TABLE_NAME + " WHERE age > ?";
        String[] args = new String[]{"10"};

        return db.rawQuery(sql, args);
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
