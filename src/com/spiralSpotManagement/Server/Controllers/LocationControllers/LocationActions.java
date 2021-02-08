package com.spiralSpotManagement.Server.Controllers.LocationControllers;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.LocationModel;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LocationActions {

    public ResponseStatus registerLocation(LocationModel location){
        String location_id = UUID.randomUUID().toString();

        try{
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

        }catch(Exception e){
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
        return new ResponseStatus(200,"CREATED","Location  registered");
    }

    /**
     *location management class. Method updating for updating given location
     * @author Felix DUSENGIMANA
     * @copyright by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-02-2021
     * return boolean to indicated the success or fail to update.
     *
     */

    public  ResponseStatus UpdateLocation (LocationModel location){

        //check new parent_id going to be updated exists
        if (location.getParent_id()!=null){
            if (!CheckParentId(location.getParent_id())){
                return  new ResponseStatus(422,"BAD REQUEST","THE PARENT ID DOESN'T EXISTS");
            }
        }

        //chek new level id going to be update exists
        if(location.getLevel_id()!=null){
           if (!CheckLevelId(location.getLevel_id())){
               return  new ResponseStatus(422,"BAD REQUEST","LEVEL ID DOESN'T EXISTS");
           }
        }

        HashMap<String,String> updateLocationData = new HashMap<>();
        updateLocationData.put("location_id",location.getLocation_id());
        updateLocationData.put("parent_id",location.getParent_id());
        updateLocationData.put("level_id",location.getLevel_id());
        updateLocationData.put("location_name",location.getLocation_name());
        updateLocationData.put("location_GPS",location.getLocation_GPS());
        updateLocationData.put("description",location.getDescription());

        Iterator dataIterator = updateLocationData.entrySet().iterator();
        StringBuilder attr = new StringBuilder();
        String cond = "",query="";

        //while loop for looping through hashMap to check the attributes a user wants to update
        while (dataIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)dataIterator.next();
            if (mapElement.getValue()!=null){
                /*get location id to update*/
                if(mapElement.getKey() =="location_id"){
                    cond = "WHERE "+mapElement.getKey()+"='"+mapElement.getValue()+"'";
                }else{
                    attr.append(" ").append(mapElement.getKey()).append("=").append("'").append(mapElement.getValue()).append("',");
                }
            }
        }

        System.out.println(attr);

        try{
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            if(attr.length()==0){
                return  new ResponseStatus(400,"BAD REQUEST","Please enter at least one key to update.");
            }else{
                String withoutLastComma = attr.substring( 0, attr.length( ) - ",".length( ) );
                query +="UPDATE locations SET "+withoutLastComma+" "+cond;
                System.out.println("Query :: "+query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int updated = preparedStatement.executeUpdate();
                if(updated==1){
                    connection.close();
                    return  new ResponseStatus(200,"UPDATED","Location updated successfully.");
                }else {
                    connection.close();
                    return  new ResponseStatus(400,"BAD REQUEST","Error while updating.");
                }
            }
        }catch (Exception e){
            return  new ResponseStatus(300,"EXCEPTION ERROR",e.getMessage());
        }
    }


    /**
     *location management class. Method updating for updating given location
     * @author Felix DUSENGIMANA
     * @copyright  by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-02-2021
     * @param parentId {String} for new data to update existing ones.
     * return boolean to indicated the success or fail to update.
     *
     */

    protected  boolean CheckParentId(String parentId){
        String query = "SELECT location_id FROM `locations` WHERE location_id =?";
        boolean checkResult = false;
        try {
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            PreparedStatement checkStatment = connection.prepareStatement(query);
            checkStatment.setString(1,parentId);

            ResultSet rs = checkStatment.executeQuery();
            if (rs.next()){
                connection.close();
                checkResult = true;
            }
        }catch (SQLException e){
            System.out.println("Error Message: "+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkResult;
    }


    /**
     *location management class. Method updating for updating given location
     * @author Felix DUSENGIMANA
     * @copyright  by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-02-2021
     * @param levelId {String} for new data to update existing ones.
     * return boolean to indicated the success or fail to update.
     *
     */

    protected  boolean CheckLevelId(String levelId){
        String query = "SELECT level_id FROM `location_levels` WHERE level_id =?";
        boolean checkResult = false;
        try {
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            PreparedStatement checkStatment = connection.prepareStatement(query);
            checkStatment.setString(1,levelId);

            ResultSet rs = checkStatment.executeQuery();
            if (rs.next()){
                connection.close();
                checkResult = true;
            }
        }catch (SQLException e){
            System.out.println("Error Message: "+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkResult;
    }

    /**
     *
     * @author Divine
     * @author Felix DUSENGIMANA
     * @description deleteLocation is function to delete location it will not delete by removing location in database
     * it will change the status of our input to inactive if it exists in our database
     * */

    public ResponseStatus DeleteLocation(LocationModel location){
        try {
            Connection connection= new CloudStorageConnectionHandler().getConnection();

                if(!CheckParentId(location.getLocation_id())){
                    return new ResponseStatus(400,"BAD REQUEST","Oops,Entered location doesn't exists");
                }
                        String status = "inactive";
                        String sql = "UPDATE locations SET status = ? WHERE location_id = ?";
                        PreparedStatement stmt = connection.prepareStatement(sql);
                        stmt.setString(1,status);
                        stmt.setString(2,location.getLocation_id());
                        int updated = stmt.executeUpdate();
                        if(updated<1){
                            connection.close();
                            return  new ResponseStatus(500,"SERVER ERROR","Unable to delete, please try again.");
                        }
                        connection.close();
            return  new ResponseStatus(200,"UPDATED","updated successfully.");

        } catch (Exception e) {
            return  new ResponseStatus(500,"SERVER ERROR",e.getMessage());
        }
    }



//    OTHER METHODS TO GO HERE
//    ---------------------------------------


}
