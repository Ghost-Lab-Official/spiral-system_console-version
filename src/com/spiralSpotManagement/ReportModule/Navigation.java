package com.spiralSpotManagement.ReportModule;

import java.util.Scanner;

public class Navigation {
    public static  String toContinue;
    public static  int choice;
    public static void navigateToHome(){

//        do{
            System.out.println("\t\t\t\t Good management is the art of making problems so interesting\n" +
                    "\t\t\t\t and their solutions so constructive that everyone wants to get\n" +
                    " \t\t\t\t to work and deal with them.");
            System.out.println("\t\t\t\t welcome to spiral app’s management module");

//            System.out.println("'\t\t Do you want to continue ? ");
//            Scanner scanValue=new Scanner(System.in);
//            toContinue= scanValue.nextLine();
//        }while (toContinue.equals("Y") || toContinue.equals("y"));
    }

    public static void navigateToSpotsManagement(){
        Scanner scanInput=new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS                     = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View Statistics                     ||");
        System.out.println("\t\t\t|| 2.  View all  spots                     ||");
        System.out.println("\t\t\t|| 3.  View Spots by status                ||");
        System.out.println("\t\t\t|| 4.  back                                ||");
        System.out.println("\t\t\t|| 5.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");

        System.out.println("\t\t\t Make a choice: ");
        choice=scanInput.nextInt();

        switch (choice){
            case 1:
                viewAllStatistics();
                break;
            case 2:
                viewAllSpots();
                break;
            case 3:
                viewSpotsByStatus();
                break;
            case 4:
                navigateToHome();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t Invalid input");
        }
    }

    public static void viewAllStatistics(){
        Scanner scanInput1=new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  Number of registered spots          ||");
        System.out.println("\t\t\t|| 2.  Number of active spots              ||");
        System.out.println("\t\t\t|| 3.  Number of inactive spots            ||");
        System.out.println("\t\t\t|| 4.  Number of frequently visited spots  ||");
        System.out.println("\t\t\t|| 5.  Back                                ||");
        System.out.println("\t\t\t|| 6.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput1.nextInt();

        switch (choice) {
            case 1:
                getTheTotalNumberOfRegisteredSpots();
                break;
            case 2:
                getTheTotalNumberOfActiveSpots();
                break;
            case 3:
                getTheTotalNumberOfInactiveSpots();
                break;
            case 4:
                getTheNumberOfHighlyVisitedSpots();
                break;
            case 5:
                navigateToSpotsManagement();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("\t\t\t\t Invalid input");
        }

    }
    public static void getTheTotalNumberOfRegisteredSpots(){
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||    Number of registered spots            ||");

//        calculateTheTotalNumberOfSpots();
        System.out.println("\t\t\t============================================= ");

    }

    public static void getTheTotalNumberOfActiveSpots(){
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||    Number of Active spots               ||");

//        calculateTheTotalNumberOfActiveSpots();
        System.out.println("\t\t\t============================================= ");
    }

    public static void getTheTotalNumberOfInactiveSpots(){
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||    Number of Inactive spots               ||");

//        calculateTheTotalNumberOfInactiveSpots();
        System.out.println("\t\t\t============================================= ");
    }

    public static void getTheNumberOfHighlyVisitedSpots(){
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||    Number of trending spots               ||");

//        calculateTheTotalNumberOfHighlyVisitedSpots();
        System.out.println("\t\t\t============================================= ");
    }

    public static void viewAllSpots(){
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All          = ");
        System.out.println("\t\t\t============================================= ");

//        viewAListOfAllSpots();
    }

    public static void viewSpotsByStatus(){
        Scanner scanInput1=new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-by-status      = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View all active spots              ||");
        System.out.println("\t\t\t|| 2.  View all archived spots            ||");
        System.out.println("\t\t\t|| 3.  View top-5 highly visited spots    ||");
        System.out.println("\t\t\t|| 4.  Back                               ||");
        System.out.println("\t\t\t|| 5.  Exit                               ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput1.nextInt();

        switch (choice) {
            case 1:
//                viewAllActiveSpots();
                break;
            case 2:
//                viewAllActiveSpots();
                break;
            case 3:
//                viewAllActiveSpots();
                break;
            case 4:
//                viewAllActiveSpots();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

}
