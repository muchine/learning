package com.muchine.chapter2_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.muchine.chapter2_2.basic.parcelable.SimpleData;

public class AnotherActivity extends AppCompatActivity {

    private TextView parcelValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        parcelValue = (TextView) findViewById(R.id.parcelValue);

        processIntent(getIntent());
    }

    private void processIntent(Intent intent) {
        Bundle bundle = getIntent().getExtras();

        SimpleData data = (SimpleData) bundle.getParcelable(MainActivity.KEY_SIMPLE_DATA);
        parcelValue.setText("Parcelable로 전달된 값\nNumber: " + data.getNumber() + "\nMessage: " + data.getMessage());
    }

    public void onBackButtonClicked(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", "mike");

        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
