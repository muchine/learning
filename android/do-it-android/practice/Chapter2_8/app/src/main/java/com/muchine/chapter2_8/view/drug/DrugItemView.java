package com.muchine.chapter2_8.view.drug;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muchine.chapter2_8.R;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugItemView extends LinearLayout {

    public DrugItemView(Context context) {
        super(context);

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflator.inflate(R.layout.drug_item, this, true);
    }

    public DrugItemView(Context context, DrugItem item) {
        this(context);
        reload(item);
    }

    public void reload(DrugItem item) {
        initIcon(item);
        initDescription(item);
    }

    private void initIcon(DrugItem item) {
        ImageView icon = (ImageView) findViewById(R.id.drugIcon);
        icon.setImageDrawable(item.getIcon());
    }

    private void initDescription(DrugItem item) {
        TextView productName = (TextView) findViewById(R.id.drugProductName);
        productName.setText(item.getProductName());

        TextView code = (TextView) findViewById(R.id.drugCode);
        code.setText(item.getCode());

        TextView name = (TextView) findViewById(R.id.drugName);
        name.setText(item.getName());

        TextView distributor = (TextView) findViewById(R.id.drugDistributor);
        distributor.setText(item.getDistributor());
    }
}
