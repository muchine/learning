package com.muchine.chapter2_4.event.multitouch.motion;

import android.view.MotionEvent;

import com.muchine.chapter2_4.event.multitouch.MotionPoint;
import com.muchine.chapter2_4.event.multitouch.Point;

/**
 * Created by sejoonlim on 8/25/16.
 */
public class DoubleTouchMotion extends TouchMotion {

    private final String TAG = getClass().getSimpleName();

    private MotionPoint start;

    private OnMoveListener onMoveListener;

    public DoubleTouchMotion(Point p1, Point p2) {
        this.start = new MotionPoint(p1, p2);
    }

    public DoubleTouchMotion(MotionEvent event) {
        this.start = new MotionPoint(event, 2);
    }

    @Override
    public void move(MotionEvent event) {
        if (touchCount() != event.getPointerCount()) return;

        MotionPoint end = new MotionPoint(event, 2);
        onMoveListener.onMove(start, end);

        this.start = end;
    }

    @Override
    public void up(MotionEvent event) {
        move(event);
    }

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

    @Override
    public int touchCount() {
        return 2;
    }
}
