package com.muchine.chapter2_5.ui.coverflow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.muchine.chapter2_5.R;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class ImageAdapter extends BaseAdapter {

    private int[] imageIds = {
            R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04, R.drawable.item05
    };

    private final Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView image = new ImageView(context);
        image.setImageResource(imageIds[i]);
        image.setLayoutParams(new CoverFlow.LayoutParams(500, 280));
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        drawable.setAntiAlias(true);

        return image;
    }
}
