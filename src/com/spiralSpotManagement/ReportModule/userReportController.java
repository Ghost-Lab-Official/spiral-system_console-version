package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.lang.*;
import java.io.*;

import static com.spiralSpotManagement.ReportModule.Navigation.view;

public class userReportController {

    /**
     * @author Ntezirizaza Erneste
     * @description This method shows the number of all registered users
     * @throws Exception
     */

    public static void getTheTotalNumberOfAllUsersRegistered() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM users_table");

        while(rs.next()){
                System.out.println( "\t\t\t  Number of Users Registered :         "+ rs.getInt(1)+"");
        }
        connection.close();

    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method shows the number of all users according to their statuses
     * @param statusValue is taken by this method as a parameter
     * @throws Exception
     */

    public static void getTheTotalNumberOfAllUsersByStatus(String statusValue) throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM users_table WHERE user_status = '"+statusValue+"'");

        while(rs.next()){
            if(statusValue == "active")
                System.out.println( "\t\t\t  Number of Active Users :         "+ rs.getInt(1)+"");
            else if(statusValue == "disabled")
                System.out.println( "\t\t\t  Number of Disabled Users :         "+ rs.getInt(1)+"");
            else if(statusValue == "guest")
                System.out.println( "\t\t\t  Number of Disabled Users :         "+ rs.getInt(1)+"");
            else
                System.out.println( "Invalid status!");
        }
        connection.close();

    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method shows the list of all registered users
     * @throws Exception
     */
    public static void viewAllUsers() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT users_table.user_id , users_table.first_name , users_table.first_name ," +
                        " users_table.last_name , users_table.user_name  , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , " +
                        " users_categories.user_category from users_table " +
                        "left join users_categories on users_table.user_id=users_categories.category_id"
        );
        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
        while (rs.next()) {
            usersModel  myUsers = new usersModel(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getDate("birth_date"),
                    rs.getString("user_category"),
                    rs.getString("location"),
                    rs.getString("user_status"),
                    rs.getDate("registration_date")
                    );
            AllUsers.add(myUsers);
        }
        Iterator it = AllUsers.iterator();

        System.out.println("\n THE RESULT FROM THE SELECTED TABLE");

        System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

        System.out.println(String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","Birth date","User category","Location name","User status","Registration date"));
        System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

        while(it.hasNext()){
            usersModel user = (usersModel)it.next();
            System.out.println(
                    String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
                            user.getUserId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getUserName(),
                            user.getEmail(),
                            user.getGender(),
                            user.getBirthDate(),
                            user.getUserCategoryName(),
                            user.getLocationName(),
                            user.getUserStatus(),
                            user.getRegistrationDate())
            );
        }
        connection.close();
    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method shows the list of all users according to their statuses
     * @param statusValue is taken by this method as a parameter
     * @throws Exception
     */

    public static void viewAllUsersByStatus(String statusValue) throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();

        String query = "SELECT users_table.user_id , users_table.first_name , users_table.first_name ,users_table.last_name , users_table.user_name , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , users_categories.user_category from users_table left join users_categories " +
                "on users_table.user_category=users_categories.category_id where user_status ='"+statusValue+"'";

        ResultSet rs = stmt.executeQuery(query);
        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
        while (rs.next()) {
            usersModel  myUsers = new usersModel(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getDate("birth_date"),
                    rs.getString("user_category"),
                    rs.getString("location"),
                    rs.getString("user_status"),
                    rs.getDate("registration_date")
            );
            AllUsers.add(myUsers);
        }
        Iterator it = AllUsers.iterator();

        view();

        System.out.println("\n THE RESULT FROM THE SELECTED TABLE");

        System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

        System.out.println(String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","Birth date","User category","Location name","User status","Registration date"));
        System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

        while(it.hasNext()){
            usersModel user = (usersModel)it.next();
            System.out.println(
                    String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
                            user.getUserId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getUserName(),
                            user.getEmail(),
                            user.getGender(),
                            user.getBirthDate(),
                            user.getUserCategoryName(),
                            user.getLocationName(),
                            user.getUserStatus(),
                            user.getRegistrationDate())
            );
        }
        connection.close();
    }
}
