package com.muchine.chapter2_4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.muchine.chapter2_4.ui.gallery.OriginalPictureView;

public class GalleryActivity extends AppCompatActivity {

    private Cursor imageCursor;
    private Cursor actualImageCursor;

    private int imageColumnIndex;
    private int actualImageColumnIndex;

    private Gallery gallery;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        checkDangerousPermissions();
        init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != 1) return;

        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkDangerousPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "권한 설명 필요함", Toast.LENGTH_LONG).show();
            return;
        }

        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 1);
    }

    private void init() {
        String[] image = {MediaStore.Images.Thumbnails._ID};
        imageCursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, image, null, null, MediaStore.Images.Thumbnails.IMAGE_ID + "");

        imageColumnIndex = imageCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
        count = imageCursor.getCount();

        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(getApplicationContext()));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] data = {MediaStore.Images.Media.DATA};
                actualImageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, data, null, null, null);
                actualImageColumnIndex = actualImageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualImageCursor.moveToPosition(i);
                String filename = actualImageCursor.getString(actualImageColumnIndex);

                Intent intent = new Intent(getApplicationContext(), OriginalPictureView.class);
                intent.putExtra("filename", filename);

                startActivity(intent);
            }
        });
    }

    private class ImageAdapter extends BaseAdapter {

        private Context context;
        private Gallery.LayoutParams params;

        public ImageAdapter(Context applicationContext) {
            context = applicationContext;

            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            Display display = manager.getDefaultDisplay();
            display.getMetrics(metrics);

            params = new Gallery.LayoutParams(metrics.widthPixels / 3, Gallery.LayoutParams.MATCH_PARENT);
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = view == null ? new ImageView(context) : (ImageView) view;

            imageCursor.moveToPosition(i);
            int id = imageCursor.getInt(imageColumnIndex);

            imageView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, String.valueOf(id)));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(params);

            return imageView;
        }
    }
}
