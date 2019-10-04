package com.muchine.chapter2_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muchine.chapter2_5.ui.custom.CustomViewImage;

import java.util.ArrayList;
import java.util.List;

public class CustomViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomViewImage(this));

        List<String> aa = new ArrayList<>();
    }
}
