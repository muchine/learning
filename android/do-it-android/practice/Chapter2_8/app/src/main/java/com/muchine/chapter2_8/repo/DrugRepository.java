package com.muchine.chapter2_8.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.muchine.chapter2_8.db.drug.DrugDatabaseFactory;
import com.muchine.chapter2_8.model.drug.Drug;
import com.muchine.chapter2_8.model.drug.DrugContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugRepository {

    private final SQLiteDatabase db;

    public DrugRepository() {
        this(DrugDatabaseFactory.openDatabase());
    }

    public DrugRepository(SQLiteDatabase db) {
        this.db = db;
    }

    public List<DrugContent> getContents(String drugCode) {
        String statement = "SELECT DRUGCODE, CLASSCODE, CLASSNAME, DETAILS FROM DETAIL WHERE DRUGCODE = ?";
        String[] args = new String[]{drugCode};

        List<DrugContent> contents = new ArrayList<>();

        Cursor cursor = db.rawQuery(statement, args);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            String classCode = cursor.getString(1);
            String className = cursor.getString(2);
            String details = cursor.getString(3);

            contents.add(new DrugContent(drugCode, classCode, className, details));
        }

        return contents;
    }

    public List<Drug> search(String keyword) {
        String statement = "SELECT DRUGCODE, DRUGNAME, PRODENNM, DISTRNAME FROM MASTER WHERE DRUGNAME LIKE ?";
        String[] args = new String[]{keyword + "%"};

        List<Drug> drugs = new ArrayList<>();

        Cursor cursor = db.rawQuery(statement, args);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            String code = cursor.getString(0);
            String name = cursor.getString(1);
            String product = cursor.getString(2);
            String distributor = cursor.getString(3);

            Drug drug = new Drug(code, name, product, distributor);
            drugs.add(drug);
        }

        return drugs;
    }
}
