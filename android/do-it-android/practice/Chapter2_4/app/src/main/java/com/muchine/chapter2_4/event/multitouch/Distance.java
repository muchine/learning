package com.muchine.chapter2_4.event.multitouch;

/**
 * Created by sejoonlim on 8/25/16.
 */
public class Distance {

    private Point c1;
    private Point c2;

    public Distance(Point c1, Point c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public float offsetX() {
        return c1.x() - c2.x();
    }

    public float offsetY() {
        return c1.y() - c2.y();
    }

    public float distance() {
        return new Double(Math.sqrt(new Float(offsetX() * offsetX() + offsetY() * offsetY()).doubleValue())).floatValue();
    }
}
