package com.muchine.chapter2_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muchine.chapter2_3.basic.parcelable.SimpleData;

public class KeyEventActivity extends AppCompatActivity {

    public static final String KEY_SIMPLE_DATA = "data";

    private static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyevents);
    }

    public void onNewActivityClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
        intent.putExtra(KEY_SIMPLE_DATA, new SimpleData(100, "Hello Android!"));

        startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ANOTHER) handleAnotherActivityResult(resultCode, data);
    }

    private void handleAnotherActivityResult(int resultCode, Intent intent) {
        String message = "onActivityResult method invoked. request code: " + REQUEST_CODE_ANOTHER
                + ", result code: " + resultCode;

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();

        if (resultCode != RESULT_OK) return;

        String name = intent.getExtras().getString("name");
        toast = Toast.makeText(getApplicationContext(), "result name: " + name, Toast.LENGTH_LONG);
        toast.show();
    }

}
