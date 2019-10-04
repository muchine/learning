package com.muchine.chapter2_2.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muchine.chapter2_2.R;

public class Mission03MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03_main);
    }

    public void onLoginButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), Mission03MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MENU) handleActivityResult(resultCode, data);
    }

    private void handleActivityResult(int resultCode, Intent data) {
        String name = data.getExtras().getString("name");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

}
