package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

/**
 *
 * @author Divine
 * Billing modal format
 * This is describing the entities reserved for the billing table
 */

public class BillingModel implements Serializable {

    private int billing_id;
    private String billing_name;
    private  String billing_price;
    private  int billing_period;

    public BillingModel(){}

    public BillingModel(int billing_id, String billing_name, String billing_price, int billing_period){
        this.billing_id = billing_id;
        this.billing_name = billing_name;
        this.billing_price = billing_price;
        this.billing_period = billing_period;
    }

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }

    public String getBilling_name() {
        return billing_name;
    }

    public void setBilling_name(String billing_name) {
        this.billing_name = billing_name;
    }

    public String getBilling_price() {
        return billing_price;
    }

    public void setBilling_price(String billing_price) {
        this.billing_price = billing_price;
    }

    public int getBilling_period() {
        return billing_period;
    }

    public void setBilling_period(int billing_period) {
        this.billing_period = billing_period;
    }
}
