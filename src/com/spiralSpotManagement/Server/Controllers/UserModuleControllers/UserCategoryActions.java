package com.spiralSpotManagement.Server.Controllers.UserModuleControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.User;
import com.spiralSpotManagement.Server.Model.UserCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCategoryActions {
    String InsertSql = "INSERT INTO users_categories (user_category) VALUES(?)";
    String UpdateSql = "UPDATE users_categories SET user_category=? WHERE category_id=?";
    String deleteSQL = "DELETE FROM users_categories where category_id=?";
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
            preparedStatement.setInt(2,userCategoryToUpdate.getCatId());
            preparedStatement.setString(1,userCategoryToUpdate.getCatName());
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
        return new ResponseStatus(500,"SERVER ERROR","e.getMessage()");
    }
}
