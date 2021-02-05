package com.spiralSpotManagement.SearchModule;
import java.sql.Statement;
import java.util.Scanner;
import static com.spiralSpotManagement.SearchModule.SearchCategories.cloudStorageConnection;



//this is the main class of the search module where all methods will go

public class SearchModule {
//	public void tester(){
//        System.out.println("we're testing this class");
//    }
//    Statement stmt;
//
//    {
//        try {
//            stmt = cloudStorageConnection.getConnection().createStatement();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void SearchByCategory() {

        try (Scanner scanner = new Scanner(System.in)) {
            try (Scanner continueScanner = new Scanner(System.in)) {

                String cont = "";
                do {
                    Filter filter = new Filter();
                    System.out.println("\t\t\t============================================= ");
                    System.out.println("\t\t\t||                SEARCH OPTIONS           || ");
                    System.out.println("\t\t\t============================================= ");
                    System.out.println("\t\t\t|| 1.  SEARCH SPOT                         ||");
                    System.out.println("\t\t\t|| 2.  SEARCH PEOPLE                       ||");
                    System.out.println("\t\t\t|| 3.  SEARCH MESSAGE                      ||");
                    System.out.println("\t\t\t============================================= ");
                    System.out.print("Enter Your choice: ");
                    int option = scanner.nextInt();

                    switch (option) {
                        case 1 -> System.out.println("Enter Your choice: ");
                        case 2 -> System.out.println("Enter Your choice: ");
                        case 3 -> System.out.println("Enter Your choice: ");
                        default -> System.out.println("Invalid option");
                    }

                    System.out.print("Continue searching(yes/no): ");
                    cont = continueScanner.nextLine();

                } while (cont.equalsIgnoreCase("Y") || cont.equalsIgnoreCase("YES"));
            }
        }
    }
}
