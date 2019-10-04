package com.muchine.chapter2_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.muchine.chapter2_4.ui.composite.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimePickerActivity extends AppCompatActivity {

    private TextView dateTimeView;
    private DateTimePicker dateTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite);

        dateTimeView = (TextView) findViewById(R.id.compositeTextView1);
        initDateTimePicker();
    }

    private void initDateTimePicker() {
        dateTimePicker = (DateTimePicker) findViewById(R.id.dateTimePicker);
        dateTimePicker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(DateTimePicker view, int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute) {
                setDateTimeText(year, monthOfYear, dayOfMonth, hourOfDay, minute);
            }
        });

        setDateTimeText(dateTimePicker);
    }

    private void setDateTimeText(DateTimePicker picker) {
        setDateTimeText(picker.getYear(), picker.getMonth(), picker.getDayOfMonth(), picker.getCurrentHour(), picker.getCurrentMinute());
    }

    private void setDateTimeText(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        dateTimeView.setText(formatter.format(calendar.getTime()));
    }

}
