package com.muchine.hello.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muchine.hello.R;

public class Mission02Activity extends AppCompatActivity {

    private EditText text;
    private TextView textLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        text = (EditText) findViewById(R.id.smsText);
        textLength = (TextView) findViewById(R.id.smsLength);

//        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(80);
//        text.setFilters(new InputFilter[] { lengthFilter });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.toString().length();
                textLength.setText(length + " / 80 바이트");
            }
        });
    }

    public void onSend(View v) {
        String textValue = text.getText().toString();
        Toast.makeText(getApplicationContext(), textValue, Toast.LENGTH_LONG).show();
    }

    public void onClose(View v) {
        Toast.makeText(getApplicationContext(), "Close!", Toast.LENGTH_LONG).show();
    }

}
