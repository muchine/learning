package com.muchine.chapter2_5.ui.coverflow;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class CoverFlow extends Gallery {

    public static final int MAX_ROTATION_ANGLE = 55;
    public static final int MAX_ZOOM_RATIO = -60;

    private final Camera camera = new Camera();

    private int centerPoint;

    public CoverFlow(Context context) {
        super(context);
        init();
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoverFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setStaticTransformationsEnabled(true);
    }

    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    private int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerPoint = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        int center = getCenterOfView(child);
        int width = child.getWidth();

        int rotationAngle = 0;
        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);

        rotationAngle = rotationAngle(center, width);
        transformImageBitmap((ImageView) child, t, rotationAngle);

        return true;
    }

    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle) {
        camera.save();

        Matrix matrix = t.getMatrix();
        int height = child.getLayoutParams().height;
        int width = child.getLayoutParams().width;
        int rotation = Math.abs(rotationAngle);

        camera.translate(0F, 0F, 100F);

        if (rotation < MAX_ROTATION_ANGLE) {
            float zoomAmount = (float) (MAX_ZOOM_RATIO + (rotation * 1.5));
            camera.translate(0F, 0F, zoomAmount);
        }

        camera.rotateY(rotationAngle);
        camera.getMatrix(matrix);

        matrix.preTranslate(-(width / 2), -(height / 2));
        matrix.postTranslate(width / 2, height / 2);

        camera.restore();
    }

    private int rotationAngle(int center, int width) {
        if (center == centerPoint) return 0;

        int distance = centerPoint - center;
        float ratio = (float) distance / width;

        int angle = (int) ratio * MAX_ROTATION_ANGLE;
        if (Math.abs(angle) > MAX_ROTATION_ANGLE) {
            angle = angle < 0 ? -MAX_ROTATION_ANGLE : MAX_ROTATION_ANGLE;
        }

        return angle;
    }

}
