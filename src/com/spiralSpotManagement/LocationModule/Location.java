package com.spiralSpotManagement.LocationModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.UUID;

/**
 * Location management class. It contains all methods for manipulating location table.
 * @author Harerimana Egide
 * @version 1.0
 * @since 2021-02-03
 */
public class Location extends CloudStorageConnection {

    /**
     *  Create <i>locations</i> table when it does not exist.
     * @author Harerimana Egide
     * @version 1.0
     * @return boolean indicating if the query was ok, i.e true/false
     */
    public boolean createTable(){
        boolean query_ok = false;
        String sql = "CREATE TABLE `locations` (" +
                "`location_id` varchar(255) NOT NULL," +
                "`level_id` varchar(255) NOT NULL," +
                "`parent_id` varchar(255)," +
                "`location_name` varchar(255) NOT NULL," +
                "`location_GPS` varchar(255) NOT NULL," +
                "`description` TEXT NOT NULL," +
                "PRIMARY KEY (`location_id`)," +
                "FOREIGN KEY (`parent_id`) REFERENCES `locations`(`location_id`)," +
                "FOREIGN KEY (`level_id`) REFERENCES `location_levels`(`level_id`)" +
                ")";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.executeUpdate();
            query_ok = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return query_ok;
    }

    /**
     * Registering a new location. This function will take:
     * @param location_name the new location name
     * @param location_GPS the new location GPS coordinates
     * @param description the new location description
     * @param level_id the new location's location level id
     * @param parent_id the new location's parent location id. It can be null or empty string if no parent exists.
     * @return String the new location id or false in case of query failure.
     * @author Harerimana Egide
     */
    public String newLocation(
            String location_name, String location_GPS, String description,
            String level_id, String parent_id
    ) {
        String result = "false";
        String location_id = UUID.randomUUID().toString();
        String par_id = parent_id==null || parent_id.trim()==""? "": parent_id;
        return result;
    }
}