package com.spiralSpotManagement.LocationModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import com.spiralSpotManagement.Main;
import java.sql.*;
import java.util.*;
/*
* Added a method to get parent location from the user input
* Added a method to return locations depending on the specified parent
* Added by Landrada
* */
public class LocationModule extends CloudStorageConnection {
    public String getParentFromUser(){
        Scanner parentScanner = new Scanner(System.in);
        System.out.println("Enter the parent location name : ");
        String parent=parentScanner.nextLine();
        return parent;
    }
    public void fetchByParent(String parent) throws Exception {

        ResultSet result1,result2;
        String locId = null;
        String child;
        Connection connection = getConnection();
        String query1 = "select locationId,locationName from Locations where locationName ='"+parent+"'";
        Statement selectStmt1 = connection.createStatement();
        Statement selectStmt = connection.createStatement();
        result1 = selectStmt.executeQuery(query1);
        while (result1.next()){
            locId = result1.getString("locationId");
        }
        String query = "select locationName from Locations where parentId ='"+locId+"'";
        result2 = selectStmt1.executeQuery(query);
        System.out.println("Locations in "+parent+": ");
        while(result2.next()){
            child = result2.getString("locationName");
            System.out.println("\t*"+child+"\n");
        }
    }
    public static void main(String[] args){
        try {
        LocationModule locationModule = new LocationModule();
        String parent = locationModule.getParentFromUser();
        locationModule.fetchByParent(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
