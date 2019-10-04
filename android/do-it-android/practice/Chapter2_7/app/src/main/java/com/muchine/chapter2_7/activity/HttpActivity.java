package com.muchine.chapter2_7.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muchine.chapter2_7.R;
import com.muchine.chapter2_7.handler.HttpResponseHandler;
import com.muchine.chapter2_7.thread.HttpConnector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HttpActivity extends AppCompatActivity {

    @Bind(R.id.httpHost)
    EditText url;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);

        initHttpResponseHandler();
        initRequestButton();
    }

    private void initRequestButton() {
        Button button = (Button) findViewById(R.id.httpRequestButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpConnector connector = new HttpConnector(url.getText().toString(), handler);
                connector.start();
            }
        });
    }

    private void initHttpResponseHandler() {
        TextView text = (TextView) findViewById(R.id.httpResultText);
        handler = new HttpResponseHandler(text);
    }

}
