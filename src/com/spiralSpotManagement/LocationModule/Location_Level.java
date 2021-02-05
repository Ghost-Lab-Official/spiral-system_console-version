package com.spiralSpotManagement.LocationModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Location level management class. It contains all methods for manipulating location_levels table.
 * @author Harerimana Egide
 * @version 1.0
 * @since 2021-02-02
 */
public class Location_Level extends CloudStorageConnection{

    /**
     *  Create <i>location_levels</i> table when it does not exist.
     * @author Harerimana Egide
     * @version 1.0
     * @return boolean indicating if the query was ok, i.e true/false
     */
    public boolean createLocationLevelsTable(){
        boolean query_ok = false;
        String sql = "CREATE TABLE IF NOT EXISTS `location_levels` (" +
                "`level_id` VARCHAR(255) NOT NULL, `level_name` VARCHAR(255) NOT NULL," +
                "PRIMARY KEY (`level_id`))";
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
     *  Register a new location level. It will take a level name and
     *  return a new location level id or false in case of query failure
     * @author Harerimana Egide
     * @version 1.0
     * @param level_name the name for new level
     * @return String the new location level id or false in case of query failure.
     */
    public String newLocationLevel(String level_name){
        String res = "false";
        String level_id = UUID.randomUUID().toString();
        try{
            String sql = "INSERT INTO location_levels(level_id,level_name) VALUES(?,?)";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, level_id);
            stmt.setString(2, level_name);
            int inserted_rec = stmt.executeUpdate();
            if(inserted_rec == 1){
                res = level_id;
            }
            if(conn != null){
                conn.close();
            }
        }catch(Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return res;
    }

    /**
     *  Update a location level. It will take a new level name and
     *  a level_id to update
     * @author Harerimana Egide
     * @version 1.0
     * @param level_name the new level name
     * @param level_id the id of the level to update
     * @return boolean indicating whether the query is ok
     */
    public boolean updateLocationLevel(String level_id, String level_name){
        boolean ok = false;
        String sql = "UPDATE location_levels SET level_name=? WHERE level_id=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(2, level_id);
            stmt.setString(1, level_name);
            stmt.executeUpdate();
            ok = true;
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ok;
    }

    /**
     *  Delete a location level. It will take a level_id's to delete
     * @author Harerimana Egide
     * @version 1.0
     * @param level_id id of the level to delete
     * @return boolean indicating whether the query is ok
     */
    public boolean deleteLocationLevel(String level_id){
        boolean ok = false;
        String sql = "DELETE FROM location_levels WHERE level_id=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, level_id);
            stmt.executeUpdate();
            ok = true;
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ok;
    }

    /**
     * This function retrieves a single location level from the <i>location_levels</i> table
     * @author Harerimana Egide
     * @version 1.0
     * @param level_id id of the level to get
     * @return HashMap of Strings that contains levels data
     */
    public HashMap<String, String> getLocationLevel(String level_id){
        HashMap<String, String> level_map = new HashMap<>();
        String sql = "SELECT * FROM location_levels WHERE level_id=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, level_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                level_map.put("level_id", rs.getString("level_id"));
                level_map.put("level_name", rs.getString("level_name"));
            }
            rs.close();
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return level_map;
    }

    /**
     * This function retrieves all location levels from the <i>location_levels</i> table
     * @author Harerimana Egide
     * @version 1.0
     * @return List of HashMaps that contains levels data
     */
    public List<HashMap> getAllLocationsLevels(){
        List<HashMap> list = new ArrayList<>();
        String sql = "SELECT * FROM location_levels";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                HashMap<String, String> map = new HashMap<>();
                map.put("level_id", rs.getString("level_id"));
                map.put("level_name", rs.getString("level_name"));
                list.add(map);
            }
            rs.close();
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
