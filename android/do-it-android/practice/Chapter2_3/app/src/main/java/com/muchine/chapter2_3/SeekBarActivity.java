package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity {

    private View panel;

    private SeekBar seekBar;

    private TextView seekText;

    private int brightness = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        panel = findViewById(R.id.seekPanel01);
        seekText = (TextView) findViewById(R.id.seekText);

        initSeekButton();
        initSeekBar();
    }

    private void initSeekButton() {
        Button button = (Button) findViewById(R.id.seekShowButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPanel();
            }
        });
    }

    private void initSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.seekBar01);
        seekBar.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());
    }

    private void showPanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        seekBar.setProgress(this.brightness);
        panel.setVisibility(View.VISIBLE);
        panel.startAnimation(animation);
    }

    private void hidePanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        panel.startAnimation(animation);
        panel.setVisibility(View.GONE);
    }

    private void setBrightness(int value) {
        if (value < 10) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }

        brightness = value;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) value / 100;
        getWindow().setAttributes(params);
    }

    private final class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            setBrightness(i);
            seekText.setText("밝기 수준 : " + i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            hidePanel();
        }
    }

}
