package com.spiralSpotManagement.SearchModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.sql.*;

public class RecentSearches {
    public static void viewAll(Connection con) throws SQLException {
     List resultArray= new ArrayList<String>();
// this will hold the result from db in array to display recent searches
// you will iterate from last elt and you display 10 searches at most
        Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select searched_query,search_date from searchHistory where user_id =1");
while(rs.next()){
String searcheq=rs.getString( "searched_query");
String madate = rs.getString("search_date");
String concat=searcheq+" on "+ madate;
   resultArray.add(concat);
//   System.out.println("search query : " + searcheq+" on the date  : " + madate);

}
        System.out.println(resultArray);
        System.out.println("after reverse");
            Collections.reverse(resultArray);
    System.out.println(resultArray);
        //    for(int i= resultArray.size()-1; i>=0; i--){
//        System.out.println(resultArray.get(i));
//    }

    }

     // author @Mugisha Isaac
    // method that prints the most searched spot by a loggedin user
    public static void viewRecentOfMostSearched(Connection con) throws  SQLException{
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select searched_query, count(*) AS times from `searchHistory` group by searched_query order by times desc");
            System.out.println("Recent Most Searched Spot By Their Order");
            System.out.println("----------------------------------------");
            System.out.println("Number\t\t Query \t\t\t\t\t Times");
            int i = 1;
            while (res.next()) {
                String query = res.getString("searched_query");
                int times = res.getInt("times");
                System.out.println(i + "\t\t\t" + query + "\t\t\t"+times);
                i++;
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }


    // author Mugisha Isaac
    // method to print recents for an non-loggedin user

//    public static  void viewRecentForUnauthonticatedUser(Connection con,String ipaddress) throws  SQLException{
//        // using ip address to give most searched spots in that area
//        try{
//          String query = "SELECT searhed_query,max()  "
//          Statement stm = con.createStatement();
//          ResultSet res = stm.executeQuery(query);
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//    }


    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        System.out.println("Review your recent searches\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
        if(cloudStorageConnection.getConnection() !=null){

            do {
            System.out.println("1.view all recent searches \n 2.view most searched spots \n 3.Exit \n");
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
            System.out.println("Program executed");
            break;
        default:
            System.out.println("the value you entered is wrong");
            break;

    }
        }while( choice!=2);

        }


    }
}
