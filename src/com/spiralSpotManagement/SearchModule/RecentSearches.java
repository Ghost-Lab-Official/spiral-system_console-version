package com.spiralSpotManagement.SearchModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import java.util.*;

import java.io.*;
import java.sql.*;
public class RecentSearches {
    public static void viewAll(Connection con) throws SQLException {
//        List resultArray= new ArrayList<String>();
// this will hold the result from db in array to display recent searches
// you will iterate from last elt and you display 10 searches at most
        Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select searched_query,search_date from searchHistory where user_id =1");
while(rs.next()){
String searcheq=rs.getString( "searched_query");
String madate = rs.getString("search_date");

    System.out.println("search query : " + searcheq);
    System.out.println("the date of the query : " + madate);

}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        System.out.println("Review your recent searches\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
        if(cloudStorageConnection.getConnection() !=null){

            do {
            System.out.println("1.view all recent searches \n 2.Exit \n");
            System.out.println("Enter your choice");
                choice = Integer.parseInt(reader.readLine());

    switch (choice) {

        case 1:
            viewAll(cloudStorageConnection.getConnection());
            break;

        default:
            System.out.println("the value you entered is wrong");
            break;

    }

        }while( choice!=2);

        }


    }
}
