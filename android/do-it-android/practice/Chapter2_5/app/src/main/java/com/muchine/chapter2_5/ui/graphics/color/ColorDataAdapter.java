package com.muchine.chapter2_5.ui.graphics.color;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by sejoonlim on 8/31/16.
 */
public class ColorDataAdapter extends BaseAdapter {

    private static final int[] colors = new int[]{
            0xff000000, 0xff00007f, 0xff0000ff, 0xff007f00, 0xff007f7f, 0xff00ff00, 0xff00ff7f,
            0xff00ffff, 0xff7f007f, 0xff7f00ff, 0xff7f7f00, 0xff7f7f7f, 0xffff0000, 0xffff007f,
            0xffff00ff, 0xffff7f00, 0xffff7f7f, 0xffff7fff, 0xffffff00, 0xffffff7f, 0xffffffff
    };

    public static final int ROW_COUNT = 3;
    public static final int COLUMN_COUNT = 7;

    private final Context context;

    private OnColorSelectionListener onColorSelectionListener;

    public ColorDataAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setOnColorSelectionListener(OnColorSelectionListener onColorSelectionListener) {
        this.onColorSelectionListener = onColorSelectionListener;
    }

    @Override
    public int getCount() {
        return ROW_COUNT * COLUMN_COUNT;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int rowIndex = i / ROW_COUNT;
        int columnIndex = i % COLUMN_COUNT;
        Log.d(getClass().getSimpleName(), "Index: " + rowIndex + ", " + columnIndex);

        GridView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.MATCH_PARENT);

        Button item = new Button(context);
        item.setText(" ");
        item.setLayoutParams(params);
        item.setPadding(4, 4, 4, 4);
        item.setBackgroundColor(colors[i]);
        item.setHeight(64);
        item.setTag(colors[i]);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onColorSelectionListener == null) return;
                onColorSelectionListener.onColorSelected((int) view.getTag());
            }
        });

        return item;
    }

    public interface OnColorSelectionListener {
        void onColorSelected(int color);
    }

}
