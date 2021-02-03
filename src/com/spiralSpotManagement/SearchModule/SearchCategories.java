package com.spiralSpotManagement.SearchModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Statement;
import java.util.Scanner;


public class SearchCategories {
    public static CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();

    public static void main(String[] args) throws Exception{
        Statement stmt = cloudStorageConnection.getConnection().createStatement();

        Filter filter = new Filter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||                SEARCH OPTIONS           || ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  SEARCH SPOT                         ||");
        System.out.println("\t\t\t|| 2.  SEARCH PEOPLE                       ||");
        System.out.println("\t\t\t|| 3.  SEARCH MESSAGE                      ||");
        System.out.println("\t\t\t============================================= ");
        System.out.print("Enter Your choice: ");
        int option = scanner.nextInt();

        switch(option){
            case 1 -> filter.spotFilter(stmt);
            case 2 -> filter.peopleFilter();
            case 3 -> filter.messageFilter();
            default -> System.out.println("Invalid option");
        }
    }
}
