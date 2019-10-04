package com.muchine.chapter2_5;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ImageScaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagescale);

        ImageView image01 = (ImageView) findViewById(R.id.imageScale01);
        ImageView image02 = (ImageView) findViewById(R.id.imageScale02);
        ImageView image03 = (ImageView) findViewById(R.id.imageScale03);
        ImageView image04 = (ImageView) findViewById(R.id.imageScale04);
        ImageView image05 = (ImageView) findViewById(R.id.imageScale05);
        ImageView image06 = (ImageView) findViewById(R.id.imageScale06);
        ImageView image07 = (ImageView) findViewById(R.id.imageScale07);
        ImageView image08 = (ImageView) findViewById(R.id.imageScale08);

        image01.setScaleType(ImageView.ScaleType.CENTER);
        image02.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image03.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image04.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image05.setScaleType(ImageView.ScaleType.FIT_END);
        image06.setScaleType(ImageView.ScaleType.FIT_START);
        image07.setScaleType(ImageView.ScaleType.FIT_XY);
        image08.setScaleType(ImageView.ScaleType.MATRIX);

        Matrix matrix = new Matrix();
        matrix.postRotate(45.0F);
        image08.setImageMatrix(matrix);
    }
}
