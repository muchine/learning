package com.muchine.chapter2_7.view.rss;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muchine.chapter2_7.R;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class RSSNewsItemView extends LinearLayout {

    private static final String TAG = "RSSNewsItemView";

    public RSSNewsItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rss_item, this, true);
    }

    public RSSNewsItemView(Context context, RSSNewsItem item) {
        this(context);

        render(item);
    }

    public void render(RSSNewsItem item) {
        ImageView icon = (ImageView) findViewById(R.id.rssIcon);
        icon.setImageDrawable(item.getIcon());

        renderSummary(item);
        renderContent(item);
    }

    private void renderSummary(RSSNewsItem item) {
        TextView title = (TextView) findViewById(R.id.rssTitle);
        title.setText(item.getTitle());

        TextView pubDate = (TextView) findViewById(R.id.rssPubDate);
        pubDate.setText(item.getPublishedDate());

        TextView category = (TextView) findViewById(R.id.rssCategory);
        if (item.getCategory() != null) category.setText(item.getCategory());
    }

    private void renderContent(RSSNewsItem item) {
        WebView contentView = (WebView) findViewById(R.id.rssContent);
        Log.d(TAG, "RSSNewsItemView() called.");
        String content = item.getDescription().replace("\"//", "\"http://");
        contentView.loadDataWithBaseURL("http://localhost/", content, "text/html", "utf-8", null);
    }

}
