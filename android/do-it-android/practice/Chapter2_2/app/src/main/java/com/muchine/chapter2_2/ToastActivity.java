package com.muchine.chapter2_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {

    private EditText xOffsetEdit;
    private EditText yOffsetEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        initializeWidgets();
    }

    public void onShowToastButtonClicked(View v) {
        showCustomizedToast();
        showToastWithOffset();

    }

    private void showToastWithOffset() {
        try {
            Toast toast = Toast.makeText(this, "Hello Android!", Toast.LENGTH_LONG);

            int xOffset = Integer.valueOf(xOffsetEdit.getText().toString());
            int yOffset = Integer.valueOf(yOffsetEdit.getText().toString());
            toast.setGravity(Gravity.CENTER, xOffset, yOffset);

            toast.show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showCustomizedToast() {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toastLayoutRoot));
        TextView text = (TextView) layout.findViewById(R.id.toastText);
        text.setText("Hello My Android!");

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setView(layout);
        toast.show();
    }

    private void initializeWidgets() {
        xOffsetEdit = (EditText) findViewById(R.id.xOffsetEdit);
        yOffsetEdit = (EditText) findViewById(R.id.yOffsetEdit);
    }

}
