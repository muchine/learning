package com.muchine.chapter2_4.model;

/**
 * Created by sejoonlim on 8/29/16.
 */
public class Event {

    private String hour;

    private String minute;

    private String name;

    public Event(String hour, String minute, String name) {
        this.hour = hour;
        this.minute = minute;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return hour + ":" + minute + " " + name;
    }
}
