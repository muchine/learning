package com.muchine.chapter2_4.ui.listview;

import android.graphics.drawable.Drawable;

/**
 * Created by sejoonlim on 8/19/16.
 */
public class IconTextItem implements Comparable {

    private Drawable icon;

    private String[] data;

    private boolean selectable = true;

    public IconTextItem(Drawable icon, String[] data) {
        this.icon = icon;
        this.data = data;
    }

    public IconTextItem(Drawable icon, String text1, String text2, String text3) {
        this.icon = icon;

        data = new String[3];
        data[0] = text1;
        data[1] = text2;
        data[2] = text3;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String[] getData() {
        return data;
    }

    public String getData(int index) {
        if (data == null || index > data.length) return null;
        return data[index];
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
