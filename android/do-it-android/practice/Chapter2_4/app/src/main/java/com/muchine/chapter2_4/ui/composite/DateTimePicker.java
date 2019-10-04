package com.muchine.chapter2_4.ui.composite;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.muchine.chapter2_4.R;

import java.util.Calendar;

/**
 * Created by sejoonlim on 8/23/16.
 */
public class DateTimePicker extends LinearLayout {

    public interface OnDateTimeChangedListener {

        void onDateTimeChanged(DateTimePicker view, int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute);

    }

    private OnDateTimeChangedListener onDateTimeChangedListener;

    private final DatePicker datePicker;
    private final CheckBox enableTimeCheckBox;
    private final TimePicker timePicker;

    public DateTimePicker(Context context) {
        this(context, null);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.datetimepicker, this, true);

        Calendar calendar = Calendar.getInstance();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateTimePicker);
        final int currentYear = a.getInt(R.styleable.DateTimePicker_year, calendar.get(Calendar.YEAR));
        final int currentMonth = a.getInt(R.styleable.DateTimePicker_month, calendar.get(Calendar.MONTH));
        final int currentDay = a.getInt(R.styleable.DateTimePicker_day, calendar.get(Calendar.DAY_OF_MONTH));
        final int currentHour = a.getInt(R.styleable.DateTimePicker_hour, calendar.get(Calendar.HOUR_OF_DAY));
        final int currentMinute = a.getInt(R.styleable.DateTimePicker_minute, calendar.get(Calendar.MINUTE));

        this.datePicker = initDatePicker(currentYear, currentMonth, currentDay);
        this.enableTimeCheckBox = initEnableTimeCheckBox();
        this.timePicker = initTimePicker(currentHour, currentMinute);

        setTimePickerVisibility(enableTimeCheckBox.isChecked());
    }

    public void setOnDateTimeChangedListener(OnDateTimeChangedListener onDateTimeChangedListener) {
        this.onDateTimeChangedListener = onDateTimeChangedListener;
    }

    public void updateDateTime(int year, int monthOfYear, int dayOfMonth, int currentHour, int currentMinute) {
        updateDate(year, monthOfYear, dayOfMonth);
        timePicker.setCurrentHour(currentHour);
        timePicker.setCurrentMinute(currentMinute);
    }

    public void updateDate(int year, int monthOfYear, int dayOfMonth) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
    }

    public void setIs24HourView(final boolean is24HourView) {
        timePicker.setIs24HourView(is24HourView);
    }

    public int getYear() {
        return datePicker.getYear();
    }

    public int getMonth() {
        return datePicker.getMonth();
    }

    public int getDayOfMonth() {
        return datePicker.getDayOfMonth();
    }

    public int getCurrentHour() {
        return timePicker.getCurrentHour();
    }

    public int getCurrentMinute() {
        return timePicker.getCurrentMinute();
    }

    public boolean enableTime() {
        return enableTimeCheckBox.isChecked();
    }

    private DatePicker initDatePicker(final int year, final int month, final int day) {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                if (onDateTimeChangedListener == null) return;

                onDateTimeChangedListener.onDateTimeChanged(DateTimePicker.this, year, monthOfYear, dayOfMonth, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
            }
        });

        return datePicker;
    }

    private CheckBox initEnableTimeCheckBox() {
        final CheckBox enableTimeCheckBox = (CheckBox) findViewById(R.id.enableTimeCheckBox);
        enableTimeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setTimePickerVisibility(b);
            }
        });

        return enableTimeCheckBox;
    }

    private TimePicker initTimePicker(final int hour, final int minute) {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                if (onDateTimeChangedListener == null) return;
                ;

                onDateTimeChangedListener.onDateTimeChanged(DateTimePicker.this, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
            }
        });

        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        return timePicker;
    }

    private void setTimePickerVisibility(boolean enabled) {
        timePicker.setEnabled(enabled);
        timePicker.setVisibility(enableTimeCheckBox.isChecked() ? View.VISIBLE : View.INVISIBLE);
    }

}
