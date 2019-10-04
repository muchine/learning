package com.muchine.chapter2_3;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarActivity extends AppCompatActivity {

    public static final int PROGRESS_DIALOG = 1001;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        initProgressBar();
        initImageView();
        initTextViews();
        initButtons();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case PROGRESS_DIALOG:
                return initDialog();
        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.progress_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) return true;

        return super.onOptionsItemSelected(item);
    }

    private ProgressDialog initDialog() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("데이터를 확인하는 중입니다.");

        return dialog;
    }

    private void initProgressBar() {
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar01);
        bar.setIndeterminate(false);
        bar.setMax(100);
        bar.setProgress(80);
    }

    private void initImageView() {
        ImageView icon = (ImageView) findViewById(R.id.progressIconItem);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.apple);
        icon.setImageDrawable(drawable);
    }

    private void initTextViews() {
        TextView name = (TextView) findViewById(R.id.progressDataItem01);
        name.setText("사과");

        TextView progress = (TextView) findViewById(R.id.progressDataItem02);
        progress.setText("80%");
    }

    private void initButtons() {
        Button show = (Button) findViewById(R.id.progressShowButton);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(PROGRESS_DIALOG);
            }
        });

        Button close = (Button) findViewById(R.id.progressCloseButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) dialog.dismiss();
            }
        });
    }

}
