package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ActionBar01Activity extends AppCompatActivity {

    private TextView textView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar01);

        actionBar = getSupportActionBar();
        actionBar.setSubtitle("옵션바 살펴보기");

        textView = (TextView) findViewById(R.id.actionBar01Text);
    }

    public void onActionBar01ButtonClicked(View v) {
        actionBar.setLogo(R.drawable.home);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar01_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.actionbar01_menu_refresh:
                textView.setText("새로고침 메뉴를 선택했습니다");
                break;
            case R.id.actionbar01_menu_search:
                textView.setText("검색 메뉴를 선택했습니다");
                break;
            case R.id.actionbar01_menu_settings:
                textView.setText("설정 메뉴를 선택했습니다");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
