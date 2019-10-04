package com.muchine.chapter2_7.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.muchine.chapter2_7.R;
import com.muchine.chapter2_7.thread.SocketConnector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocketActivity extends AppCompatActivity {

    @Bind(R.id.socketInput)
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);

        initConnectButton();
    }

    private void initConnectButton() {
        Button button = (Button) findViewById(R.id.socketButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String host = input.getText().toString();

                SocketConnector connector = new SocketConnector(host);
                connector.start();
            }
        });
    }

}
