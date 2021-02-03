package com.spiralSpotManagement.SpotModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.net.ConnectException;
import java.sql.Connection;

public class SpotRatings {
    private String rating_id;
    private String user_id;
    private String spot_id;
    private Integer rating;

    public String getRating_id() {
        return rating_id;
    }

    public void setRating_id(String rating_id) {
        this.rating_id = rating_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(String spot_id) {
        this.spot_id = spot_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    private static final String Add_spot_rating() throws Exception{
        //try and catch
        try{
            CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "INSERT INTO spot_rating"
        }
    }
}
