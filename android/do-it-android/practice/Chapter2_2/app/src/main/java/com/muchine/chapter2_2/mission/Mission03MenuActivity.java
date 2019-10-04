package com.muchine.chapter2_2.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.muchine.chapter2_2.R;

public class Mission03MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03_menu);
    }

    public void onCustomerButtonClicked(View v) {
        returnToMainActivity("고객 관리");
    }

    public void onRevenueButtonClicked(View v) {
        returnToMainActivity("매출 관리");
    }

    public void onProductButtonClicked(View v) {
        returnToMainActivity("상품 관리");
    }

    private void returnToMainActivity(String menuName) {
        Intent intent = new Intent();
        intent.putExtra("name", menuName);

        setResult(RESULT_OK, intent);
        finish();
    }

}
