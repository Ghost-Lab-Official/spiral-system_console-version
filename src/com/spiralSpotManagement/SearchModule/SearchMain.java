package com.spiralSpotManagement.SearchModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import java.sql.Statement;
import java.util.Scanner;


public class SearchMain {
    public static Scanner scanner = new Scanner(System.in);


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
    }


    /*This is the entry of search module. It is the main function for search module where all function
     * are being called from the SearchModule class
     * @Bethiane*/

    public static void main(String[] args) throws Exception {
        int choice;
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Statement stmt = cloudStorageConnection.getConnection().createStatement();
        SearchModule searchModuleMethods = new SearchModule();

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
                searchModuleMethods.SearchByCategory(stmt);
                break;
            case 2:
                System.out.println("Here");
                //searchText();
                break;
            case 3:
                searchModuleMethods.popularityEntry();
                break;

            case 4:
                searchModuleMethods.showRecentSearchDialog(stmt);
                break;
            default:
                break;


        }
    }
}
