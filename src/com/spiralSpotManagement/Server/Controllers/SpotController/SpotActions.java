package com.spiralSpotManagement.Server.Controllers.SpotController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.Spot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SpotActions {
    String InsertSpotQuery =
        "INSERT INTO Spot_table (user_id, category_id, location_id, spot_name, spot_description, registration_date, status, spot_id) VALUES(?,?,?,?,?,?,?,?)";
    String UpdateSpotQuery =
    "UPDATE Spot_table SET user_id=?, category_id=?, location_id=?, spot_name=?, spot_description=?, update_date=?, status=? WHERE spot_id=?";
    String deleteSpotQuery =
    "UPDATE Spot_table SET status=0 where spot_id=?";

    public ResponseStatus createSpotInDb(Spot spotToRegister)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (
          PreparedStatement preparedStatement = connection.prepareStatement(InsertSpotQuery)
        ) {
          preparedStatement.setString(1, spotToRegister.getUserId().toString());
          preparedStatement.setString(2, spotToRegister.getCategoryId().toString());
          preparedStatement.setString(3, spotToRegister.getLocationId().toString());
          preparedStatement.setString(4, spotToRegister.getSpotName());
          preparedStatement.setString(5, spotToRegister.getSpotDescription());
          preparedStatement.setString(6, spotToRegister.getRegistrationDate());
          preparedStatement.setString(7, spotToRegister.getStatus());
          preparedStatement.setString(8, spotToRegister.getSpotId().toString());
          int inserted = preparedStatement.executeUpdate();

            if (inserted == 1) {
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

    public ResponseStatus updateTheSpot(Spot spotToUpdate)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try (PreparedStatement sql = connection.prepareStatement(UpdateSpotQuery)) {
            sql.setString(1, spotToUpdate.getUserId().toString());
            sql.setString(2, spotToUpdate.getCategoryId().toString());
            sql.setString(3, spotToUpdate.getLocationId().toString());
            sql.setString(4, spotToUpdate.getSpotName());
            sql.setString(5, spotToUpdate.getSpotDescription());
            sql.setString(6, spotToUpdate.getRegistrationDate());
            sql.setString(7, spotToUpdate.getStatus());
            sql.setString(8, spotToUpdate.getSpotId().toString());
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
}
