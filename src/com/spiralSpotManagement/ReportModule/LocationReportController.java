package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

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
           CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
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
           CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
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
           CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
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
           CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
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
}
