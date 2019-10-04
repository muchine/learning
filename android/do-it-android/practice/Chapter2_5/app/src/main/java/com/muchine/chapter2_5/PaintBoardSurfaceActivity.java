package com.muchine.chapter2_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muchine.chapter2_5.ui.graphics.PaintBoardSurface;

public class PaintBoardSurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintBoardSurface(this));
    }
}
