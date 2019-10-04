package com.muchine.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    private MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }

    public void onGoMenuButtonClicked() {
        getFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
    }

    public void onGoMainButtonClicked() {
        getFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
    }

    private void initWidgets() {
        mainFragment = (MainFragment) getFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();
    }

}
