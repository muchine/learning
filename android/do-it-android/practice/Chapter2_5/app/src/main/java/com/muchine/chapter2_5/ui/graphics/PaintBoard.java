package com.muchine.chapter2_5.ui.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.muchine.chapter2_5.ui.graphics.painter.LinePainter;
import com.muchine.chapter2_5.ui.graphics.painter.Paintable;

/**
 * Created by sejoonlim on 8/31/16.
 */
public class PaintBoard extends View {

    private static final String TAG = PaintBoard.class.getSimpleName();

    private Paintable painter;
    private SnapshotManager snapshotManager = new SnapshotManager();

    private Canvas canvas;

    private Bitmap bitmap;

    private Paint paint;

    public PaintBoard(Context context) {
        super(context);

        this.paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    public int getColor() {
        return paint.getColor();
    }

    public float getPenWidth() {
        return paint.getStrokeWidth();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setPenWidth(int penWidth) {
        paint.setStrokeWidth(penWidth);
    }

    public void setStrokeCap(Paint.Cap cap) {
        paint.setStrokeCap(cap);
    }

    public void undo() {
        Bitmap snapshot = snapshotManager.pop();
        if (snapshot == null) return;

        Log.d(TAG, "undo with a last snapshot");

        drawBackground();
        canvas.drawBitmap(snapshot, 0, 0, paint);
        invalidate();

        snapshot.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);

        painter = new LinePainter(canvas, paint);

        drawBackground();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap == null) return;
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                snapshotManager.createSnapshot(bitmap, paint);
                painter.begin(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                painter.moveTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                painter.end(x, y);
                break;
        }

        painter.paint(this);
        return true;
    }

    private void drawBackground() {
        canvas.drawColor(Color.WHITE);
    }

}
