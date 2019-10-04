package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonAnimationActivity extends AppCompatActivity {

    private TextView animationText;
    private Animation flowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_animation);

        animationText = (TextView) findViewById(R.id.animationText);
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
    }

    public void onStartAnimationButtonClicked(View v) {
        flowAnimation.setAnimationListener(new FlowAnimationListener());
        animationText.startAnimation(flowAnimation);
    }

    private final class FlowAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(getApplicationContext(), "애니메이션 종료됨", Toast.LENGTH_LONG).show();
        }

    }

}
