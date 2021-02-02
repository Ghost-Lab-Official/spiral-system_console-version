package com.spiralSpotManagement.SearchModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SearchModule {
    private static CloudStorageConnection cloudstorageconnection = new CloudStorageConnection();


    public static void filterSearches(String category,String searchString) throws Exception {
        Statement stmt = cloudstorageconnection.getConnection().createStatement();
        System.out.println("Filtering category "+category);
        String sql = "SELECT * from Spot_table WHERE spot_name LIKE '"+searchString+"%'";
        String col1 = "spot_name";
        String col2 = "spot_description";

        if(category == "People"){
            sql = "SELECT * FROM users_table";
            col1="user_name";
            col2="email";
        }
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(col1) + ": " + rs.getString(col2));
        }
    }


    public static void printCategories() throws Exception {
        Scanner categoryScanner = new Scanner(System.in);
        System.out.println("******Categories*******");
        String[] categories = {"Spots","Reviews","People","Locations","all"};
        for (int i=0;i<categories.length;i++){
            System.out.println(i+1 + ". " +categories[i]);
        }

        System.out.print("Enter your choice: ");
        Integer choice = categoryScanner.nextInt();
        filterSearches(categories[choice-1],"Spot");
    }



    public static  void main(String[] args) throws Exception {
        System.out.println("Search module");
        printCategories();
//        System.out.println(cloudstorageconnection.getConnection());
    }
}
