package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int currentId = item.getItemId();
        switch(currentId) {
            case R.id.option_menu_refresh:
                showToast("새로고침");
                break;
            case R.id.option_menu_search:
                showToast("검색");
                break;
            case R.id.option_menu_settings:
                showToast("설정");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showToast(String menuName) {
        Toast.makeText(this, menuName + " 메뉴가 선택되었습니다.", Toast.LENGTH_LONG).show();
    }
}
