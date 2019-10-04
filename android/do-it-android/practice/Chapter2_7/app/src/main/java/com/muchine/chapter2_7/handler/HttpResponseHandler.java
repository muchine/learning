package com.muchine.chapter2_7.handler;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class HttpResponseHandler extends Handler {

    private final TextView text;

    public HttpResponseHandler(TextView text) {
        this.text = text;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        text.setText((String) msg.obj);
    }
}
