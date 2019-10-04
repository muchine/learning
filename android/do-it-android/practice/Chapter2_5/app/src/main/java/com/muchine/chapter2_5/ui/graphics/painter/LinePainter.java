package com.muchine.chapter2_5.ui.graphics.painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class LinePainter implements Paintable {

    private final Canvas canvas;
    private final Paint paint;

    private int lastX = -1;
    private int lastY = -1;

    public LinePainter(Canvas canvas, Paint paint) {
        this.canvas = canvas;
        this.paint = paint;
    }

    @Override
    public void paint(View view) {
        view.invalidate();
    }

    @Override
    public void begin(int x, int y) {
        saveCoordinate(x, y);
    }

    @Override
    public void moveTo(int x, int y) {
        if (x == lastX && y == lastY) return;

        canvas.drawLine(lastX, lastY, x, y, paint);
        saveCoordinate(x, y);
    }

    @Override
    public void end(int x, int y) {
        moveTo(x, y);
        initCoordinate();
    }

    private void initCoordinate() {
        saveCoordinate(-1, -1);
    }

    private void saveCoordinate(int x, int y) {
        lastX = x;
        lastY = y;
    }
}
