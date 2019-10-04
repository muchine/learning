package com.muchine.chapter2_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.imageMainLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView view = new ImageView(this);
        view.setImageResource(R.drawable.waterdrop);
        view.setAdjustViewBounds(true);

        mainLayout.addView(view, params);
    }
}
