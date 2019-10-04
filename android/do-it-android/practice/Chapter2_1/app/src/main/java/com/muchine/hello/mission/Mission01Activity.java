package com.muchine.hello.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.muchine.hello.R;

public class Mission01Activity extends AppCompatActivity {

    private ImageView aboveImageView;
    private ImageView belowImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        aboveImageView = (ImageView) findViewById(R.id.aboveImageView);
        belowImageView = (ImageView) findViewById(R.id.belowImageView);
    }

    public void onUpClicked(View v) {
        aboveImageView.setVisibility(View.VISIBLE);
        belowImageView.setVisibility(View.INVISIBLE);

        Toast.makeText(getApplicationContext(), "위로", Toast.LENGTH_LONG).show();
    }

    public void onDownClicked(View v) {
        aboveImageView.setVisibility(View.INVISIBLE);
        belowImageView.setVisibility(View.VISIBLE);

        Toast.makeText(getApplicationContext(), "아래로", Toast.LENGTH_LONG).show();
    }

}
