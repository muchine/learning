package com.muchine.chapter2_4.ui.calendar;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by sejoonlim on 8/23/16.
 */
public interface OnDataSelectionListener {

    void onDataSelected(AdapterView parent, View v, int position, long id);

}
