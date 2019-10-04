package com.muchine.chapter2_4.ui.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

/**
 * Created by sejoonlim on 8/23/16.
 */
public class CalendarMonthAdapter extends BaseAdapter {

    public static final String TAG = CalendarMonthAdapter.class.getSimpleName();

    public static final int COLUMN_COUNT = 7;
    public static final int ROW_COUNT = 6;
    public static final int MAX_CELL_COUNT = COLUMN_COUNT * ROW_COUNT;

    private Context context;
    private int selectedPosition = -1;
    private MonthItem[] items;

    private int firstDay;
    private int lastDay;

    private Calendar calendar;

    public CalendarMonthAdapter(Context context) {
        super();

        this.context = context;
        init();
    }

    public CalendarMonthAdapter(Context context, AttributeSet attrs) {
        super();

        this.context = context;
        init();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getCount() {
        return MAX_CELL_COUNT;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(TAG, "getView(" + i + ") called.");

        MonthItemView itemView = (MonthItemView) view;
        if (itemView == null) itemView = new MonthItemView(context);

        setItem(itemView, i);
        return itemView;
    }

    public void setPreviousMonth() {
        calendar.add(Calendar.MONTH, -1);
        render();
    }

    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        render();
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public void render() {
        recalculate();
        resetDayNumbers();
        selectedPosition = -1;
    }

    private void recalculate() {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Log.d(TAG, "year: " + getYear() + ", month: " + getMonth());

        firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "firstDay: " + firstDay + ", lastDay: " + lastDay);
    }

    private void resetDayNumbers() {
        for (int i = 0; i < MAX_CELL_COUNT; i++) {
            int dayNumber = (i + 1) - firstDay;
            if (dayNumber < 1 || dayNumber > lastDay) dayNumber = 0;

            items[i] = new MonthItem(getYear(), getMonth(), dayNumber);
        }
    }

    private void init() {
        items = new MonthItem[MAX_CELL_COUNT];

        calendar = Calendar.getInstance();
        render();
    }

    private void setItem(MonthItemView view, int position) {
        int rowIndex = position / COLUMN_COUNT;
        int columnIndex = position % COLUMN_COUNT;

        Log.d(TAG, "Index: " + rowIndex + ", " + columnIndex);
        view.setItem(items[position]);

        setItemLayout(view);
        setItemTextColor(view, columnIndex);
        setItemBackgroundColor(view, position);
    }

    private void setItemLayout(MonthItemView view) {
        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 120);
        view.setLayoutParams(params);
        view.setPadding(2, 2, 2, 2);
        view.setGravity(Gravity.LEFT);
    }

    private void setItemTextColor(MonthItemView view, int columnIndex) {
        int color = columnIndex == 0 ?  Color.RED : Color.BLACK;
        view.setTextColor(color);
    }

    private void setItemBackgroundColor(MonthItemView view, int position) {
        int color = position == selectedPosition ? Color.YELLOW : Color.WHITE;
        view.setBackgroundColor(color);
    }

}
