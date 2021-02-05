package com.spiralSpotManagement.LocationModule;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
public class LocationModule {

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

    public  boolean UpdateLocation(Connection connection, HashMap data) throws SQLException {
        Iterator dataIterator = data.entrySet().iterator();

        String query = "UPDATE Locations SET ";
        StringBuilder attr = new StringBuilder();
        String cond = "";

        /*while loop for looping through hashMap to check the attributes a user wants to update*/
        while (dataIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)dataIterator.next();
            if (mapElement.getValue()!=null){
                /*get location id to update*/
                if(mapElement.getKey() =="locationId"){
                    cond = "WHERE "+mapElement.getKey()+"='"+mapElement.getValue()+"'";
                }else{
                    attr.append(" ").append(mapElement.getKey()).append("=").append("'").append(mapElement.getValue()).append("',");
                }
            }
        }

       /* if condition for checking if the querry to be inserted is correct*/
        if(attr.length()==0){
            return  false;
        }else{
            query += attr.append("status = 'inactive' ")+" "+cond;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int updated = preparedStatement.executeUpdate();
            if(updated==1){
                System.out.println("Location name updated successfully");
                return  true;
            }else {
                System.out.println("Error while updating location");
                return  false;
            }
        }
    }

    public  static  void  main(String[] args) throws Exception {
        CloudStorageConnection connection = new CloudStorageConnection();

        HashMap<String,String> updateLocationData = new HashMap<>();

        LocationModule location = new LocationModule();
        boolean verify = location.UpdateLocation(connection.getConnection(),updateLocationData);
        if(!verify) System.out.println("error occured");
        System.out.println("Locations module");
    }

}
