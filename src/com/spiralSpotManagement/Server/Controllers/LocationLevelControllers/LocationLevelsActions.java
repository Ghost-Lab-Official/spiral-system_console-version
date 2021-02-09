package com.spiralSpotManagement.Server.Controllers.LocationLevelControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.LocationLevels;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * Location levels actions (Synchronizing the classes)
 * @author Gervais Ishimwe
 * @author Harerimana Egide
 */

public class LocationLevelsActions {
    String createLevelQuery = "INSERT INTO location_levels(level_id,level_name, description) VALUES(?,?,?)";
    String deleteLevelQuery = "DELETE FROM location_levels WHERE level_id=?";
    String updateLevelQuery = "UPDATE location_levels SET level_name=?,description=? WHERE level_id=?";
    String getLevelQuery = "SELECT * FROM location_levels WHERE level_id=?";

    /**
     *  Register a new location level. It will take a level name and
     *  return a new location level id or false in case of query failure
     * @author Harerimana Egide
     * @version 1.0
     * @param level the new level object
     * @return response status.
     */

    public ResponseStatus registerLocationLevel(LocationLevels level)throws Exception{
        String level_id = UUID.randomUUID().toString();

        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(createLevelQuery);
            stmt.setString(1,level_id );
            stmt.setString(2, level.getLevel_name());
            stmt.setString(3, level.getDescription());
            int inserted_rec = stmt.executeUpdate();
            if(inserted_rec == 1){
              return new ResponseStatus(200,"CREATED","Location level registered");
            }
            if(connection == null){
                return new ResponseStatus(500,"SERVER ERROR","Insertion failed, try or contact System Administrator");
            }

        }catch(Exception e){
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
           return new ResponseStatus(200,"CREATED","Location level registered");
    }

    /**
     *  Update a location level. It will take a new level name and
     *  a level_id to update
     * @author Harerimana Egide
     * @version 1.0
     * @param level the level object
     * @return response status
     */
    public ResponseStatus updateLocationLevel(LocationLevels level)throws Exception{
        try {
            Connection connection = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement stmt = connection.prepareStatement(updateLevelQuery);
            stmt.setString(3, level.getLevel_id());
            stmt.setString(1, level.getLevel_name());
            stmt.setString(2, level.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
        return new ResponseStatus(200,"UPDATED","Location level updated");
    }

    /**
     *  Delete a location level. It will take a level_id's to delete
     * @author Harerimana Egide
     * @version 1.0
     * @param level level object
     * @return response status
     */
    public ResponseStatus deleteLocationLevel(LocationLevels level)throws Exception{
        try {
            Connection connection = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement stmt = connection.prepareStatement(deleteLevelQuery);
            stmt.setString(1, level.getLevel_id());
            stmt.executeUpdate();
        } catch (Exception e) {
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
        return new ResponseStatus(200,"DELETED","Location level deleted");
    }

    /**
     * Get a single location level
     * @author Harerimana Egide
     * @param level location level object containing level id
     * @return response status
     * */
    public ResponseStatus getLevel(LocationLevels level) throws Exception {
        try {
            Connection connection = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement stmt = connection.prepareStatement(getLevelQuery);
            stmt.setString(1, level.getLevel_id());
            ResultSet rs = stmt.executeQuery();
            LocationLevels locationLevel = new LocationLevels();
            while (rs.next()){
                locationLevel.setLevel_id(rs.getString("level_id"));
                locationLevel.setLevel_name((rs.getString("level_name")));
                locationLevel.setDescription(rs.getString("description"));
            }
            return new ResponseStatus(200, "LEVEL EXIST", (Object) locationLevel, "Got location level");
        } catch (Exception e) {
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
    }
}
