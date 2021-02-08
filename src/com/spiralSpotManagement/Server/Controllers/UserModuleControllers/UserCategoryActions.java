package com.spiralSpotManagement.Server.Controllers.UserModuleControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.*;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCategoryActions {
    String InsertSql = "INSERT INTO users_categories (user_category) VALUES(?)";
    String UpdateSql = "UPDATE users_categories SET user_category=?,category_status=? WHERE category_id=?";
    String updateUserCategory = "UPDATE users_table SET user_category=? WHERE category_id=? ";
    String UpdateUserStatus = "UPDATE users_table SET user_status=? WHERE category_id=?";
    String deleteSQL = "DELETE FROM users_categories WHERE category_id=?";
    String selectSQL = "SELECT * FROM users_categories";

    public ResponseStatus createUserCategory(UserCategory userCategoryToRegister)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)){
            preparedStatement.setString(1,userCategoryToRegister.getCatName());
            int inserted = preparedStatement.executeUpdate();

            if(inserted == 1){
                return new ResponseStatus(200,"USER CATEGORY RECORDED","You have inserted the user category");
            }
        }
        catch (SQLException e){
             return  new ResponseStatus(400,"BAD REQUEST",e.getMessage());
        }
        catch (Exception e){
            new ResponseStatus(500,"SERVER ERROR",e.getMessage());
        }
        return null;
    }
    public List<Object> selectCategories() throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        Statement state= connection.createStatement();
        ResultSet result =state.executeQuery(selectSQL);
        List<Object> userCategories = new ArrayList<>();

        while (result.next()){
           UserCategory userCategory = new UserCategory();
           userCategory.setCatId(result.getInt(1));
           userCategory.setCatName(result.getString(2));
           userCategories.add((Object) userCategory);
        }
        return  userCategories;
    }
    public ResponseStatus updateCategory(UserCategory userCategoryToUpdate) throws Exception {
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UpdateSql)){
            preparedStatement.setInt(3,userCategoryToUpdate.getCatId());
            preparedStatement.setString(1,userCategoryToUpdate.getCatName());
            preparedStatement.setString(2,userCategoryToUpdate.getCatStatus());
            int updated = preparedStatement.executeUpdate();

            if(updated == 1){
                return new ResponseStatus(200,"USER CATEGORY UPDATED","You have updated the user category");
            }
        }
        catch (SQLException e){
            return  new ResponseStatus(400,"BAD REQUEST",e.getMessage());
        }
        catch (Exception e){
            new ResponseStatus(500,"SERVER ERROR",e.getMessage());
        }
        return new ResponseStatus(500,"SERVER ERROR","Server is done , not running ....");
    }
    public ResponseStatus deleteUserCategory(UserCategory userCategoryToDelete) throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)){
            preparedStatement.setInt(1, userCategoryToDelete.getCatId());
            int deleted = preparedStatement.executeUpdate();
            if(deleted == 1){
                updateStatus(userCategoryToDelete.getCatId(),"inactive");
                return new ResponseStatus(200,"USER CATEGORY DELETED","You have updated the user category");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return  new ResponseStatus(400,"BAD REQUEST",e.getMessage());
        }
        catch (Exception e){
            new ResponseStatus(500,"SERVER ERROR",e.getMessage());
        }
        return new ResponseStatus(500,"SERVER ERROR","e.getMessage()");
    }
    public ResponseStatus updateStatus(Integer categoryId,String categoryName) throws Exception {
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection = cloudStorageConnection.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(UpdateUserStatus)) {
            statement.setInt(2, categoryId);
            statement.setString(1, categoryName);
            int updated = statement.executeUpdate();
            if (updated == 1) {
                return new ResponseStatus(200, "USERS WITH THE SAME STATUS UPDATED", "User with the same changes is now updated");
            }
        }  catch (SQLException e){
            System.out.println(e.getMessage());
            return  new ResponseStatus(400,"BAD REQUEST",e.getMessage());
        } catch (Exception e){
            new ResponseStatus(500,"SERVER ERROR",e.getMessage());
        }
        return new ResponseStatus(500,"SERVER ERROR","e.getMessage()");
    }
}
