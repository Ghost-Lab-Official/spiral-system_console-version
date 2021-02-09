package com.spiralSpotManagement.Server.Controllers.LocationControllers;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.LocationModel;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class LocationActions {

    public ResponseStatus registerLocation(LocationModel location)throws Exception{
        String location_id = UUID.randomUUID().toString();

        try{
            String par_id = location.getParent_id()==null || location.getParent_id().trim()==""? "": location.getParent_id();
            String sql = "INSERT INTO locations(" +
                    "location_id,level_id,parent_id,location_name,location_GPS,description)" +
                    " VALUES(?,?,?,?,?,?)";

            Connection connection = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, location_id);
            stmt.setString(2, location.getLevel_id());
            stmt.setString(3, location.getParent_id());
            stmt.setString(4, location.getLocation_name());
            stmt.setString(5, location.getLocation_GPS());
            stmt.setString(6, location.getDescription());
            int inserted_rec = stmt.executeUpdate();
            if(inserted_rec == 1){
                return new ResponseStatus(200,"CREATED","Location registered");
            }
            if(connection != null){
//                conn.close();
                return new ResponseStatus(500,"SERVER ERROR","Insertion failed, try or contact System Administrator");
            }

        }catch(Exception e){
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
        return new ResponseStatus(200,"CREATED","Location level registered");
    }
/////get locations by parent
public List<Object> fetchByParent(String parent) throws Exception {
    ResultSet result1;
    ResultSet result2;
    String locId = null;
    List<Object> locations = new ArrayList<>();
    Connection connection = new CloudStorageConnectionHandler().getConnection();
    String query1 = "select location_id,location_name from locations where location_name =?";
    PreparedStatement selectStmt1 = connection.prepareStatement(query1);
    selectStmt1.setString(1,parent);
    result1 = selectStmt1.executeQuery();
    while (result1.next()){
        System.out.println("Beautiful");
        locId = result1.getString("location_id");
    }
    String query = "select * from locations where parent_id ='"+locId+"' and status='active'";
    PreparedStatement selectStmt = connection.prepareStatement(query);
    result2 = selectStmt.executeQuery();
    while(result2.next()){
        List<Object> list = new ArrayList<>();
        list.add(result2.getString("location_name"));
        list.add(result2.getString("location_GPS"));
        list.add(result2.getString("description"));
        locations.add(list);
    }
    return locations;
}

//    OTHER METHODS TO GO HERE
//    ---------------------------------------


}
