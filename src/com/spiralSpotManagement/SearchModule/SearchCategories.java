package com.spiralSpotManagement.SearchModule;

import java.util.Scanner;


public class SearchCategories {
    public static void main(String[] args) throws Exception{
        Filter filter = new Filter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||                SEARCH OPTIONS           || ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  SEARCH SPOT                         ||");
        System.out.println("\t\t\t|| 2.  SEARCH PEOPLE                       ||");
        System.out.println("\t\t\t|| 3.  SEARCH MESSAGE                      ||");
        System.out.println("\t\t\t============================================ ");
        System.out.println("Enter Your choice: ");
        int option = scanner.nextInt();

        switch(option){
            case 1 -> filter.spotFilter();
            default -> System.out.println("Invalid choice");
        }

        filter.spotFilter();
    }
}
