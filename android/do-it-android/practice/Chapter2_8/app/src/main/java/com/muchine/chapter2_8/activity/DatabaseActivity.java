package com.muchine.chapter2_8.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.model.Employee;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";

    private SQLiteDatabase db;

    @Bind(R.id.databaseNameInput)
    EditText databaseNameInput;

    @Bind(R.id.databaseTableNameInput)
    EditText tableNameInput;

    @Bind(R.id.databaseStatus)
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);

        initDatabaseCreationButton();
        initTableCreationButton();
    }

    private void initDatabaseCreationButton() {
        Button button = (Button) findViewById(R.id.databaseCreationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatabase();
            }
        });
    }

    private void initTableCreationButton() {
        Button button = (Button) findViewById(R.id.databaseTableCreationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = tableNameInput.getText().toString();

                createTable(tableName);
                insertRecords(tableName);

                insertRawRecord(tableName, "Rice", 28, "010-1414-2424");
                updateRawRecord(tableName, "Rice");
                deleteRawRecord(tableName, "Rice");
            }
        });
    }

    private void createDatabase() {
        String databaseName = databaseNameInput.getText().toString();

        try {
            db = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
            printStatus("A database has been created.");
        } catch (Exception e) {
            e.printStackTrace();
            printStatus("A database has not been created.");
        }
    }

    private void createTable(String tableName) {
        if (db == null) return;

        printStatus("creating table " + tableName + " ...");

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE " + tableName + " (");
        builder.append("_id integer PRIMARY KEY AUTOINCREMENT,");
        builder.append("name text,");
        builder.append("age integer,");
        builder.append("phone text);");

        db.execSQL(builder.toString());
    }

    private int insertRecords(String tableName) {
        List<Employee> employees = newEmployees();

        for (Employee employee : employees) {
            employee.save(db, tableName);
        }

        printStatus(employees.size() + " records have been inserted.");
        return employees.size();
    }

    private void insertRawRecord(String tableName, String name, int age, String phone) {
        printStatus("inserting records using parameters.");

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("phone", phone);

        db.insert(tableName, null, values);
    }

    private int updateRawRecord(String tableName, String name) {
        printStatus("updating records using parameters.");

        ContentValues values = new ContentValues();
        values.put("age", 43);
        String[] whereArgs = new String[]{name};

        return db.update(tableName, values, "name = ?", whereArgs);
    }

    private int deleteRawRecord(String tableName, String name) {
        printStatus("deleting records using parameters.");

        String[] args = new String[]{name};
        return db.delete(tableName, "name = ?", args);
    }

    private void printStatus(String message) {
        Log.d(TAG, message);
        status.append("\n" + message);
    }

    private List<Employee> newEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("John", 20, "010-7788-1234"));
        employees.add(new Employee("Hyewon", 35, "010-2877-3026"));
        employees.add(new Employee("Sejoon", 37, "010-2920-6174"));

        return employees;
    }

}
