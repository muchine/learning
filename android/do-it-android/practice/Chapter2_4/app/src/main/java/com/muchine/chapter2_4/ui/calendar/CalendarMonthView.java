package com.muchine.chapter2_4.ui.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by sejoonlim on 8/23/16.
 */
public class CalendarMonthView extends GridView {

    private OnDataSelectionListener selectionListener;

    private CalendarMonthAdapter adapter;

    public CalendarMonthView(Context context) {
        super(context);
        init();
    }

    public CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OnDataSelectionListener getSelectionListener() {
        return selectionListener;
    }

    public void setSelectionListener(OnDataSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    @Override
    public Object getSelectedItem() {
        int position = adapter.getSelectedPosition();
        return getItemAtPosition(position);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        this.adapter = (CalendarMonthAdapter) adapter;
    }

    private void init() {
        setBackgroundColor(Color.GRAY);
        setVerticalSpacing(1);
        setHorizontalSpacing(1);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        setNumColumns(CalendarMonthAdapter.COLUMN_COUNT);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapter != null) {
                    adapter.setSelectedPosition(i);
                    adapter.notifyDataSetChanged();
                }

                if (selectionListener != null) {
                    selectionListener.onDataSelected(adapterView, view, i, l);
                }
            }
        });
    }

}
