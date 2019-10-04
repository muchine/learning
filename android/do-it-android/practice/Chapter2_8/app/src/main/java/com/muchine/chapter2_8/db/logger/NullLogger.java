package com.muchine.chapter2_8.db.logger;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class NullLogger implements DatabaseLoggable {

    private static NullLogger instance;

    private NullLogger() {
    }

    public static NullLogger getInstance() {
        if (instance == null) instance = new NullLogger();
        return instance;
    }

    @Override
    public void print(String message) {

    }
}
