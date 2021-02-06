package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserReportController {

    public static void numberOfAllRegisteredUsers() throws Exception, SQLException {

        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        String query= "SELECT count(*) AS TotalUsers FROM users_table";
        ResultSet result = stmt.executeQuery(query);
        result.next();

        int count = result.getInt("TotalUsers");
        result.close();

        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/USERS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||   Number of registered users:     " +count+ "     ||");


        connection.close();
    }

    public static void numberOfAllActiveUsers() throws Exception, SQLException {

        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        String query= "SELECT count(*) AS ActiveUsers FROM users_table WHERE user_status = 'active'";
        ResultSet result = stmt.executeQuery(query);
        result.next();

        int count = result.getInt("ActiveUsers");
        result.close();

        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/USERS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||   Number of Active users:      " +count+ "     ||");


        connection.close();
    }

    public static  void numberOfAllInactiveUsers() throws Exception {

        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection = cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT count(*) AS InactiveUsers FROM users_table WHERE user_status = 'inactive'";
        ResultSet result = stmt.executeQuery(query);
        result.next();

        int count = result.getInt("InactiveUsers");
        result.close();

        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/USERS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||   Number of Inactive users:    " + count + "     ||");


        connection.close();

    }

}
