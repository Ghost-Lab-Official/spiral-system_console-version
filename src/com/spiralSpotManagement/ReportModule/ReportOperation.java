package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ReportOperation {

    public static void getTheTotalNumberOfRegisteredSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table");
        while(rs.next()){
            System.out.println( "\t\t\t||  Number of registered spots :         "+ rs.getInt(1)+"||");
        }
        connection.close();


    }

    public static void getTheTotalNumberOfActiveSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status='active'");

        while(rs.next()){
            System.out.println( "\t\t\t||  Number of Active spots :         "+ rs.getInt(1)+"||");
        }
        connection.close();

    }

    public static void getTheTotalNumberOfInactiveSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status='inactive'");

        while(rs.next()){
            System.out.println( "\t\t\t||  Number of Inactive spots :         "+ rs.getInt(1)+"||");
        }
        connection.close();

    }
}
