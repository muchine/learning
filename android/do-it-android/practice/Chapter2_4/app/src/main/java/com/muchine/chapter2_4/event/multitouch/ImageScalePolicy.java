package com.muchine.chapter2_4.event.multitouch;

/**
 * Created by sejoonlim on 8/25/16.
 */
public class ImageScalePolicy {

    private static final float DEFAULT_RATIO = 1.0F;

    private static float MAX_SCALE_RATIO = 5.0F;
    private static float MIN_SCALE_RATIO = 0.1F;

    private final float threshold = 3.0F;

    private final float from;
    private final float to;

    private final float ratio;

    public ImageScalePolicy(MotionPoint from, MotionPoint to) {
        this.from = from.distance(0, 1);
        this.to = to.distance(0, 1);

        this.ratio = calculateRatio();
    }

    public boolean isScalable() {
        return ratio != DEFAULT_RATIO;
    }

    public float ratio() {
        return ratio;
    }

    private boolean canScale() {
        return Math.abs(to - from) >= threshold;
    }

    private float calculateRatio() {
        if (!canScale()) return DEFAULT_RATIO;
        float ratio = calculateBaseRatio();

        if (isValidRatio(ratio)) return ratio;
        return DEFAULT_RATIO;
    }

    private float calculateBaseRatio() {
        if (from < to) {
            return 1 + (from / to * 0.05F);
        } else if (from > to) {
            return 1 - (to / from * 0.05F);
        }

        return DEFAULT_RATIO;
    }

    private boolean isValidRatio(float ratio) {
        return MIN_SCALE_RATIO < ratio && ratio < MAX_SCALE_RATIO;
    }

}
