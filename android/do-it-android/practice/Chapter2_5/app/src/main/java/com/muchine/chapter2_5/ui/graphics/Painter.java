package com.muchine.chapter2_5.ui.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class Painter {

    private static final int EXTRA_BORDER = 10;
    private static final float THRESHOLD = 8;

    private final Path path = new Path();

    private final Canvas canvas;
    private Paint paint;

    private float lastX;
    private float lastY;
    private float curveEndX;
    private float curveEndY;

    public Painter(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Rect begin(float x, float y) {
        path.moveTo(x, y);
        canvas.drawPath(path, paint);

        Rect rect = new Rect();
        setRectBorder(rect, x, y);

        lastX = x;
        lastY = y;
        curveEndX = x;
        curveEndY = y;

        return rect;
    }

    public Rect end(float x, float y) {
        Rect rect = moveTo(x, y);
        path.rewind();

        return rect;
    }

    public Rect moveTo(float x, float y) {
        Rect rect = new Rect();

        float dx = Math.abs(x - lastX);
        float dy = Math.abs(y - lastY);
        if (dx < THRESHOLD && dy < THRESHOLD) return rect;

        setRectBorder(rect, curveEndX, curveEndY);

        curveEndX = (x + lastX) / 2;
        curveEndY = (y + lastY) / 2;

        path.quadTo(lastX, lastY, curveEndX, curveEndY);
        canvas.drawPath(path, paint);

        unionRectBorder(rect, lastX, lastY);
        unionRectBorder(rect, curveEndX, curveEndY);

        lastX = x;
        lastY = y;

        return rect;
    }

    private void setRectBorder(Rect rect, float x, float y) {
        int border = EXTRA_BORDER;
        rect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);
    }

    private void unionRectBorder(Rect rect, float x, float y) {
        int border = EXTRA_BORDER;
        rect.union((int) x - border, (int) y - border, (int) x + border, (int) y + border);
    }

}
