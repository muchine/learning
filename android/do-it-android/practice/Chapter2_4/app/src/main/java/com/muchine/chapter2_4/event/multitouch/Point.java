package com.muchine.chapter2_4.event.multitouch;

import android.view.MotionEvent;

/**
 * Created by sejoonlim on 8/25/16.
 */
public class Point {

    private float x;
    private float y;

    public Point(MotionEvent event) {
        this(event.getX(), event.getY());
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float offsetX(Point p) {
        return x - p.x;
    }

    public float offsetY(Point p) {
        return y - p.y;
    }

    public float distance(Point p) {
        float offsetX = offsetX(p);
        float offsetY = offsetY(p);

        return new Double(Math.sqrt(new Float(offsetX * offsetX + offsetY * offsetY).doubleValue())).floatValue();
    }

}
