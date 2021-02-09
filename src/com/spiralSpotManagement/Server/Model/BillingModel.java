package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class BillingModel implements Serializable {
    private Integer billing_id ;
    private String billing_name;
    private Integer price;

    public BillingModel(){}

    public Integer getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(Integer billing_id) {
        this.billing_id = billing_id;
    }

    public String getBilling_name() {
        return billing_name;
    }

    public void setBilling_name(String billing_name) {
        this.billing_name = billing_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
