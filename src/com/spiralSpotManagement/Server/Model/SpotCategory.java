package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

/*
        @author : NTWARI Egide
        @Description: the model for the SpotCategory class
 */
public class SpotCategory implements Serializable {
    private Integer spotId;
    private Integer userId;
    private String categoryName;
    private String description;
    private String status;

    public SpotCategory() {
    }

    public SpotCategory(Integer spotId, Integer userId, String categoryName, String description, String status) {
        this.spotId = spotId;
        this.userId = userId;
        this.categoryName = categoryName;
        this.description = description;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
