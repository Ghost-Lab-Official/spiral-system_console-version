package com.spiralSpotManagement.LocationModule;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
public class LocationModule {
    /*
     *location management class. Method CheckLocationLevel for checking parent before inserting,updating location
     * @author Felix DUSENGIMANA
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  05-02-2021
     * @param connection {Connection} provide connection to database
     * @param parentId {string} the id of existing parent id.
     * return boolean to indicated the success or fail to update.
     */

    protected boolean CheckLocationLevel(Connection connection,String parentId){

        String query = "SELECT level_id FROM `location_levels` WHERE level_id =?";
        boolean checkResult = false;
        try {
            PreparedStatement checkStatment = connection.prepareStatement(query);
            checkStatment.setString(1,parentId);

            ResultSet rs = checkStatment.executeQuery();
            while (rs.next()){
                checkResult = true;
            }
        }catch (SQLException e){
             System.out.println("Error Message: "+e.getMessage());
        }
        return checkResult;
    }

    /*
    *location management class. Method updating for updating given location
    * @author Felix DUSENGIMANA
    * @powered by Rwanda Coding Academy
    * instructor Donatien MASHENGESHO
    * @since  04-02-2021
    * @param connection {Connection} provide connection to database
    * @param data {Hashmap} for new data to update existing ones.
    * return boolean to indicated the success or fail to update.
    *
    */

    public  boolean UpdateLocation(Connection connection, HashMap data) {
        Iterator dataIterator = data.entrySet().iterator();
        boolean updateResult = false;
        String query = "UPDATE locations SET ";
        StringBuilder attr = new StringBuilder();
        String cond = "";

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
     try {
         /* if condition for checking if the query to be inserted is correct*/
         if(attr.length()==0){
             updateResult=false;
         }else{
             String withoutLastComma = attr.substring( 0, attr.length( ) - ",".length( ) );
             query += withoutLastComma+" "+cond;
             System.out.println(query);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             int updated = preparedStatement.executeUpdate();
             if(updated==1){
                 updateResult = true;
             }else {
                 updateResult= false;
             }
         }
     }catch (SQLException e){
         System.out.println("Error Message: "+e.getMessage());
     }

     return  updateResult;
    }

    public  static  void  main(String[] args) throws Exception {
        CloudStorageConnection connection = new CloudStorageConnection();

        HashMap<String,String> updateLocationData = new HashMap<>();

        updateLocationData.put("location_id","add");
        updateLocationData.put("parent_id","add");
        updateLocationData.put("location_name","add");
        updateLocationData.put("location_GPS","add");
        updateLocationData.put("description","add");
        updateLocationData.put("status",null);

        LocationModule location = new LocationModule();
        boolean verify = location.UpdateLocation(connection.getConnection(),updateLocationData);
        if(!verify){
            System.out.println("Error occurred");
        }else{
            System.out.println("Location updating Successfully");
        }

    }

}
