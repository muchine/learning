package com.muchine.chapter2_4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.muchine.chapter2_4.ui.bitmap.widget.TitleBitmapButton;
import com.muchine.chapter2_4.ui.bitmap.widget.TitleButton;

public class BitmapWidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_widget);

        getSupportActionBar().hide();

        initArrowLeftButton();
        initTitleButton();
    }

    private void initArrowLeftButton() {
        TitleBitmapButton arrowLeftButton = (TitleBitmapButton) findViewById(R.id.bitmapWidgetArrowLeftButton);

        Resources res = getResources();
        BitmapDrawable drawable = (BitmapDrawable) res.getDrawable(R.drawable.arrow_left);
        Bitmap iconBitmap = drawable.getBitmap();
        BitmapDrawable clickedDrawable = (BitmapDrawable) res.getDrawable(R.drawable.arrow_left_clicked);
        Bitmap iconClickedBitmap = clickedDrawable.getBitmap();
        arrowLeftButton.setIconBitmap(iconBitmap, iconClickedBitmap);

        arrowLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initTitleButton() {
        TitleButton titleButton = (TitleButton) findViewById(R.id.bitmapWidgetTitleButton);
        titleButton.setTitleText("비트맵 Title");
        titleButton.setDefaultSize(32F);
    }
}
