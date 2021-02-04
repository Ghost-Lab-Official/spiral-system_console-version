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
        ResultSet rs = stmt.executeQuery("select searched_query,search_date from searchHistory where user_id =1");
        while (rs.next()) {
            String searcheq = rs.getString("searched_query");
            String madate = rs.getString("search_date");
            String concat = searcheq + " on " + madate;
            resultArray.add(concat);
            // System.out.println("search query : " + searcheq+" on the date : " + madate);

        }
        System.out.println(resultArray);
        System.out.println("after reverse");
        Collections.reverse(resultArray);
        System.out.println(resultArray);
        // for(int i= resultArray.size()-1; i>=0; i--){
        // System.out.println(resultArray.get(i));
        // }

    }

    /*
     * @author: Mugisha Isaac method that prints the most searched spot by a
     * loggedin user when a user login and wants to search we can propose him some
     * queries he used before so that if he needs to use the same query, there is no
     * need of entering it again instead he will choose it and we use the same query
     * to search for relevant information
     * 
     */
    public static void viewRecentOfMostSearched(Connection con) throws SQLException {
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(
                    "select searched_query, count(*) AS times from `searchHistory` group by searched_query order by times desc LIMIT 10");
            System.out.println("Recent Most Searched Spot By Their Order");
            System.out.println("----------------------------------------");
            System.out.println("Number\t\t Query \t\t\t\t\t Times");
            int i = 1;
            while (res.next()) {
                String query = res.getString("searched_query");
                int times = res.getInt("times");
                System.out.println(i + "\t\t\t" + query + "\t\t\t" + times);
                i++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*
     * @author: Mugisha Isaac method to print recents for an non-loggedin user. When
     * a user don't yet have an account we can propose some mostly used queries.
     * Right now, i am giving those recents by printing all queris restricting to 10
     * but later we will use ip address to print those found in the same location as
     * that of the user
     */

    public static void viewRecentForUnauthonticatedUser(Connection con) throws SQLException {
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(
                    "SELECT searched_query FROM searchHistory WHERE searched_query LIKE '%a%' || searched_query LIKE '%i%' || searched_query LIKE'%u%' || searched_query LIKE '%o%' || searched_query Like '%e%' LIMIT 10");
            System.out.println("Recent queries");
            System.out.println("--------------");
            System.out.println("Number\t\t Query");
            int i = 1;
            while (res.next()) {
                String query = res.getString("searched_query");
                System.out.println(i + "\t\t" + query);
                i++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
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
                System.out.println(
                        "1.view all recent searches \n 2.view most searched spots \n 3.view recent for non-logged in user \n 4.Exit ");
                System.out.println("Enter your choice");
                choice = Integer.parseInt(reader.readLine());

                switch (choice) {

                    case 1:
                        viewAll(cloudStorageConnection.getConnection());
                        break;
                    case 2:
                        viewRecentOfMostSearched(cloudStorageConnection.getConnection());
                        break;
                    case 3:
                        viewRecentForUnauthonticatedUser(cloudStorageConnection.getConnection());
                        break;
                    case 4:
                        System.out.println("program terminated");
                        break;
                    default:
                        System.out.println("the value you entered is wrong");
                        break;
                }
            } while (choice != 4);

        }

    }
}
