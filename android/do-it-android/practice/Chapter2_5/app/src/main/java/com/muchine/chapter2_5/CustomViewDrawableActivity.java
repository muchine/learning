package com.muchine.chapter2_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muchine.chapter2_5.ui.custom.CustomViewDrawables;

/**
 * Created by sejoonlim on 8/30/16.
 */
public class CustomViewDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomViewDrawables(this));
    }

}
