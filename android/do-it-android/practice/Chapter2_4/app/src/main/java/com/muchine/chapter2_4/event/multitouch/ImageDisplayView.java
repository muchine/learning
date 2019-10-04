package com.muchine.chapter2_4.event.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.muchine.chapter2_4.event.multitouch.motion.DoubleTouchMotion;
import com.muchine.chapter2_4.event.multitouch.motion.SingleTouchMotion;
import com.muchine.chapter2_4.event.multitouch.motion.TouchMotion;

/**
 * Created by sejoonlim on 8/24/16.
 */
public class ImageDisplayView extends View implements View.OnTouchListener {

    private final String TAG = getClass().getSimpleName();

    private Context context;

    private Canvas canvas;
    private Bitmap source;
    private Paint paint;
    private Matrix matrix;

    private TouchMotion motion;

    private float ratio;

    public ImageDisplayView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void setSourceImage(Bitmap image) {
        recycle();

        this.source = image;
        ratio = 1.0F;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int action = motionEvent.getAction();

        int pointerCount = motionEvent.getPointerCount();
        Log.d(TAG, "Pointer Count: " + pointerCount);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (pointerCount == 1) {
                    motion = newSingleTouchMotion(motionEvent);
                } else if (pointerCount == 2) {
                    motion = new DoubleTouchMotion(motionEvent);
                }
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    if (motion == null) {
                        motion = newSingleTouchMotion(motionEvent);
                        return true;
                    }
                } else if (pointerCount == 2) {
                    if (motion == null) {
                        motion = newDoubleTouchMotion(motionEvent);
                        return true;
                    }
                }

                motion.move(motionEvent);
            case MotionEvent.ACTION_UP:
                motion.move(motionEvent);
        }

        return true;
    }

    private TouchMotion newSingleTouchMotion(MotionEvent event) {
        SingleTouchMotion motion = new SingleTouchMotion(event);
        motion.setOnMoveListener(new TouchMotion.OnMoveListener() {
            @Override
            public void onMove(MotionPoint start, MotionPoint end) {
                Point s = start.point(0);
                Point e = end.point(0);

                moveImage(s.offsetX(e), s.offsetY(e));
            }
        });

        return motion;
    }

    private TouchMotion newDoubleTouchMotion(final MotionEvent event) {
        DoubleTouchMotion motion = new DoubleTouchMotion(event);
        motion.setOnMoveListener(new TouchMotion.OnMoveListener() {
            @Override
            public void onMove(MotionPoint start, MotionPoint end) {
                ImageScalePolicy policy = new ImageScalePolicy(start, end);
                if (!policy.isScalable()) return;

                scaleImage(policy.ratio());
            }
        });

        return motion;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(source, 0, 0, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w <= 0 || h <= 0) return;

        newImage(w, h);
        redraw();
    }

    private void init() {
        paint = new Paint();
        matrix = new Matrix();

        setOnTouchListener(this);
    }

    private void newImage(int width, int height) {
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(image);
    }

    private void redraw() {
        if (source == null) return;

        canvas.drawColor(Color.BLACK);

        float originX = ((float) canvas.getWidth() - (float) source.getWidth()) / 2.0F;
        float originY = ((float) canvas.getHeight() - (float) source.getHeight()) / 2.0F;

        canvas.translate(originX, originY);
        canvas.drawBitmap(source, matrix, paint);
        canvas.translate(-originX, -originY);

        invalidate();
    }

    private void recycle() {
        if (source != null) source.recycle();
    }

    private void scaleImage(float ratio) {
        Log.d(TAG, "scale image... " + ratio);

        float sourceCenterX = source.getWidth() / 2;
        float sourceCenterY = source.getHeight() / 2;

        matrix.postScale(ratio, ratio, sourceCenterX, sourceCenterY);
        matrix.postRotate(0);

        this.ratio *= ratio;
        redraw();
    }

    private void moveImage(float offsetX, float offsetY) {
        if (ratio > 1.0F) return;

        Log.d(TAG, "move image... " + offsetX + ", " + offsetY);

        matrix.postTranslate(offsetX, offsetY);
        redraw();
    }

}
