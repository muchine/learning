package com.muchine.chapter2_4.ui.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sejoonlim on 8/19/16.
 */
public class IconTextListAdapter extends BaseAdapter {

    private Context context;

    private List<IconTextItem> items = new ArrayList<>();

    public IconTextListAdapter(Context context) {
        this.context = context;
    }

    public void addItem(IconTextItem item) {
        items.add(item);
    }

    public void setItems(List<IconTextItem> items) {
        this.items = items;
    }

    public boolean allItemSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        if (position >= items.size()) return false;
        return items.get(position).isSelectable();
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
        if (view == null) return new IconTextView(context, items.get(i));

        IconTextView itemView = (IconTextView) view;
        itemView.setIcon(items.get(i).getIcon());
        itemView.setText(0, items.get(i).getData(0));
        itemView.setText(1, items.get(i).getData(1));
        itemView.setText(2, items.get(i).getData(2));

        return itemView;
    }

}
