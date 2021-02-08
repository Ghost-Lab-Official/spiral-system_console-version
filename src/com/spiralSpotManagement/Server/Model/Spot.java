package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class Spot implements Serializable {
    private Integer spotId;
    private Integer userId;
    private Integer categoryId;
    private Integer locationId;
    private String spotName;
    private String spotDescription;
    private String registrationDate;
    private String status;

    public Spot() {
    }

    public Spot( Integer spotId,Integer userId, Integer categoryId, Integer locationId, String spotName, String spotDescription, String status) {
        this.spotId = spotId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.locationId = locationId;
        this.spotName = spotName;
        this.spotDescription = spotDescription;
//        this.registrationDate = registrationDate;
        this.status = status;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getSpotDescription() {
        return spotDescription;
    }

    public void setSpotDescription(String spotDescription) {
        this.spotDescription = spotDescription;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
