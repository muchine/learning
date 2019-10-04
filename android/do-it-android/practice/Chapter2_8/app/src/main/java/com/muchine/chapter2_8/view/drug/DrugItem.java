package com.muchine.chapter2_8.view.drug;

import android.graphics.drawable.Drawable;

import com.muchine.chapter2_8.model.drug.Drug;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugItem {

    private final Drawable icon;

    private final Drug drug;

    public DrugItem(Drawable icon, Drug drug) {
        this.icon = icon;
        this.drug = drug;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getProductName() {
        return drug.getProduct();
    }

    public String getCode() {
        return drug.getCode();
    }

    public String getName() {
        return drug.getName();
    }

    public String getDistributor() {
        return drug.getDistributor();
    }

}
