
/*
        @author : Manzi Gusave
        @Description: the model for the Spot Ratings Action class
 */

package com.spiralSpotManagement.Server.Controllers.SpotController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.Spot;
import com.spiralSpotManagement.Server.Model.SpotRatings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SpotRatingActions {
    String InsertSpotRatingQuery = "INSERT INTO spot_ratings (user_id, spot_id, rating) VALUES(?,?,?) ";
    String UpdateSpotRatingQuery = "UPDATE spot_ratings set user_id=?, spot_id=?, rating=? WHERE rating_id=?";
    String deleteSpotRatingQuery = "UPDATE Spot_table SET status=0 where spot_id=?";

    /*
            @author : Manzi Gustave
            @description: This method is used to insert a new spot Rating
     */
    public ResponseStatus createSpotRating(SpotRatings spotRatingToRegister)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(InsertSpotRatingQuery)
        ) {
            preparedStatement.setInt(1, spotRatingToRegister.getUser_id());
            preparedStatement.setInt(2, spotRatingToRegister.getSpot_id());
            preparedStatement.setInt(3, spotRatingToRegister.getRating());
            int inserted = preparedStatement.executeUpdate();

            if (inserted == 1) {
                return new ResponseStatus(200, "SPOT RATING CREATED", "Spot rating is available World Wide");
            }

        } catch (SQLException e) {
            return new ResponseStatus(404, "BAD REQUEST", e.getMessage());
        }

        catch (Exception e){
            return new ResponseStatus(505,"SERVER ERROR",e.getMessage());
        }

        return null;
    }
    /*
            @author : Manzi Gustave
            @description: This method is used to update a spot Ratings
     */
    public ResponseStatus updateTheSpotRating(SpotRatings spotRatingToUpdate)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (PreparedStatement sql = connection.prepareStatement(UpdateSpotRatingQuery)) {
            sql.setInt(1, spotRatingToUpdate.getUser_id());
            sql.setInt(2, spotRatingToUpdate.getSpot_id());
            sql.setInt(3, spotRatingToUpdate.getRating());
            sql.setInt(4, spotRatingToUpdate.getSpotRatingId());
            int updated = sql.executeUpdate();

            if (updated == 1) {
                return new ResponseStatus(200, "SPOT CREATED", "Spot is available World Wide");
            }

        } catch (SQLException e) {
            return new ResponseStatus(404, "BAD REQUEST", e.getMessage());
        }

        catch (Exception e){
            return new ResponseStatus(505,"SERVER ERROR",e.getMessage());
        }

        return null;
    }
    /*
            @author : Manzi Gustave
            @description: This method is used to de-activate a spot
     */
    public ResponseStatus updateTheSpotRatingStatus(Spot spotToDelete)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (PreparedStatement deleteConnection = connection.prepareStatement(deleteSpotRatingQuery)) {
            deleteConnection.setString(1, spotToDelete.getSpotId().toString());
            int deleted = deleteConnection.executeUpdate();

            if (deleted == 1) {
                return new ResponseStatus(200, "SPOT DELETED", "Spot is now Inactive");
            }

        } catch (SQLException e) {
            return new ResponseStatus(404, "BAD REQUEST", e.getMessage());
        }

        catch (Exception e){
            return new ResponseStatus(505,"SERVER ERROR",e.getMessage());
        }

        return null;
    }
}