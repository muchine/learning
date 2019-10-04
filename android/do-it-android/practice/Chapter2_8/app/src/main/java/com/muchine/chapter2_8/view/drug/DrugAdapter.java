package com.muchine.chapter2_8.view.drug;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.model.drug.Drug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugAdapter extends BaseAdapter {

    private final Context context;

    private final Drawable defaultIcon;

    private final List<DrugItem> items = new ArrayList<>();

    public DrugAdapter(Context context) {
        this.context = context;

        Resources res = context.getResources();
        this.defaultIcon = res.getDrawable(R.drawable.capsule1);
    }

    public void reset(List<Drug> drugs) {
        items.clear();

        for (Drug drug : drugs) {
            items.add(new DrugItem(defaultIcon, drug));
        }
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
        DrugItemView itemView = (DrugItemView) view;
        if (itemView == null) itemView = new DrugItemView(context);

        DrugItem item = items.get(i);
        itemView.reload(item);

        return itemView;
    }
}
