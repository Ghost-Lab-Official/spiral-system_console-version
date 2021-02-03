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
            String query= "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                    " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                    " users_table.user_name , Locations.locationName , spot_category.category_name from Spot_table " +
                    "left join users_table on Spot_table.spot_id= users_table.user_id" +
                    " left join Locations on Spot_table.location_id = Locations.locationId" +
                    " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                    "WHERE Spot_table.status ='active'";
            ResultSet result = stmt.executeQuery(query);


        ArrayList<spotsModel> ActiveSpotsList = new ArrayList<spotsModel>();


        while (result.next()) {

            spotsModel  myActiveSpot = new spotsModel(
                    result.getString("spot_id"),
                    result.getString("user_name"),
                    result.getString("category_name"),
                    result.getString("locationName"),
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
        System.out.println("\t\t\t  #Id" + "\t\t\t createdBy" +  "\t\t\t\t Entitled " +  "\t\t\t location" +  "\t\t\t category " +  "\t\t\t\t status " +  "\t\t\t\t views"+  "\t\t\t registrationDate ");
        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while(it.hasNext()){
            spotsModel spot = (spotsModel)it.next();
            System.out.println(" \t\t\t\t "+spot.getSpot_id() + " \t\t\t\t " + spot.getuser_name()+ " \t\t\t\t " + spot.getSpot_name()+ " \t\t\t\t " + spot.getLocationName()+ " \t\t\t\t " + spot.getCategory_name()+ " \t\t\t\t " + spot.getStatus()
                    + " \t\t\t\t " + spot.getViews()+ " \t\t\t\t " + spot.getRegistration_date());
        }
        connection.close();
        }


}
