package com.muchine.chapter2_3;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Mission05Activity extends AppCompatActivity {

    private EditText name;
    private EditText age;

    private Button birthdayButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission05);

        name = (EditText) findViewById(R.id.mission05NameEdit);
        age = (EditText) findViewById(R.id.mission05AgeEdit);

        initBirthdayButton();
        initSaveButton();
    }

    private void initBirthdayButton() {
        birthdayButton = (Button) findViewById(R.id.mission05BirthdayButton);
        birthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());

                DatePickerDialog dialog = new DatePickerDialog(Mission05Activity.this,
                        new BirthdaySetListener(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                dialog.show();
            }
        });
    }

    private void initSaveButton() {
        saveButton = (Button) findViewById(R.id.mission05SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "이름: " + name.getText().toString() + ", 나이: " + age.getText().toString() +
                        ", 생년월일: " + birthdayButton.getText();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private final class BirthdaySetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            birthdayButton.setText(i + "년 " + (i1 + 1) + "월 " + i2 + "일");
        }
    }

}
