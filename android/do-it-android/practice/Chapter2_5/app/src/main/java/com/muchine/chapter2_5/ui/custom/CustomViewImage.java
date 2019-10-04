package com.muchine.chapter2_5.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

import com.muchine.chapter2_5.R;

/**
 * Created by sejoonlim on 8/30/16.
 */
public class CustomViewImage extends View {

    private Bitmap cacheBitmap;

    public CustomViewImage(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Canvas canvas = createCacheBitmap(w, h);
        drawBitmap(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (cacheBitmap == null) return;
        canvas.drawBitmap(cacheBitmap, 0, 0, null);
    }

    private Canvas createCacheBitmap(int width, int height) {
        cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas();
        canvas.setBitmap(cacheBitmap);

        return canvas;
    }

    private void drawBitmap(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(100, 100, 200, 200, paint);

        drawWaterdrops(canvas);
        drawFace(canvas);
    }

    private void drawWaterdrops(Canvas canvas) {
        Paint paint = new Paint();

        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
        canvas.drawBitmap(source, 30, 30, paint);

        Bitmap horizontalInversed = scaleImage(source, -1, 1);
        canvas.drawBitmap(horizontalInversed, 30, 130, paint);

        Bitmap verticalInversed = scaleImage(source, 1, -1);
        canvas.drawBitmap(verticalInversed, 30, 230, paint);
    }

    private void drawFace(Canvas canvas) {
        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.face);

        Paint paint = new Paint();
        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));

        Bitmap scaled = Bitmap.createScaledBitmap(source, source.getWidth() * 2, source.getHeight() * 2, false);
        canvas.drawBitmap(scaled, 100, 230, paint);
    }

    private Bitmap scaleImage(Bitmap source, float sx, float sy) {
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy); // horizontally inversed
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, false);
    }

}
