package com.muchine.chapter2_8.model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class Employee {

    private final String name;

    private final int age;

    private final String phone;

    public Employee(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public void save(SQLiteDatabase db, String tableName) {
        String sql = "INSERT INTO " + tableName + " (name, age, phone) values ('" + name + "', " + age + ", '" + phone + "')";
        db.execSQL(sql);
    }

}
