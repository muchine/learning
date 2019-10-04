package com.muchine.chapter2_4.ui.listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muchine.chapter2_4.R;

/**
 * Created by sejoonlim on 8/19/16.
 */
public class IconTextView extends LinearLayout {

    private ImageView icon;

    private TextView text01;

    private TextView text02;

    private TextView text03;

    public IconTextView(Context context, IconTextItem item) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        icon = (ImageView) findViewById(R.id.listViewIconItem);
        icon.setImageDrawable(item.getIcon());

        text01 = (TextView) findViewById(R.id.listViewDataItem01);
        text01.setText(item.getData(0));

        text02 = (TextView) findViewById(R.id.listViewDataItem02);
        text02.setText(item.getData(1));

        text03 = (TextView) findViewById(R.id.listViewDataItem03);
        text03.setText(item.getData(2));
    }

    public void setText(int index, String data) {
        switch (index) {
            case 0:
                text01.setText(data);
                break;
            case 1:
                text02.setText(data);
                break;
            case 2:
                text03.setText(data);
                break;
        }

        throw new IllegalArgumentException();
    }

    public void setIcon(Drawable icon) {
        this.icon.setImageDrawable(icon);
    }

}
