package com.muchine.chapter2_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.muchine.chapter2_3.basic.parcelable.SimpleData;

public class AnotherActivity extends AppCompatActivity {

    private TextView parcelValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        parcelValue = (TextView) findViewById(R.id.parcelValue);

        processIntent(getIntent());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) return false;

        close("andrew");
        return true;
    }

    public void onBackButtonClicked(View v) {
        close("mike");
    }

    private void processIntent(Intent intent) {
        Bundle bundle = getIntent().getExtras();

        SimpleData data = (SimpleData) bundle.getParcelable(KeyEventActivity.KEY_SIMPLE_DATA);
        parcelValue.setText("Parcelable로 전달된 값\nNumber: " + data.getNumber() + "\nMessage: " + data.getMessage());
    }

    private void close(String name) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", name);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
