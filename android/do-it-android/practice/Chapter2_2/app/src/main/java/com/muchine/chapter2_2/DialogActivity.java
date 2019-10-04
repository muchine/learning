package com.muchine.chapter2_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    private TextView dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initializeWidgets();
    }

    public void onShowDialogButtonClicked(View v) {
        AlertDialog dialog = createDialogBox();
        dialog.show();
    }

    private AlertDialog createDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "예 버튼이 눌렸습니다. " + Integer.toString(i);
                dialogText.setText(message);
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "취소 버튼이 눌렸습니다. " + Integer.toString(i);
                dialogText.setText(message);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "아니오 버튼이 눌렸습니다. " + Integer.toString(i);
                dialogText.setText(message);
            }
        });

        AlertDialog dialog = builder.create();
        return dialog;
    }


    private void initializeWidgets() {
        dialogText = (TextView) findViewById(R.id.dialogText);
    }

}
