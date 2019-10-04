package com.muchine.chapter2_2.domain;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.muchine.chapter2_2.MainActivity;

import java.io.File;

/**
 * Created by sejoonlim on 8/6/16.
 */
public class PDFLinker {

    private final MainActivity activity;
    private final String filename;

    public PDFLinker(MainActivity activity, String filename) {
        this.activity = activity;
        this.filename = filename;
    }

    public void open() {
        if (filename.isEmpty()) {
            Toast.makeText(activity.getApplicationContext(), "PDF 파일명을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        openPDF(filename.trim());
    }

    private void openPDF(String contentPath) {
        File file = new File(contentPath);

        if (!file.exists()) {
            Toast.makeText(activity.getApplicationContext(), "PDF 파일이 없습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = newIntent(file);
        try {
            activity.startActivity(intent);
        } catch(ActivityNotFoundException e) {
            Toast.makeText(activity, "PDF 파일을 보기 위한 뷰어 앱이 없습니다", Toast.LENGTH_SHORT).show();
        }
    }

    private Intent newIntent(File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return intent;
    }

}
