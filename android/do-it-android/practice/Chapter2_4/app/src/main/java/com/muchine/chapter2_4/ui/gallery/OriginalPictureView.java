package com.muchine.chapter2_4.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.muchine.chapter2_4.R;

/**
 * Created by sejoonlim on 8/19/16.
 */
public class OriginalPictureView extends Activity {

    private ImageView imageView;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictureview);

        imageView = (ImageView) findViewById(R.id.galleryImageView);
        processIntent();
    }

    private void processIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        filename = extras.getString("filename");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeFile(filename, options);
        imageView.setImageBitmap(bitmap);
    }
}
