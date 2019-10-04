package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActionBar02Activity extends AppCompatActivity {

    private TextView textView;
    private EditText searchEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar02);

        textView = (TextView) findViewById(R.id.actionBar02Text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar02_menu, menu);

        View v = menu.findItem(R.id.actionbar02_menu_search).getActionView();
        if (v != null) {
            searchEdit = (EditText) v.findViewById(R.id.actionBar02Edit);

            if (searchEdit != null) {
                searchEdit.setOnEditorActionListener(onSearchListener);
            }
        } else {
            Toast.makeText(getApplicationContext(), "ActionView is null.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    private TextView.OnEditorActionListener onSearchListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (keyEvent == null || keyEvent.getAction() == KeyEvent.ACTION_UP) {
                search();

                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            }

            return true;
        }
    };

    private void search() {
        String searchString = searchEdit.getEditableText().toString();
        Toast.makeText(this, "검색어 : " + searchString, Toast.LENGTH_SHORT).show();
    }

}
