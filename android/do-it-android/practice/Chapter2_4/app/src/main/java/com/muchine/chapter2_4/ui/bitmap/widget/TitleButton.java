package com.muchine.chapter2_4.ui.bitmap.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import com.muchine.chapter2_4.R;

/**
 * Created by sejoonlim on 8/17/16.
 */
public class TitleButton extends Button {

    protected Paint paint;

    private Context context;

    private int defaultColor = 0xffffffff;

    private float defaultSize = 20F;

    private float defaultScaleX = 1.0F;

    private Typeface defaultTypeface = Typeface.DEFAULT_BOLD;

    private String titleText = "";

    private boolean paintChanged = false;

    public TitleButton(Context context) {
        super(context);

        this.context = context;
        init();
    }

    public TitleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        paintChanged = true;
    }

    public float getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(float defaultSize) {
        this.defaultSize = defaultSize;
        paintChanged = true;
    }

    public float getDefaultScaleX() {
        return defaultScaleX;
    }

    public void setDefaultScaleX(float defaultScaleX) {
        this.defaultScaleX = defaultScaleX;
        paintChanged = true;
    }

    public Typeface getDefaultTypeface() {
        return defaultTypeface;
    }

    public void setDefaultTypeface(Typeface defaultTypeface) {
        this.defaultTypeface = defaultTypeface;
        paintChanged = true;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    private void init() {
        init(R.drawable.title_button);
    }

    private void init(int backgroundId) {
        setBackgroundResource(backgroundId);

        paint = new Paint();
        paint.setAntiAlias(true);
        setPaint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(context, titleText, Toast.LENGTH_LONG).show();
                break;
        }

        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int curWidth = getWidth();
        int curHeight = getHeight();

        if (paintChanged) {
            setPaint();
        }

        Rect bounds = new Rect();
        paint.getTextBounds(titleText, 0, titleText.length(), bounds);
        float textWidth = ((float) curWidth - bounds.width()) / 2.0F;
        float textHeight = ((float) (curHeight - 4) + bounds.height()) / 2.0F - 1.0F;

        canvas.drawText(titleText, textWidth, textHeight, paint);
    }

    protected void setPaint() {
        paint.setColor(defaultColor);
        paint.setTextScaleX(defaultScaleX);
        paint.setTextSize(defaultSize);
        paint.setTypeface(defaultTypeface);
    }

}
