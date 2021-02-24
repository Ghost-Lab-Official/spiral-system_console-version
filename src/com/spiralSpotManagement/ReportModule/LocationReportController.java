package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author MANZI Mike
 * @description This class contains the methods related on locations reports
 */
public class LocationReportController {
   /**
    * @author MANZI Mike
    * @description This is a method to print all registered locations
    * @throws Exception
   */
   public static void viewAllLocations() throws Exception {
       try{
           CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
           Connection connection= cloudStorageConnection.getConnection();
           Statement stmt = connection.createStatement();
           ResultSet resultSet = stmt.executeQuery("SELECT locationId, location_name, location_GPS, description," +
                   " status FROM locations");

           ArrayList<LocationModel> AllLocations = new ArrayList<LocationModel>();
           while(resultSet.next()) {
               LocationModel location = new LocationModel(
                       resultSet.getString("locationId"),
                       resultSet.getString("location_name"),
                       resultSet.getString("location_GPS"),
                       resultSet.getString("description"),
                       resultSet.getString("status")
               );
               AllLocations.add(location);
           }

           Iterator it = AllLocations.iterator();
           System.out.println("Id\t Location Name\t Coordinates\t Description\t Status");
           System.out.println("-----------------------------------------------------------------------");
           while (it.hasNext()){
               LocationModel location = (LocationModel) it.next();
               System.out.println(location.getLocationId()+"\t"+location.getLocation_name()+"\t"+
                       location.getLocation_GPS()+"\t"+ location.getDescription()+"\t"+location.getStatus());
           }
           connection.close();
       }catch (Exception e) {
           System.out.println("Error: "+e.getMessage());
       }
   }

    /**
     * @author MANZI Mike
     * @description This is a method to print locations depending on their status
     * @throws Exception
     */
   public static void viewLocationsByStatus(String val) throws Exception {


       try{
           CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
           Connection connection= cloudStorageConnection.getConnection();
           Statement stmt = connection.createStatement();
           ResultSet resultSet = stmt.executeQuery("SELECT locationId, location_name, location_GPS, description," +
                   " status FROM locations WHERE status = '"+val+"'");
           ArrayList<LocationModel> AllLocations = new ArrayList<LocationModel>();
           while(resultSet.next()) {
               LocationModel location = new LocationModel(
                       resultSet.getString("locationId"),
                       resultSet.getString("location_name"),
                       resultSet.getString("location_GPS"),
                       resultSet.getString("description"),
                       resultSet.getString("status")
               );
               AllLocations.add(location);
           }

           Iterator it = AllLocations.iterator();
           System.out.println("Id\t Location Name\t Coordinates\t Description\t Status");
           System.out.println("-----------------------------------------------------------------------");
           while (it.hasNext()){
               LocationModel location = (LocationModel) it.next();
               System.out.println(location.getLocationId()+"\t"+location.getLocation_name()+"\t"+
                       location.getLocation_GPS()+"\t"+ location.getDescription()+"\t"+location.getStatus());
           }
           connection.close();
       }catch (Exception e) {
           System.out.println("Error: "+e.getMessage());
       }
   }

    /**
     * @author MANZI Mike
     * @description This is a method to print the number of registered locations
     * @throws Exception
     */

   public static void totalNumberOfRegisteredLocations() throws Exception {
       try{
           CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
           Connection connection= cloudStorageConnection.getConnection();
           Statement stmt = connection.createStatement();
           ResultSet resultSet = stmt.executeQuery("SELECT count(location_name) from locations");
           while(resultSet.next()){
               System.out.println( "\t\t\t  Number of Registered Locations :         "+ resultSet.getInt(1)+"");
           }
           connection.close();
       }catch (Exception e) {
           System.out.println("Error: "+e.getMessage());
       }
   }

    /**
     * @author MANZI Mike
     * @description This is a method to print the number of locations depending on their status
     * @throws Exception
     */
   public static void totalNumberOfLocationsByStatus(String val) throws Exception {
       try{
           CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
           Connection connection= cloudStorageConnection.getConnection();
           Statement stmt = connection.createStatement();
           ResultSet resultSet = stmt.executeQuery("SELECT count(location_name) from locations WHERE status = " +
                   "'"+val+"'");
           while(resultSet.next()){
               System.out.println( "\t\t\t  Number of "+val+" Locations :         "+ resultSet.getInt(1)+"");
           }
           connection.close();
       }catch (Exception e) {
           System.out.println("Error: "+e.getMessage());
       }
   }

    /**
     * @author MANZI Mike
     * @description This is a method to get spots depending on their locations
     * @throws Exception
     */
   public static void getSpotsByLocation() throws Exception{
       try{
           Scanner scan = new Scanner(System.in);
           CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
           Connection connection= cloudStorageConnection.getConnection();
           Statement stmt = connection.createStatement();
           System.out.println("Enter a location");
           String location = scan.nextLine();
           ResultSet resultSet = stmt.executeQuery("SELECT Spot_table.spot_id,users_table.user_name,spot_category.category_name,"+
                   "Spot_table.spot_name,locations.location_name,Spot_table.spot_description,Spot_table.views,Spot_table.status," +
                   "Spot_table.registration_date "+"FROM `Spot_table` LEFT JOIN users_table ON users_table.user_id = Spot_table.user_id " +
                   "LEFT JOIN spot_category ON "+"spot_category.category_id = Spot_table.category_id LEFT JOIN locations on " +
                   "locations.location_id = Spot_table.location_id WHERE locations.location_name = '"+location+"'");
           printSpots(resultSet);
       }catch (Exception e) {
           System.out.println("Error: "+e.getMessage());
       }
   }

    /**
     * @author MANZI Mike
     * @description This is a method to print spots depending on their locations
     * @throws Exception
     */
   public static void printSpots(ResultSet resultSet) throws Exception{
       try{
           CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
           Connection connection= cloudStorageConnection.getConnection();
           ArrayList<spotsModel> AllSpots = new ArrayList<spotsModel>();

           while (resultSet.next()) {

               spotsModel  mySpots = new spotsModel(
                       resultSet.getString("spot_id"),
                       resultSet.getString("user_name"),
                       resultSet.getString("category_name"),
                       resultSet.getString("location_name"),
                       resultSet.getString("spot_name"),
                       resultSet.getString("spot_description"),
                       resultSet.getDouble("views"),
                       resultSet.getString("status"),
                       resultSet.getDate("registration_date")
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
       }catch (Exception e) {
           System.out.println("Error: "+e.getMessage());
       }
   }
}
