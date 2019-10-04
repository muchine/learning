package com.muchine.chapter2_4.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sejoonlim on 8/29/16.
 */
public class EventHolder {

    private final Map<String, DailyEventGroup> events = new HashMap<>();

    public void save(String date, String name) {
        DailyEventGroup group = getEventGroup(date);
        group.set(name);
    }

    public void save(String date, Event event) {
        DailyEventGroup group = getEventGroup(date);
        group.add(event);
    }

    public List<Event> getEvents(String date) {
        DailyEventGroup group = events.get(date);
        return group == null ? null : group.getEvents();
    }

    public String get(String date) {
        DailyEventGroup group = events.get(date);
        return group == null ? "" : group.getEvents().get(0).getName();
    }

    private DailyEventGroup getEventGroup(String date) {
        DailyEventGroup group = events.get(date);
        if (group == null) {
            group = new DailyEventGroup();
            events.put(date, group);
        }

        return group;
    }

}
