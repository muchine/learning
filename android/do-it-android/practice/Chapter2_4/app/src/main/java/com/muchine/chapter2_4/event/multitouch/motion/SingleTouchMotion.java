package com.muchine.chapter2_4.event.multitouch.motion;

import android.util.Log;
import android.view.MotionEvent;

import com.muchine.chapter2_4.event.multitouch.MotionPoint;
import com.muchine.chapter2_4.event.multitouch.Point;

/**
 * Created by sejoonlim on 8/25/16.
 */
public class SingleTouchMotion extends TouchMotion {

    private final String TAG = getClass().getSimpleName();

    private Point point;

    private OnMoveListener onMoveListener;

    public SingleTouchMotion(MotionEvent event) {
        this.point = new Point(event);
    }

    @Override
    public void move(MotionEvent event) {
        if (touchCount() != event.getPointerCount()) return;

        Point end = new Point(event);

        Log.d(TAG, "ACTION MOVE: " + point.offsetX(end) + ", " + point.offsetY(end));
        onMoveListener.onMove(new MotionPoint(point), new MotionPoint(end));

        this.point = end;
    }

    @Override
    public void up(MotionEvent event) {
        if (touchCount() != event.getPointerCount()) return;

        Point end = new Point(event);
        onMoveListener.onMove(new MotionPoint(point), new MotionPoint(end));
    }

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

    @Override
    public int touchCount() {
        return 1;
    }

}
