package com.muchine.chapter2_3.ui.flipper;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.muchine.chapter2_3.R;

/**
 * Created by sejoonlim on 8/9/16.
 */
public class ScreenViewFlipper extends LinearLayout implements View.OnTouchListener {

    public static int countIndexes = 3;

    private float downX;
    private float upX;

    private int currentIndex = 0;
    private LinearLayout flipperButtonLayout;
    private ImageView[] indexButtons;
    private View[] views;
    private ViewFlipper flipper;

    public ScreenViewFlipper(Context context) {
        super(context);
        init(context);
    }

    public ScreenViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(0xffbfbfbf);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.screenview, this, true);

        flipperButtonLayout = (LinearLayout) findViewById(R.id.flipperButtonLayout);

        initFlipper();

        renderIndexButtons(context);
        renderTextViews(context);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view != flipper) return false;

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            downX = motionEvent.getX();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            upX = motionEvent.getX();

            if (upX < downX) {
                flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.wallpaper_open_enter));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.wallpaper_open_exit));

                if (currentIndex < (countIndexes - 1)) {
                    flipper.showNext();

                    currentIndex++;
                    updateIndexes();
                }
            } else if (upX > downX) {
                flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_right_out));

                if (currentIndex > 0) {
                    flipper.showPrevious();

                    currentIndex--;
                    updateIndexes();
                }
            }
        }

        return true;
    }

    private void initFlipper() {
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        flipper.setOnTouchListener(this);
    }

    private void renderIndexButtons(Context context) {
        indexButtons = new ImageView[countIndexes];

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = 50;

        for (int i = 0; i < countIndexes; i++) {
            indexButtons[i] = new ImageView(context);

            indexButtons[i].setPadding(10, 10, 10, 10);
            flipperButtonLayout.addView(indexButtons[i], params);
        }

        updateIndexes();
    }

    private void renderTextViews(Context context) {
        views = new TextView[countIndexes];

        for (int i = 0; i < countIndexes; i++) {
            TextView currentView = new TextView(context);
            currentView.setText("View #" + i);
            currentView.setTextColor(Color.RED);
            currentView.setTextSize(32);
            views[i] = currentView;

            flipper.addView(views[i]);
        }
    }

    private void updateIndexes() {
        for (int i = 0; i < countIndexes; i++) {
            if (i == currentIndex) {
                indexButtons[i].setImageResource(R.drawable.green);
            } else {
                indexButtons[i].setImageResource(R.drawable.white);
            }
        }
    }

}
