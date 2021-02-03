package com.spiralSpotManagement.ReportModule; // spots model by Best Verie

import java.util.Date;

public class spotsModel {
    private String spot_id;
    private String user_name;
    private String category_name;
    private String locationName;
    private String spot_name;
    public String spot_description;
    private double views;
    private String status;
    private Date registration_date;

    public spotsModel(String spot_id,
                      String user_name,
                      String category_name,
                      String locationName,
                      String spot_name,
                      String spot_description,
                      double views,
                      String status,
                      Date registration_date) {
        this.spot_id=spot_id;
        this.user_name=user_name;
        this.category_name=category_name;
        this.locationName=locationName;
        this.spot_name=spot_name;
        this.spot_description=spot_description;
        this.views=views;
        this.status=status;
        this.registration_date=registration_date;
    }


    public String getCategory_name() {
        return category_name;
    }

    public String getLocationName() {
        return locationName;
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

    public String getuser_name() {
        return user_name;
    }

    public String getStatus() {
        return status;
    }

    public double getViews() {
        return views;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setViews(double views) {
        this.views = views;
    }
}
