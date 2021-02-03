package com.spiralSpotManagement.ReportModule; // spots model by Best Verie

import java.util.Date;

public class spotsModel {
    private String spot_id;
    private String user_id;
    private String category_id;
    private String location_id;
    private String spot_name;
    public String spot_description;
    private double views;
    private String status;
    private Date registration_date;

    public spotsModel(String spot_id,
                      String user_id,
                      String category_id,
                      String location_id,
                      String spot_name,
                      String spot_description,
                      double views,
                      String status,
                      Date registration_date) {
        this.spot_id=spot_id;
        this.user_id=user_id;
        this.category_id=category_id;
        this.location_id=location_id;
        this.spot_name=spot_name;
        this.spot_description=spot_description;
        this.views=views;
        this.status=status;
        this.registration_date=registration_date;
    }


    public String getCategory_id() {
        return category_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public String getSpot_id() {
        return spot_id;
    }

    public String getSpot_name() {
        return spot_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }

    public double getViews() {
        return views;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public void setSpot_id(String spot_id) {
        this.spot_id = spot_id;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setViews(double views) {
        this.views = views;
    }
}
