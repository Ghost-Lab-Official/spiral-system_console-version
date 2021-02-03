package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class ReportOperation {

    public static void viewAllActiveSpots() throws Exception {

            CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("select  * from Spot_table where status='active'");


        ArrayList<spotsModel> ActiveSpotsList = new ArrayList<spotsModel>();


        while (result.next()) {

            spotsModel  myActiveSpot = new spotsModel(
                    result.getString("spot_id"),
                    result.getString("user_id"),
                    result.getString("category_id"),
                    result.getString("location_id"),
                    result.getString("spot_name"),
                    result.getString("spot_description"),
                    result.getDouble("views"),
                    result.getString("status"),
                    result.getDate("registration_date")
            );
            ActiveSpotsList.add(myActiveSpot);
        }

//        System.out.println(ActiveSpotsList.size());
//        for(int i = 0; i < ActiveSpotsList.size(); i++)
//        {
//            System.out.println(ActiveSpotsList.get(i).getSpot_id() + "| " + ActiveSpotsList.get(i).getUser_id() + " | " + ActiveSpotsList.get(i).getSpot_name());
//        }

        Iterator it = ActiveSpotsList.iterator();
        System.out.println(" " + "#Id" +  " | " + "userId" +" | " + "spot_name");
        while(it.hasNext()){
            spotsModel spot = (spotsModel)it.next();
            System.out.println(" "+spot.getSpot_id() + " | " + spot.getUser_id()+ " | " + spot.getSpot_name());
        }


        connection.close();
        }



        public static  void displayDataInTabularFormat(){
          
        }

}
