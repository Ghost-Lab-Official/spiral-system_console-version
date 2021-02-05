package com.spiralSpotManagement.LocationModule;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LocationModule extends Location_Level{


    /*
     *location management class. Method updating for updating given location
     * @author Felix DUSENGIMANA
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-02-2021
     * @param parentId {String} for new data to update existing ones.
     * return boolean to indicated the success or fail to update.
     *
     */

    protected  boolean CheckParentId(String parentId){
        String query = "SELECT location_id FROM `locations` WHERE location_id =?";
        boolean checkResult = false;
        Connection connection = null;
        try {
            connection= getConnection();
            PreparedStatement checkStatment = connection.prepareStatement(query);
            checkStatment.setString(1,parentId);

            ResultSet rs = checkStatment.executeQuery();
            while (rs.next()){
                checkResult = true;
            }
        }catch (SQLException e){
            System.out.println("Error Message: "+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkResult;
    }

    /*
    *location management class. Method updating for updating given location
    * @author Felix DUSENGIMANA
    * @powered by Rwanda Coding Academy
    * instructor Donatien MASHENGESHO
    * @since  04-02-2021
    * @param data {Hashmap} for new data to update existing ones.
    * return boolean to indicated the success or fail to update.
    *
    */

    public  boolean UpdateLocation(HashMap data) {
        Iterator dataIterator = data.entrySet().iterator();
        boolean updateResult = false;
        String query = "UPDATE locations SET ";
        StringBuilder attr = new StringBuilder();
        String cond = "";
        Connection connection = null;
        String parentId = (String) data.get("parent_id");
        String levelId = (String) data.get("level_id");

        if(!CheckParentId(parentId) || !CheckLocationLevelExistence(levelId)){
            return  false;
        }
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
         connection = getConnection();

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
     } catch (Exception e) {
         e.printStackTrace();
     }

        return  updateResult;
    }

    public  static  void  main(String[] args){
        HashMap<String,String> updateLocationData = new HashMap<>();

        updateLocationData.put("location_id","af95f097-008e-4774-a67a-3ca6d42c3d55");
        updateLocationData.put("parent_id","LOC001HQT");
        updateLocationData.put("level_id","6f4e56c6-a173-42a3-b458-a7ead5905ad0");
        updateLocationData.put("location_name","America");
        updateLocationData.put("location_GPS",null);
        updateLocationData.put("description","No given description");
        updateLocationData.put("status",null);

        LocationModule location = new LocationModule();
        boolean verify = location.UpdateLocation(updateLocationData);
        if(!verify){
            System.out.println("Error occurred while updating");
        }else{
            System.out.println("Location updating Successfully");
        }

    }

}
