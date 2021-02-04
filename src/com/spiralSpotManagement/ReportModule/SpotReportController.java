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

public class SpotReportController {

    /**
     * @author Ntezirizaza Erneste
     * description This method generates the number of highly visited spots
     * @throws Exception
     */

    public static void getTheTotalNumberOfHighlyVisitedSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table  where views>10 ");
        while(rs.next()){
            System.out.println( "\t\t\t||  Number of highly visited spots :         "+ rs.getInt(1)+"||");
        }
        connection.close();

    }



    /**
     * @author Ntezirizaza Erneste
     * @description This method generates the number of registered spots
     * @throws Exception
     */

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

    /**
     * @author Ntezirizaza Erneste
     * @description This method generates the number of active spots
     * @throws Exception
     */

    public static void getTheTotalNumberOfActiveSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status=1");

        while(rs.next()){
            System.out.println( "\t\t\t||  Number of Active spots :         "+ rs.getInt(1)+"||");
        }
        connection.close();

    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method generates the number of inactive spots
     * @throws Exception
     */

    public static void getTheTotalNumberOfInactiveSpots() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status=0");

        while(rs.next()){
            System.out.println( "\t\t\t||  Number of Inactive spots :         "+ rs.getInt(1)+"||");
        }
        connection.close();
    }
}
