package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class PageSlidingActivity extends AppCompatActivity {

    private boolean isPageOpen;

    private Animation translateLeftAnimation;
    private Animation translateRightAnimation;

    private LinearLayout slidingPage01;
    private Button slidingPageButton01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sliding);

        slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01);
        slidingPageButton01 = (Button) findViewById(R.id.slidingPageButton01);

        translateLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener listener = new SlidingPageAnimationListener();
        translateLeftAnimation.setAnimationListener(listener);
        translateRightAnimation.setAnimationListener(listener);
    }

    public void onSlidingPageButton01Clicked(View v) {
        if (isPageOpen) {
            slidingPage01.startAnimation(translateRightAnimation);
        } else {
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnimation);
        }
    }

    private final class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                slidingPage01.setVisibility(View.INVISIBLE);

                slidingPageButton01.setText("Open");
                isPageOpen = false;
            } else {
                slidingPageButton01.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
