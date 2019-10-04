package com.muchine.chapter2_5.ui.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.muchine.chapter2_5.R;

/**
 * Created by sejoonlim on 8/30/16.
 */
public class CustomViewDrawables extends View {

    private final int windowWidth;

    private final int windowHeight;

    private final ShapeDrawable upperDrawable;

    private final ShapeDrawable lowerDrawable;

    public CustomViewDrawables(Context context) {
        super(context);

        Point point = newWindowSizePoint(context);
        this.windowWidth = point.x;
        this.windowHeight = point.y;

        Resources res = getResources();
        int blackColor = res.getColor(R.color.color01);
        int grayColor = res.getColor(R.color.color02);
        int darkGrayColor = res.getColor(R.color.color03);

        this.upperDrawable = newUpperDrawable(grayColor, blackColor);
        this.lowerDrawable = newLowerDrawable(blackColor, darkGrayColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        upperDrawable.draw(canvas);
        lowerDrawable.draw(canvas);

        drawPaths(canvas);
    }

    private Point newWindowSizePoint(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point sizePoint = new Point();
        display.getSize(sizePoint);

        return sizePoint;
    }

    private ShapeDrawable newUpperDrawable(int startColor, int endColor) {
        ShapeDrawable drawable = new ShapeDrawable();

        RectShape rectangle = new RectShape();
        rectangle.resize(windowWidth, windowHeight * 2 / 3);

        drawable.setShape(rectangle);
        drawable.setBounds(0, 0, windowWidth, windowHeight * 2 / 3);

        LinearGradient gradient = new LinearGradient(0, 0, 0, windowHeight * 2 / 3, startColor, endColor, Shader.TileMode.CLAMP);

        Paint paint = drawable.getPaint();
        paint.setShader(gradient);

        return drawable;
    }

    private ShapeDrawable newLowerDrawable(int startColor, int endColor) {
        ShapeDrawable drawable = new ShapeDrawable();

        RectShape rectangle = new RectShape();
        rectangle.resize(windowWidth, windowHeight * 1 / 3);

        drawable.setShape(rectangle);
        drawable.setBounds(0, windowHeight * 2 / 3, windowWidth, windowHeight);

        LinearGradient gradient =new LinearGradient(0, 0, 0, windowHeight * 1 / 3, startColor, endColor, Shader.TileMode.CLAMP);

        Paint paint = drawable.getPaint();
        paint.setShader(gradient);

        return drawable;
    }

    private void drawPaths(Canvas canvas) {
        Path path = newDefaultPath();
        Paint paint = newDefaultPaint();

        paint.setColor(Color.YELLOW);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeJoin(Paint.Join.MITER);

        canvas.drawPath(path, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        path.offset(30, 120);
        canvas.drawPath(path, paint);

        paint.setColor(Color.CYAN);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeJoin(Paint.Join.BEVEL);

        path.offset(30, 120);
        canvas.drawPath(path, paint);
    }

    private Path newDefaultPath() {
        Path path = new Path();
        path.moveTo(20, 20);
        path.lineTo(120, 20);
        path.lineTo(160, 90);
        path.lineTo(180, 80);
        path.lineTo(200, 120);

        return path;
    }

    private Paint newDefaultPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16.0F);

        return paint;
    }

}
