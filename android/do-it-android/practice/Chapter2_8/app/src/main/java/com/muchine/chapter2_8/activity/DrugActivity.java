package com.muchine.chapter2_8.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.db.drug.DrugDatabaseGenerator;
import com.muchine.chapter2_8.db.logger.DatabaseLoggable;
import com.muchine.chapter2_8.permission.PermissionRequestable;
import com.muchine.chapter2_8.permission.StoragePermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrugActivity extends AppCompatActivity {

    private static final String TAG = "DrugActivity";

    private List<PermissionRequestable> permissionRequestables = new ArrayList<>();

    @Bind(R.id.drugStatus)
    TextView status;

    private DatabaseLoggable logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        ButterKnife.bind(this);

        this.logger = newLogger();

        initPermissionManagers();
        initGenerationButton();

        requestPermissions();
    }

    private void initPermissionManagers() {
        permissionRequestables.add(new StoragePermissionManager(this));
    }

    private void requestPermissions() {
        for (PermissionRequestable r : permissionRequestables) {
            r.requestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionRequestable requestable = findPermissionRequestable(requestCode);
        requestable.handlePermissionRequestResult(permissions, grantResults);
    }

    private PermissionRequestable findPermissionRequestable(int requestCode) {
        for (PermissionRequestable r : permissionRequestables) {
            if (r.getRequestCode() == requestCode) return r;
        }

        throw new RuntimeException("requestable not found.");
    }

    private void initGenerationButton() {
        Button button = (Button) findViewById(R.id.drugCreationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrugDatabaseGenerator generator = new DrugDatabaseGenerator(logger);
                generator.generate();
            }
        });
    }

    private DatabaseLoggable newLogger() {
        return new DatabaseLoggable() {
            @Override
            public void print(String message) {
                Log.d(TAG, message);
                status.append("\n" + message);
            }
        };
    }



}
