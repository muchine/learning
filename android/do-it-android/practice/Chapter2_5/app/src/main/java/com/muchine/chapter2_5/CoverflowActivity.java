package com.muchine.chapter2_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muchine.chapter2_5.ui.coverflow.CoverFlow;
import com.muchine.chapter2_5.ui.coverflow.ImageAdapter;

public class CoverflowActivity extends AppCompatActivity {

    private static final int SPACING = -45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coverflow);

        ImageAdapter adapter = new ImageAdapter(this);

        CoverFlow coverFlow = (CoverFlow) findViewById(R.id.coverflow);
        coverFlow.setAdapter(adapter);

        coverFlow.setSpacing(SPACING);
        coverFlow.setSelection(2, true);
        coverFlow.setAnimationDuration(3000);
    }
}
