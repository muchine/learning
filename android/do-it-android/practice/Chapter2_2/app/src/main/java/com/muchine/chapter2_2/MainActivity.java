package com.muchine.chapter2_2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.muchine.chapter2_2.basic.parcelable.SimpleData;
import com.muchine.chapter2_2.basic.service.MyService;
import com.muchine.chapter2_2.domain.PDFLinker;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_SIMPLE_DATA = "data";
    public static final String PREF_ID = "Pref01";
    public static final int actMode = Activity.MODE_PRIVATE;

    private static final int REQUEST_CODE_ANOTHER = 1001;

    private EditText callnumber;
    private EditText pdfLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callnumber = (EditText) findViewById(R.id.callNumber);
        pdfLocation = (EditText) findViewById(R.id.pdfLocation);

        obtainSmsPermissionIfNecessary();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveCurrentState();
        showToast("onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        showToast("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

//        restoreFromSavedState();
//        showToast("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop");
    }

    public void onButton1Clicked(View v) {
        inflateLayout();
    }

    public void onNewActivityClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
        intent.putExtra(KEY_SIMPLE_DATA, new SimpleData(100, "Hello Android!"));

//        Intent intent = new Intent();
//        ComponentName name = new ComponentName("com.muchine.chapter2_2", "com.muchine.chapter2_2.AnotherActivity");
//        intent.setComponent(name);
        startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    }

    public void onCallButtonClicked(View v) {
        String data = callnumber.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(data));
        startActivity(intent);
    }

    public void onPdfButtonClicked(View v) {
        String filename = pdfLocation.getText().toString();

        PDFLinker linker = new PDFLinker(this, filename);
        linker.open();
    }

    public void onStartServiceButtonClicked(View v) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void onStopServiceButtonClicked(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != 1) return;

        if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            showToast("SMS 권한 거부됨");
            return;
        }

        showToast("SMS 권한을 사용자가 승인");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ANOTHER) handleAnotherActivityResult(resultCode, data);
    }

    private void obtainSmsPermissionIfNecessary() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            showToast("SMS 수신 권한 있음");
            return;
        }

        showToast("SMS 수신 권한 없음");
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
            showToast("SMS 권한 설명 필요함");
            return;
        }

        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECEIVE_SMS }, 1);
    }

    private void handleAnotherActivityResult(int resultCode, Intent intent) {
        String message = "onActivityResult method invoked. request code: " + REQUEST_CODE_ANOTHER
                + ", result code: " + resultCode;

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();

        if (resultCode != RESULT_OK) return;

        String name = intent.getExtras().getString("name");
        toast = Toast.makeText(getApplicationContext(), "result name: " + name, Toast.LENGTH_LONG);
        toast.show();
    }

    private void inflateLayout() {
        LinearLayout contentsLayout = (LinearLayout) findViewById(R.id.contentsLayout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button, contentsLayout, true);

        Button selectButton = (Button) findViewById(R.id.selectButton);
        final CheckBox allDay = (CheckBox) findViewById(R.id.allDay);

        selectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (allDay.isChecked()) {
                    allDay.setChecked(false);
                } else {
                    allDay.setChecked(true);
                }
            }

        });
    }

    private void saveCurrentState() {
        SharedPreferences myPrefs = getSharedPreferences(PREF_ID, actMode);
        SharedPreferences.Editor myEditor = myPrefs.edit();
        myEditor.putString("txtMsg", "My name is Mike");
        myEditor.commit();
    }

    private void restoreFromSavedState() {
        SharedPreferences myPrefs = getSharedPreferences(PREF_ID, actMode);
        if (myPrefs == null || !myPrefs.contains("txtMsg")) return;

        String myData = myPrefs.getString("txtMsg", "");
        showToast("Restored: " + myData);
    }

    private void clearMyPrefs() {
        SharedPreferences myPrefs = getSharedPreferences(PREF_ID, actMode);
        SharedPreferences.Editor myEditor = myPrefs.edit();
        myEditor.clear();
        myEditor.commit();
    }

    private void showToast(String methodName) {
        Toast.makeText(this, methodName + "...", Toast.LENGTH_SHORT).show();
    }

}
