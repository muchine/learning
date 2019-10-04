package com.muchine.chapter2_4.ui.calendar;

/**
 * Created by sejoonlim on 8/23/16.
 */
public class MonthItem {

    private final int year;

    private final int month;

    private final int dayValue;

    public MonthItem(int year, int month, int dayValue) {
        this.year = year;
        this.month = month;
        this.dayValue = dayValue;
    }

    public int getDayValue() {
        return dayValue;
    }

    @Override
    public String toString() {
        return "" + year + month + dayValue;
    }

}
