package com.spiralSpotManagement.SearchModule;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/*
  @author Names: - MUGISHA ISAAC
                 - ISHIMWE PAULINE
  final date: on 5/02/2021
  comment: This is a class called RecentSearches containing a method called viewAll which takes a connection of database
           as its parameter and selects all  recent searches from searchHistory table. this method reverses those searched_queries
           so as to order them by latest. it also checks if there is duplicate of queries and then returns only one if it found many same values on the same date.
           The method also prints only 10 values.
  So, this was our task, just to print the recent searched_queries.
           Thank you!!!
*/
public class RecentSearches {
    public static void viewAll(Connection con) throws SQLException {
        ArrayList<String> resultArray = new ArrayList<String>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select distinct searched_query,search_date from searchHistory where user_id =1 LIMIT 10");
        while (rs.next()) {
            String searchedQuery = rs.getString("searched_query");
            String queryDate = rs.getString("search_date");
            String concatenatedString = "\n"+ searchedQuery + " on " + queryDate;
            System.out.println("\n");
            resultArray.add(concatenatedString);
        }
        System.out.println("\n");
        Collections.reverse(resultArray);
        System.out.println(resultArray);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        // cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
        if (cloudStorageConnection.getConnection() != null) {

            do {
                System.out.println("\n\n");
                System.out.println("Review your recent searches\n");
                System.out.println("---------------------------");
                System.out.println(" 1.view all recent searches \n 2.Exit \n");
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
