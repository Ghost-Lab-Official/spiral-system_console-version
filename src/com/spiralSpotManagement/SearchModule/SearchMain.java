package com.spiralSpotManagement.SearchModule;
import java.util.Scanner;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

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
        searchCategory = scanner.nextInt();
    }

    public static void main(String[] args) {
        int choice;
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        SearchModule search2 = new SearchModule();
//        SearchByPopularity popularSpots = new SearchByPopularity();

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

                search2.tester();
                break;
            case 2:
                System.out.println("Ndaje");
                //searchText();
                break;
            case 3:
                System.out.println("Ndaje");
                break;

            case 4:
                System.out.println("Ndaje");
                // searchRecents();
                break;
            default:
                break;


        }
    }
}
