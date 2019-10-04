package com.muchine.chapter2_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.muchine.chapter2_4.ui.calendar.CalendarMonthAdapter;
import com.muchine.chapter2_4.ui.calendar.CalendarMonthView;
import com.muchine.chapter2_4.ui.calendar.MonthItem;
import com.muchine.chapter2_4.ui.calendar.OnDataSelectionListener;

public class CalendarMonthActivity extends AppCompatActivity {

    private CalendarMonthView calendarView;

    private CalendarMonthAdapter calendarAdapter;

    private TextView calendarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initCalendarView();
        initCalendarText();
        initPreviousButton();
        initNextButton();
    }

    private void initCalendarView() {
        calendarView = (CalendarMonthView) findViewById(R.id.monthView);
        calendarAdapter = new CalendarMonthAdapter(this);
        calendarView.setAdapter(calendarAdapter);

        calendarView.setSelectionListener(new OnDataSelectionListener() {
            @Override
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                MonthItem item = (MonthItem) calendarView.getItemAtPosition(position);
                int day = item.getDayValue();

                Log.d("MainActivity", "Selected : " + day);
            }
        });
    }

    private void initCalendarText() {
        calendarText = (TextView) findViewById(R.id.monthText);
        setText();
    }

    private void initPreviousButton() {
        Button previousButton = (Button) findViewById(R.id.monthPrevious);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarAdapter.setPreviousMonth();
                calendarAdapter.notifyDataSetChanged();
                setText();
            }
        });
    }

    private void initNextButton() {
        Button nextButton = (Button) findViewById(R.id.monthNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarAdapter.setNextMonth();
                calendarAdapter.notifyDataSetChanged();
                setText();
            }
        });
    }

    private void setText() {
        int year = calendarAdapter.getYear();
        int month = calendarAdapter.getMonth() + 1;

        calendarText.setText(year + "년 " + month + "월");
    }
}
