package com.muchine.chapter2_8.model.drug;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class Drug {

    private String code;

    private String name;

    private String product;

    private String distributor;

    public Drug(String code, String name, String product, String distributor) {
        this.code = code;
        this.name = name;
        this.product = product;
        this.distributor = distributor;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public String getDistributor() {
        return distributor;
    }
}
