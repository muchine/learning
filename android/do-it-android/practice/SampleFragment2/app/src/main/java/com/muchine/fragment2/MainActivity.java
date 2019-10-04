package com.muchine.fragment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements ListFragment.ImageSelectionCallback {

    private ListFragment listFragment;
    private ViewerFragment viewerFragment;

    int[] images = { R.drawable.dream01, R.drawable.dream02 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
    }

    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }

    private void initFragments() {
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.viewerFragment);
    }

}
