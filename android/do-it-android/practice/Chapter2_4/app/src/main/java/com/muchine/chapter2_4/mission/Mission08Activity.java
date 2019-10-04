package com.muchine.chapter2_4.mission;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.muchine.chapter2_4.R;
import com.muchine.chapter2_4.fragment.NewEventFragment;
import com.muchine.chapter2_4.model.Event;
import com.muchine.chapter2_4.model.EventHolder;
import com.muchine.chapter2_4.ui.calendar.CalendarMonthAdapter;
import com.muchine.chapter2_4.ui.calendar.CalendarMonthView;
import com.muchine.chapter2_4.ui.calendar.MonthItem;
import com.muchine.chapter2_4.ui.calendar.OnDataSelectionListener;

import java.util.List;

public class Mission08Activity extends AppCompatActivity {

    private CalendarMonthView calendarView;

    private CalendarMonthAdapter calendarAdapter;

    private TextView calendarText;

    private EventHolder eventHolder;

    private ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission08);

        initCalendarView();
        initCalendarText();
        initPreviousButton();
        initNextButton();

        this.eventHolder = new EventHolder();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_new) {
            MonthItem calendarItem = (MonthItem) calendarView.getSelectedItem();

            if (calendarItem == null) {
                Toast.makeText(this, "날짜를 선택하세요", Toast.LENGTH_LONG).show();
                return true;
            }

            FragmentManager manager = getSupportFragmentManager();
            NewEventFragment fragment = new NewEventFragment();

            fragment.initialize(eventHolder, calendarItem.toString());
            fragment.setOnSaveListener(new NewEventFragment.OnSaveListener() {
                @Override
                public void onSave() {
                    refreshEventList();
                }
            });

            fragment.show(manager, "new_event_fragment");
        }

        return super.onOptionsItemSelected(item);
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

                initListView(item.toString());

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

    public void refreshEventList() {
        MonthItem item = (MonthItem) calendarView.getSelectedItem();
        if (item == null) return;

        initListView(item.toString());
    }

    private void initListView(String date) {
        ListView eventList = (ListView) findViewById(R.id.mission08EventList);

        List<Event> events = eventHolder.getEvents(date);
        int eventSize = events == null ? 0 : events.size();
        String[] eventNames = new String[eventSize];
        for (int i = 0 ; i < eventSize; i++) {
            eventNames[i] = events.get(i).toString();
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventNames);
        eventList.setAdapter(adapter);
    }

    private void setText() {
        int year = calendarAdapter.getYear();
        int month = calendarAdapter.getMonth() + 1;

        calendarText.setText(year + "년 " + month + "월");
    }
}
