package com.spiralSpotManagement.Server.Controllers.SpotCategoryControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.SpotCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 @author: ntwari egide
 @descriptions: all spot category actions goes here
 */
public class SpotCategoryActions {
    String registerSpotCategoryQuery = "INSERT INTO spot_category(user_id,category_name,description,status) VALUES (?,?,?,?)";
    String updateStatusQuery = "UPDATE spot_category SET status=? WHERE category_id=?";

    public ResponseStatus addNewSpotCategory(SpotCategory spotCategoryToAdd) throws Exception {

        Connection connection = new CloudStorageConnectionHandler().getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(registerSpotCategoryQuery);
            statement.setInt(1, spotCategoryToAdd.getUserId());
            statement.setString(2, spotCategoryToAdd.getCategoryName());
            statement.setString(3, spotCategoryToAdd.getDescription());
            statement.setString(4, spotCategoryToAdd.getStatus());

            int inserted = statement.executeUpdate();
            if (inserted == 1) {
                return new ResponseStatus(200, "CREATED SPOT CATEGORY", "Spot Category is now added");
            } else {
                return new ResponseStatus(500, "SERVER ERROR", "Insertion failed, try or contact System Administrator");
            }
        } catch (Exception e) {
            return new ResponseStatus(300, "EXCEPTIONAL ERROR", e.getMessage());
        }
    }

    public ResponseStatus updateSpotCategory(SpotCategory spotCategory) throws Exception {

        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            String sql = "UPDATE spot_category SET category_name=?,description=?,status=? WHERE category_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, spotCategory.getCategoryName());
            statement.setString(2, spotCategory.getDescription());
            statement.setString(3, spotCategory.getStatus());
            statement.setInt(4, spotCategory.getCategoryId());

            int inserted = statement.executeUpdate();
            if (inserted == 1) {
                return new ResponseStatus(200, "UPDATED SPOT CATEGORY", "Spot Category is now updated");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "SERVER ERROR", e.getMessage());
        }
        return null;
    }

    public List<SpotCategory> getAllSpotCategories() throws Exception {

        List<SpotCategory> spotCategoryList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            String sql = "SELECT * FROM spot_category";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                SpotCategory spotCategory = new SpotCategory();
                spotCategory.setUserId(result.getInt("user_id"));
                spotCategory.setCategoryId(result.getInt("category_id"));
                spotCategory.setCategoryName(result.getString("category_name"));
                spotCategory.setStatus(result.getString("status"));
                spotCategory.setDescription(result.getString("description"));

                spotCategoryList.add(spotCategory);
            }

            return spotCategoryList;
        }
         catch (Exception e) {
            return spotCategoryList;
        }
    }

    public ResponseStatus updateStatus(SpotCategory spotCategory)throws Exception{
        try {
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection = cloudStorageConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateStatusQuery);
            statement.setString(1, spotCategory.getStatus());
            statement.setInt(2, spotCategory.getCategoryId());
            int updated = statement.executeUpdate();

            if (updated == 1) {
                return new ResponseStatus(200, "SPOT STATUS UPDATED", "Spot Category is now updated");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "EXCEPTION ERROR", e.getMessage());
        }

        return null;
    }
}