package com.muchine.chapter2_4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sejoonlim on 8/29/16.
 */
public class DailyEventGroup {

    private final List<Event> events = new ArrayList<>();

    public void set(String name) {
        events.clear();
        events.add(new Event("", "", name));
    }

    public void add(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}
