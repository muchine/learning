package com.muchine.chapter2_4.event.multitouch;

import android.view.MotionEvent;

/**
 * Created by sejoonlim on 8/25/16.
 */
public class MotionPoint {

    private final int count;
    private Point[] start;

    public MotionPoint(MotionEvent event, int count) {
        this.count = count;
        this.start = new Point[count];

        for (int i = 0; i < count; i++) {
            start[i] = new Point(event.getX(i), event.getY(i));
        }
    }

    public MotionPoint(Point... points) {
        this.count = points.length;
        this.start = new Point[count];

        for (int i = 0; i < count; i++) {
            start[i] = points[i];
        }
    }

    public Point point(int index) {
        return start[index];
    }

    public float distance(int index1, int index2) {
        if (index1 >= count || index2 >= count) throw new IllegalArgumentException();

        if (index1 == index2) return 0.0F;
        return start[index1].distance(start[index2]);
    }

}
