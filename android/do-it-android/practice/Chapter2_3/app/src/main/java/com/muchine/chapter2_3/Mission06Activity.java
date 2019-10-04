package com.muchine.chapter2_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Mission06Activity extends AppCompatActivity {

    private Button panelButton;
    private Button inputButton;

    private EditText inputEdit;
    private View panel;
    private WebView webView;

    private boolean isPanelOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission06);

        panel = findViewById(R.id.mission06Panel);
        inputEdit = (EditText) findViewById(R.id.mission06InputEdit);
        webView = (WebView) findViewById(R.id.mission06WebView);

        initPanelButton();
        initInputButton();
    }

    private void initPanelButton() {
        panelButton = (Button) findViewById(R.id.mission06PanelButton);
        panelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPanelOpen) {
                    closePanel();
                } else {
                    openPanel();
                }
            }
        });
    }

    private void initInputButton() {
        inputButton = (Button) findViewById(R.id.mission06InputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = inputEdit.getText().toString();
                webView.loadUrl(url);

                closePanel();
            }
        });
    }

    private void closePanel() {
        panel.setVisibility(View.INVISIBLE);
        isPanelOpen = false;

        panelButton.setText("패널 열기");
    }

    private void openPanel() {
        panel.setVisibility(View.VISIBLE);
        isPanelOpen = true;

        panelButton.setText("패널 닫기");
    }

}
