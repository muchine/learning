package com.muchine.chapter2_5.ui.graphics.pen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class PenDataAdapter extends BaseAdapter {

    public static final int COLUMN_COUNT = 5;
    public static final int ROW_COUNT = 3;

    private static final int AREA_WIDTH = 10;
    private static final int AREA_HEIGHT = 20;

    private static final int[] pens = new int[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 20
    };

    private final Context context;

    private OnPenSelectionListener onPenSelectionListener;

    public PenDataAdapter(Context context) {
        this.context = context;
    }

    public void setOnPenSelectionListener(OnPenSelectionListener onPenSelectionListener) {
        this.onPenSelectionListener = onPenSelectionListener;
    }

    @Override
    public int getCount() {
        return ROW_COUNT * COLUMN_COUNT;
    }

    @Override
    public Object getItem(int i) {
        return pens[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int rowIndex = i / ROW_COUNT;
        int columnIndex = i % ROW_COUNT;
        Log.d("PenDataAdapter", "Index: " + rowIndex + ", " + columnIndex);


        Drawable drawable = newButtonDrawable(i);
        Button button = newButton(drawable, i);

        button.setTag(pens[i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPenSelectionListener == null) return;
                onPenSelectionListener.onPenSelected((int) view.getTag());
            }
        });

        return button;
    }

    private Button newButton(Drawable drawable, int i) {
        GridView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.MATCH_PARENT);

        Button button = new Button(context);
        button.setText(" ");
        button.setLayoutParams(params);
        button.setPadding(4, 4, 4, 4);
        button.setBackgroundDrawable(drawable);
        button.setHeight(120);
        button.setTag(pens[i]);

        return button;
    }

    private Drawable newButtonDrawable(int i) {
        Bitmap bitmap = Bitmap.createBitmap(AREA_WIDTH, AREA_HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);

        drawArea(canvas);
        drawLine(canvas, i);

        return new BitmapDrawable(context.getResources(), bitmap);
    }

    private void drawArea(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, AREA_WIDTH, AREA_HEIGHT, paint);
    }

    private void drawLine(Canvas canvas, int i) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float) pens[i]);
        canvas.drawLine(0, AREA_HEIGHT / 2, AREA_WIDTH - 1, AREA_HEIGHT / 2, paint);
    }

    public interface OnPenSelectionListener {
        void onPenSelected(int penWidth);
    }

}
