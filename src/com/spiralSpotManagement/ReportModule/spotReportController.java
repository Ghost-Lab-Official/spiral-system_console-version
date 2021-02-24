package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.ReportModule.spotsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class spotReportController {

    /**
     * @author Ntezirizaza Erneste
     * description This method generates the number of highly visited spots
     * @throws Exception
     */

    public static void getTheTotalNumberOfHighlyVisitedSpots() throws Exception {
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table  where views>10 ");
        while(rs.next()){
            System.out.println( "\t\t\t  Number of highly visited spots :         "+ rs.getInt(1)+"");
        }
        connection.close();

    }



    /**
     * @author Ntezirizaza Erneste
     * @description This method generates the number of registered spots
     * @throws Exception
     */

    public static void getTheTotalNumberOfRegisteredSpots() throws Exception {
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table");
        while(rs.next()){
            System.out.println( "\t\t\t  Number of registered spots :         "+ rs.getInt(1)+"");
        }
        connection.close();

    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method generates the number of active spots
     * @throws Exception
     */
    public static void getTheTotalNumberOfActiveSpots() throws Exception {
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status=1");

        while(rs.next()){
            System.out.println( "\t\t\t  Number of Active spots :         "+ rs.getInt(1)+"");
        }
        connection.close();

    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method generates the number of inactive spots
     * @throws Exception
     */

    public static void getTheTotalNumberOfInactiveSpots() throws Exception {
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status=0");

        while(rs.next()){
            System.out.println( "\t\t\t  Number of Inactive spots :         "+ rs.getInt(1)+"");
        }
        connection.close();
    }





    public static void viewAllSpots() throws Exception {
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Spot_table.spot_id,users_table.user_name,spot_category.category_name,"+
                "Spot_table.spot_name,locations.location_name,Spot_table.spot_description,Spot_table.views,Spot_table.status," +
                "Spot_table.registration_date "+"FROM `Spot_table` LEFT JOIN users_table ON users_table.user_id = Spot_table.user_id " +
                "LEFT JOIN spot_category ON "+"spot_category.category_id = Spot_table.category_id LEFT JOIN locations on " +
                "locations.locationId = Spot_table.location_id"
        );

        ArrayList<spotsModel> AllSpots = new ArrayList<spotsModel>();

        while (rs.next()) {

            spotsModel  mySpots = new spotsModel(
                    rs.getString("spot_id"),
                    rs.getString("user_name"),
                    rs.getString("category_name"),
                    rs.getString("location_name"),
                    rs.getString("spot_name"),
                    rs.getString("spot_description"),
                    rs.getDouble("views"),
                    rs.getString("status"),
                    rs.getDate("registration_date")
            );
            AllSpots.add(mySpots);
        }
        Iterator it = AllSpots.iterator();
        System.out.println("\t\t\t  #Id" + "\t\t\t Creator" +  "\t\t\t\t Category " +  "\t\t\t Location" +  "\t\t\t Spot Name " +
                "\t\t\t\t Spot Description" +"\t\t\t\t Views" +  "\t\t\t\t Status" +  "\t\t\t RegistrationDate ");
        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while(it.hasNext()){
            spotsModel spot = (spotsModel)it.next();
            System.out.println(" \t\t\t\t "+spot.getSpot_id() + " \t\t\t\t " + spot.getuser_name()+ " \t\t\t\t " + spot.getCategory_name()+
                    " \t\t\t\t " + spot.getLocationName()+ " \t\t\t " + spot.getSpot_name() +" \t\t\t\t " +spot.getSpot_description()
                    + " \t\t\t\t "+ spot.getViews()+" \t\t\t\t " + spot.getStatus()+ " \t\t\t\t " + spot.getRegistration_date());
        }
        connection.close();
    }


    public static void viewAllActiveSpots() throws Exception {

        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        String query= "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                "left join users_table on Spot_table.spot_id= users_table.user_id" +
                " left join locations on Spot_table.location_id = locations.locationId" +
                " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                "WHERE Spot_table.status =1";
        ResultSet result = stmt.executeQuery(query);


        ArrayList<spotsModel> ActiveSpotsList = new ArrayList<spotsModel>();


        while (result.next()) {
            spotsModel  myActiveSpot = new spotsModel(
                    result.getString("spot_id"),
                    result.getString("user_name"),
                    result.getString("category_name"),
                    result.getString("location_name"),
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

        Iterator iterator = ActiveSpotsList.iterator();
        System.out.println("\t\t\t  #Id" + "\t\t\t\t createdBy" +  "\t\t\t\t  Entitled " +  "\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while(iterator.hasNext()){
            spotsModel spot = (spotsModel)iterator.next();
            System.out.println(" \t\t\t\t "+spot.getSpot_id() + " \t\t\t\t " + spot.getuser_name()+ " \t\t\t\t " + spot.getSpot_name()+ " \t\t\t\t " + spot.getLocationName()+ " \t\t\t\t " + spot.getCategory_name()+ " \t\t\t\t " + spot.getStatus()
                    + " \t\t\t\t " + spot.getViews()+ " \t\t\t\t " + spot.getRegistration_date());
        }
        connection.close();
    }


    //    Inactive spots and highly visited spots created by Ange Nicole
//    The Vector class implements a growable array of objects. Vectors basically fall in legacy classes
//    but now it is fully compatible with collections. It is found in the java.util package and implements
//    the List interface, so we can use all the methods of List interface here.

    public static void viewAllInactiveSpots() throws Exception{
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection= cloudStorageConnection.getConnection();



        Statement stmnt = connection.createStatement();
        String squery= "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                " users_table.user_name , locations.locationName , spot_category.category_name from Spot_table " +
                "left join users_table on Spot_table.spot_id= users_table.user_id" +
                " left join locations on Spot_table.location_id = locations.locationId" +
                " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                "WHERE Spot_table.status =0";
        ResultSet result = stmnt.executeQuery(squery);

        Vector<spotsModel> ActiveSpotsList = new Vector<spotsModel>();


        while (result.next()) {

            spotsModel  inactiveSpots = new spotsModel(
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
            ActiveSpotsList.add(inactiveSpots);
        }

        Iterator iterate = ActiveSpotsList.iterator();
        System.out.println("\t\t\t  #Id" + "\t\t\t createdBy" +  "\t\t\t\t Entitled " +  "\t\t\t location" +  "\t\t\t category " +
                "\t\t\t\t status " +  "\t\t\t\t views"+  "\t\t\t registrationDate ");
        System.out.println("\t\t---------------------------------------------------------------------------------------------------" +
                "----------------------------------------------------------------------");
        while(iterate.hasNext()){
            spotsModel inactspot = (spotsModel)iterate.next();
            System.out.println(" \t\t\t\t "+inactspot.getSpot_id() + " \t\t\t\t " + inactspot.getuser_name()+ " \t\t\t\t " + inactspot.getSpot_name()+
                    " \t\t\t\t " + inactspot.getLocationName()+ " \t\t\t\t " + inactspot.getCategory_name()+ " \t\t\t\t " + inactspot.getStatus()
                    + " \t\t\t\t " + inactspot.getViews()+ " \t\t\t\t " + inactspot.getRegistration_date());
        }
        connection.close();
    }

    public static void ViewHighlyVisitedSpots() throws Exception{
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        Connection connection = cloudStorageConnection.getConnection();

        Statement stment = connection.createStatement();
        String querry = "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                " users_table.user_name , locations.locationName , spot_category.category_name from Spot_table " +
                "left join users_table on Spot_table.spot_id= users_table.user_id" +
                " left join locations on Spot_table.location_id = locations.locationId" +
                " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                "WHERE Spot_table.status =1 AND Spot_table.views > 10";
        ResultSet resultset = stment.executeQuery(querry);

        Vector<spotsModel> ActiveSpotsList = new Vector<spotsModel>(2);


        while (resultset.next()) {

            spotsModel  highlyViewed = new spotsModel(
                    resultset.getString("spot_id"),
                    resultset.getString("user_name"),
                    resultset.getString("category_name"),
                    resultset.getString("locationName"),
                    resultset.getString("spot_name"),
                    resultset.getString("spot_description"),
                    resultset.getDouble("views"),
                    resultset.getString("status"),
                    resultset.getDate("registration_date")
            );
            ActiveSpotsList.add(highlyViewed);
        }

        Iterator itrt = ActiveSpotsList.iterator();
        System.out.println("\t\t\t  #Id" + "\t\t\t createdBy" +  "\t\t\t\t Entitled " +  "\t\t\t location" +  "\t\t\t category " +
                "\t\t\t\t status " +  "\t\t\t\t views"+  "\t\t\t registrationDate ");
        System.out.println("\t\t---------------------------------------------------------------------------------------------------" +
                "----------------------------------------------------------------------");
        while(itrt.hasNext()){
            spotsModel highlyViewed = (spotsModel)itrt.next();
            System.out.println(" \t\t\t\t "+highlyViewed.getSpot_id() + " \t\t\t\t " + highlyViewed.getuser_name()+ " \t\t\t\t " + highlyViewed.getSpot_name()+
                    " \t\t\t\t " + highlyViewed.getLocationName()+ " \t\t\t\t " + highlyViewed.getCategory_name()+ " \t\t\t\t " + highlyViewed.getStatus()
                    + " \t\t\t\t " + highlyViewed.getViews()+ " \t\t\t\t " + highlyViewed.getRegistration_date());
        }
        connection.close();
    }

}
