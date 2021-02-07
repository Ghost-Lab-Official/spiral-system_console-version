package com.spiralSpotManagement.SearchModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

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
        ResultSet rs = stmt.executeQuery("select DISTINCT searched_query,search_date,history_id from searchHistory where user_id =2");
        while (rs.next()) {
            String searchedQuery = rs.getString("searched_query");
            String queryDate = rs.getString("search_date");
            int id = rs.getInt("history_id");
            String concatenatedString = "\n"+ id +"." + " " + searchedQuery + " on " + queryDate;
            System.out.println("\n");
            resultArray.add(concatenatedString);
        }
        System.out.println("\n");
        Collections.reverse(resultArray);
        System.out.println(resultArray);
    }

    /*
      @author: MUGISHA ISAAC
      Date: on 7 Feb 2021
      comment: This is a method called deleteRecent() which deletes a given query from the list
      of history. this method takes connection to db and the query_id to delete as its parameters
      and deletes from history list where a history_id is the same as the query_id to delete.
      So, this accomplishes the task we were given by our group leader @Blessing
    */

    public static  void deleteRecent(Connection con, int query_id) throws  SQLException{
     try{
         PreparedStatement stmt = con.prepareStatement("DELETE FROM searchHistory WHERE history_id=?");
         stmt.setInt(1, query_id);
         boolean records = stmt.execute();
       if(records==false){
           System.out.println("Query deleted successfully"+ " "+ "200");
       }
       else{
           System.out.println("Deletion Failed"+ " "+ "404");
       }
     }
     catch(Exception ex){
         System.out.println("Message:" + " "+ ex.getMessage());
     }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        int choice;
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        // cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
        if (cloudStorageConnection.getConnection() != null) {

            do {
                System.out.println("\n\n");
                System.out.println("Review your recent searches\n");
                System.out.println("---------------------------");
                System.out.println(" 1.view all recent searches \n 2.Delete a query from history \n 3. Exit ");
                System.out.println("Enter your choice");
                choice = Integer.parseInt(reader.readLine());
                int query_id;
                switch (choice) {

                    case 1:
                        viewAll(cloudStorageConnection.getConnection());
                        break;
                    case 2:
                        System.out.println("Enter Query id ");
                        query_id = scanner.nextInt();
                        deleteRecent(cloudStorageConnection.getConnection(),query_id);
                        break;
                    case 3:
                        System.out.println("program terminated");
                        break;
                    default:
                        System.out.println("the value you entered is wrong");
                        break;
                }
            } while (choice != 3);

        }

    }
}
