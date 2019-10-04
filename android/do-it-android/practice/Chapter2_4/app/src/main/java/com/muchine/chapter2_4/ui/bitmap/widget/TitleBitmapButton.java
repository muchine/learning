package com.muchine.chapter2_4.ui.bitmap.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.muchine.chapter2_4.R;

/**
 * Created by sejoonlim on 8/17/16.
 */
public class TitleBitmapButton extends TitleButton {

    public static final int BITMAP_ALIGN_CENTER = 0;

    public static final int BITMAP_ALIGN_LEFT = 1;

    public static final int BITMAP_ALIGN_RIGHT = 2;

    private int iconStatus;

    private Bitmap iconNormalBitmap;

    private Bitmap iconClickedBitmap;

    private int bitmapAlign = BITMAP_ALIGN_CENTER;

    private int bitmapPadding = 10;

    public TitleBitmapButton(Context context) {
        super(context);

        init();
    }

    public TitleBitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public void setIconBitmap(Bitmap iconNormal, Bitmap iconClicked) {
        iconNormalBitmap = iconNormal;
        iconClickedBitmap = iconClicked;
    }

    public int getBitmapAlign() {
        return bitmapAlign;
    }

    public void setBitmapAlign(int bitmapAlign) {
        this.bitmapAlign = bitmapAlign;
    }

    public int getBitmapPadding() {
        return bitmapPadding;
    }

    public void setBitmapPadding(int bitmapPadding) {
        this.bitmapPadding = bitmapPadding;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.title_bitmap_button_normal);
                iconStatus = 0;
                break;
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.title_bitmap_button_clicked);
                iconStatus = 1;
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

        Bitmap iconBitmap = iconNormalBitmap;
        if (iconStatus == 1) {
            iconBitmap = iconClickedBitmap;
        }

        if (iconBitmap != null) {
            int iconWidth = iconBitmap.getWidth();
            int iconHeight = iconBitmap.getHeight();
            int bitmapX = 0;
            if (bitmapAlign == BITMAP_ALIGN_CENTER) {
                bitmapX = (curWidth - iconWidth) / 2;
            } else if (bitmapAlign == BITMAP_ALIGN_LEFT) {
                bitmapX = bitmapPadding;
            } else if (bitmapAlign == BITMAP_ALIGN_RIGHT) {
                bitmapX = curWidth - bitmapPadding;
            }

            canvas.drawBitmap(iconBitmap, bitmapX, (curHeight - iconHeight) / 2, super.paint);
        }
    }

    private void init() {
        setBackgroundResource(R.drawable.title_bitmap_button_normal);
    }

}
