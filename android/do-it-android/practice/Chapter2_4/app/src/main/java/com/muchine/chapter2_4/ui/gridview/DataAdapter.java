package com.muchine.chapter2_4.ui.gridview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sejoonlim on 8/22/16.
 */
public class DataAdapter extends BaseAdapter {

    private Context context;

    private ArrayTable table;

    public static int oddColor = Color.rgb(225, 225, 225);
    public static int headColor = Color.rgb(12, 32, 158);

    private int selectedRow = -1;

    public DataAdapter(Context context) {
        super();
        this.context = context;
    }

    public DataAdapter(Context context, ArrayTable table) {
        this(context);
        this.table = table;
    }

    public int getNumColumns() {
        if (table == null) return 0;
        return table.getColumnCount();
    }

    public int getCount() {
        if (table == null) return 0;
        return table.getRowCount() * table.getColumnCount();
    }

    @Override
    public Object getItem(int i) {
        if (table == null) return null;

        int rowIndex = i / table.getColumnCount();
        int columnIndex = i % table.getColumnCount();

        return table.getCell(rowIndex, columnIndex);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (table == null) return null;

        Log.d("DataAdapter", "get view: " + i);
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        renderTopLine(container);
        renderItemContainer(container, i);
        renderBottomLine(container);

        return container;
    }

    private void renderTopLine(ViewGroup container) {
        View topLine = new View(context);
        topLine.setBackgroundColor(Color.LTGRAY);

        LinearLayout.LayoutParams topParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        topParams.height = 1;

        container.addView(topLine, topParams);
    }

    private void renderBottomLine(ViewGroup container) {
        View bottomLine = new View(context);
        bottomLine.setBackgroundColor(Color.LTGRAY);

        LinearLayout.LayoutParams bottomParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bottomParams.height = 1;

        container.addView(bottomLine, bottomParams);
    }

    private void renderItemContainer(ViewGroup rootContainer, int position) {
        LinearLayout itemContainer = new LinearLayout(context);
        itemContainer.setOrientation(LinearLayout.HORIZONTAL);

        renderLeftLine(itemContainer);
        renderTextView(itemContainer, position);
        renderRightLine(itemContainer);

        LinearLayout.LayoutParams itemContainerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rootContainer.addView(itemContainer, itemContainerParams);
    }

    private void renderLeftLine(ViewGroup itemContainer) {
        View leftLine = new View(context);
        leftLine.setBackgroundColor(Color.LTGRAY);

        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.FILL_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftParams.width = 1;

        itemContainer.addView(leftLine, leftParams);
    }

    private void renderRightLine(ViewGroup itemContainer) {
        View rightLine = new View(context);
        rightLine.setBackgroundColor(Color.LTGRAY);

        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.FILL_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rightParams.width = 1;

        itemContainer.addView(rightLine, rightParams);
    }

    private void renderTextView(ViewGroup itemContainer, int position) {
        TextView itemView = new TextView(context);
        itemView.setBackgroundColor(Color.TRANSPARENT);

        RelativeLayout.LayoutParams itemParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 100);
        itemParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        itemContainer.addView(itemView, itemParams);

        int rowIndex = position / table.getColumnCount();
        int columnIndex = position % table.getColumnCount();
        String item = table.getCell(rowIndex, columnIndex);

        itemView.setText(item);
        itemView.setGravity(Gravity.LEFT);
        itemView.setTextColor(Color.BLACK);

        if (rowIndex == 0) {
            itemView.setTextColor(Color.WHITE);
            itemView.setBackgroundColor(headColor);
            itemView.setGravity(Gravity.CENTER);
        } else if (rowIndex == getSelectedRow()) {
            itemView.setBackgroundColor(Color.YELLOW);
        } else if ((rowIndex & 1) == 0) {
            itemView.setBackgroundColor(oddColor);
        } else {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }

    public ArrayTable getTable() {
        return table;
    }

    public void setTable(ArrayTable table) {
        this.table = table;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
}
