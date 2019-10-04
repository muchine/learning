package com.muchine.chapter2_5.ui.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.muchine.chapter2_5.ui.graphics.painter.LinePainter;
import com.muchine.chapter2_5.ui.graphics.painter.Paintable;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class PaintBoardSurface extends SurfaceView implements SurfaceHolder.Callback {

    private final SurfaceHolder holder;

    private Bitmap bitmap;

    private Paintable painter;

    public PaintBoardSurface(Context context) {
        super(context);

        this.holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                painter.begin(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                painter.moveTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                painter.end(x, y);
                break;
        }

        draw();
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        initPainter(bitmap);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void initPainter(Bitmap bitmap) {
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        this.painter = new LinePainter(canvas, paint);
    }

    private void draw() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            super.draw(canvas);
            canvas.drawBitmap(bitmap, 0, 0, null);
        } finally {
            if (canvas != null) holder.unlockCanvasAndPost(canvas);
        }
    }

}
