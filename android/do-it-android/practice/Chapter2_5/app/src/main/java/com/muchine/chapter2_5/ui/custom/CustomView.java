package com.muchine.chapter2_5.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by sejoonlim on 8/30/16.
 */
public class CustomView extends View {

    private final Paint paint;

    public CustomView(Context context) {
        super(context);

        this.paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(100, 100, 200, 200, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "MotionEvent.ACTION_DOWN: " + event.getX() + ", " + event.getY(), Toast.LENGTH_LONG);
        }

        return super.onTouchEvent(event);
    }
}
