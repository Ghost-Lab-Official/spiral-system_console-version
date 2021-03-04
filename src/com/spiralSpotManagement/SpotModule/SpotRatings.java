package com.spiralSpotManagement.SpotModule;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;

import java.io.InputStreamReader;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;

import static java.lang.Integer.parseInt;

public class SpotRatings {
    private Integer user_id;
    private String spot_id;
    private Integer rating;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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

    private static final String INSERT_SPORT_RATING = "INSERT INTO spot_ratings (user_id, spot_id, rating) VALUES(?,?,?) ";
    private static final String UPDATE_SPORT_RATING = "UPDATE spot_ratings set user_id=?, spot_id=?, rating=? WHERE rating_id=?";
    //private static final String REPORT_SPORT_RATING = "";


    public void Add_spot_rating() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\t\t\tEnter User id");
        user_id = parseInt(reader.readLine());
        System.out.println("\t\t\t Enter spot_id: ");
        spot_id = reader.readLine();
        System.out.println("\t\t\t Enter rating(1-5): ");
        rating = parseInt(reader.readLine());

        try{
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "INSERT INTO spot_ratings (user_id,spot_id,rating)";
            PreparedStatement statement = connection.prepareStatement(INSERT_SPORT_RATING);
            statement.setInt(1,user_id);
            statement.setString(2,spot_id);
            statement.setInt(3,rating);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update_spot_rating() throws Exception{
        try{
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "UPDATE spot_ratings SET rating=? WHERE spot_id = ?";
            PreparedStatement statement = connection.prepareStatement(INSERT_SPORT_RATING);
            statement.setInt(1,rating);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    private static final void update_spot_rating() throws Exception{
//
//    }
}
