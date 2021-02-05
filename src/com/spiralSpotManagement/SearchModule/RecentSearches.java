package com.spiralSpotManagement.SearchModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.sql.*;

public class RecentSearches {
    public static void viewAll(Connection con) throws SQLException {
        List resultArray = new ArrayList<String>();
        // this will hold the result from db in array to display recent searches
        // you will iterate from last elt and you display 10 searches at most
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select distinct searched_query,search_date from searchHistory where user_id =1 LIMIT 10");
        while (rs.next()) {
            String searcheq = rs.getString("searched_query");
            String madate = rs.getString("search_date");
            String concat = searcheq + " on " + madate;
            resultArray.add(concat);
        }
        Collections.reverse(resultArray);
        System.out.println(resultArray);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        // cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
        if (cloudStorageConnection.getConnection() != null) {

            do {
                System.out.println("\n\n");
                System.out.println("Review your recent searches\n");
                System.out.println("---------------------------");
                System.out.println("1.view all recent searches \n 2.Exit \n");
                System.out.println("Enter your choice");
                choice = Integer.parseInt(reader.readLine());

                switch (choice) {

                    case 1:
                        viewAll(cloudStorageConnection.getConnection());
                        break;
                    case 2:
                        System.out.println("program terminated");
                        break;
                    default:
                        System.out.println("the value you entered is wrong");
                        break;
                }
            } while (choice != 2);

        }

    }
}
