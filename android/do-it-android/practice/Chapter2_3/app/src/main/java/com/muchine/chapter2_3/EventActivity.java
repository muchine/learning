package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    private GestureDetector gestureDetector = null;
    private TextView eventTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        initGestureDetector();
        initEventTextView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector == null)
            return super.onTouchEvent(event);

        return gestureDetector.onTouchEvent(event);
    }

    private void initGestureDetector() {
        gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                eventTextView.append("\nonFling \n\tx = " + velocityX + "\n\ty = " + velocityY);
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                eventTextView.append("\nonScroll \n\tx = " + distanceX + "\n\ty = " + distanceY);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
    }

    private void initEventTextView() {
        eventTextView = (TextView) findViewById(R.id.eventTextView);

        eventTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                eventTextView.append("\nonLongClick: " + view.toString());
                return true;
            }
        });

        eventTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                eventTextView.append("\nonFocusChange, hasFocus: " + hasFocus);
            }
        });
    }
}
