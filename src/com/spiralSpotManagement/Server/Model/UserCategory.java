package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class UserCategory implements Serializable {
    private int catId;
    private String catName;

    public UserCategory() {
    }

    public UserCategory(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
