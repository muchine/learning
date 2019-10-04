package com.muchine.chapter2_7.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muchine.chapter2_7.R;
import com.muchine.chapter2_7.handler.RSSResponseHandler;
import com.muchine.chapter2_7.thread.RSSConnector;
import com.muchine.chapter2_7.view.rss.RSSNewsAdapter;
import com.muchine.chapter2_7.view.rss.RSSNewsItem;
import com.muchine.chapter2_7.view.rss.RSSNewsView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RssActivity extends AppCompatActivity {

    private static final String TAG = "RssActivity";
    private static final String DEFAULT_URL = "http://api.sbs.co.kr/xml/news/rss.jsp?pmDiv=entertainment";

    @Bind(R.id.rssUrl)
    EditText host;

    @Bind(R.id.rssList)
    RSSNewsView rssList;

    private final RSSNewsAdapter adapter = new RSSNewsAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss);
        ButterKnife.bind(this);

        host.setText(DEFAULT_URL);

        initRSSList();
        initShowButton();
    }

    private void initRSSList() {
        rssList.setAdapter(adapter);
        rssList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "Item Clicked...");

                RSSNewsItem item = (RSSNewsItem) adapter.getItem(i);
                Toast.makeText(getApplicationContext(), "Selected: " + item.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initShowButton() {
        Button button = (Button) findViewById(R.id.rssShowButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });
    }

    private void connect() {
        ProgressDialog dialog = ProgressDialog.show(this, "RSS Refresh", "RSS 정보 업데이트 중...", true, true);

        Handler handler = new RSSResponseHandler(adapter, dialog);
        RSSConnector connector = new RSSConnector(host.getText().toString(), handler);

        connector.start();
    }

}
