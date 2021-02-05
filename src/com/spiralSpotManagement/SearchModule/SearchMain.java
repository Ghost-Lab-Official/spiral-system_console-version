package com.spiralSpotManagement.SearchModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.io.IOException;
import java.sql.Statement;
import java.util.Scanner;

public class SearchMain {
    public static CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
    public static Scanner scanner = new Scanner(System.in);
    //static Statement stmt = cloudStorageConnection.getConnection().createStatement();



    public  static void searchCategories(){

     int searchCategory;
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|-----------------       SPIRAL SEARCH        -------------------|");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|---------------    Categories to use in search    --------------|");
        System.out.println("|------------------          1.Spots               --------------|");
        System.out.println("|------------------          2.Messages            --------------|");
        System.out.println("|------------------          3.Users               --------------|");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|  Enter  category of your search to use                                     |");
        searchCategory = scanner.nextInt();
 }


    /*This is the entry of search module. It is the main function for search module where all function
     * are being called from the SearchModule class
     * @Bethiane*/

     public static void main(String[] args) {
        int choice;
        SearchModule searchModuleMethods =new SearchModule();
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|-----------------     WELCOME TO SPIRAL      -------------------|");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|---------------   Methods to use while searching  --------------|");
        System.out.println("|------------------     1.By categories        ------------------|");
        System.out.println("|------------------     2.By text              ------------------|");
        System.out.println("|------------------     3.By popularity        ------------------|");
        System.out.println("|------------------     4.By recent search     ------------------|");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|  Enter  method to use                                     |");
        choice = scanner.nextInt();
        System.out.println(choice);

        switch (choice) {
            case 1:
                searchCategories();
            case 2:
                //searchModuleMethods.popularityByRatesArray(stmt);
            case 3:
                System.out.println("Ndaje");
                //searchPopular();
            case 4:
                System.out.println("Ndaje");
                // searchRecents();
            default:
                return;

        }
    }
}
