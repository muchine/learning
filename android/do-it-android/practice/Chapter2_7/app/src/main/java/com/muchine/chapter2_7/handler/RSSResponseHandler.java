package com.muchine.chapter2_7.handler;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

import com.muchine.chapter2_7.view.rss.RSSNewsAdapter;
import com.muchine.chapter2_7.view.rss.RSSNewsItem;

import java.util.List;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class RSSResponseHandler extends Handler {

    private final RSSNewsAdapter adapter;
    private final ProgressDialog dialog;

    public RSSResponseHandler(RSSNewsAdapter adapter, ProgressDialog dialog) {
        this.adapter = adapter;
        this.dialog = dialog;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        List<RSSNewsItem> items = (List<RSSNewsItem>) msg.obj;
        adapter.reset(items);
        adapter.notifyDataSetChanged();

        dialog.dismiss();
    }
}
