package com.muchine.chapter2_7.view.rss;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class RSSNewsAdapter extends BaseAdapter {

    private final Context context;

    private final List<RSSNewsItem> items = new ArrayList<>();

    public RSSNewsAdapter(Context context) {
        this.context = context;
    }

    public void reset(List<RSSNewsItem> newItems) {
        items.clear();
        items.addAll(newItems);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RSSNewsItemView itemView = (RSSNewsItemView) view;
        if (itemView == null) itemView = new RSSNewsItemView(context);

        itemView.render(items.get(i));
        return itemView;
    }
}
