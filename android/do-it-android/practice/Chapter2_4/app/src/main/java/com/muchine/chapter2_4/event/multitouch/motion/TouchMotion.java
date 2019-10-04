package com.muchine.chapter2_4.event.multitouch.motion;

import android.view.MotionEvent;

import com.muchine.chapter2_4.event.multitouch.MotionPoint;

/**
 * Created by sejoonlim on 8/25/16.
 */
public abstract class TouchMotion {

    public abstract int touchCount();

    public abstract void move(MotionEvent event);

    public abstract void up(MotionEvent event);

    public interface OnMoveListener {
        void onMove(MotionPoint start, MotionPoint  end);
    }

}
