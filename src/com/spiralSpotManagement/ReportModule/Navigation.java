package com.spiralSpotManagement.ReportModule;

import java.util.Scanner;

public class Navigation{
    public static Scanner scanInput=new Scanner(System.in);
    public static  int choice;
    public static void navigateToHome() {
        System.out.println("""
                \t\t\t\t Good management is the art of making problems so interesting
                \t\t\t\t and their solutions so constructive that everyone wants to get
                 \t\t\t\t to work and deal with them.""");
        System.out.println("\t\t\t\t welcome to spiral app’s management module");
    }

    //=================================================================================================
//=======================              SPOTS RELATED           ====================================
    public static void navigateToSpotsManagement() throws Exception {

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

        switch (choice) {
            case 1 -> viewAllStatistics();
            case 2 -> printAllSpots();
            case 3 -> viewSpotsByStatus();
            case 4 -> navigateToHome();
            case 5 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }
    }


    public static void viewAllStatistics() throws Exception {

        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  Number of registered spots          ||");
        System.out.println("\t\t\t|| 2.  Number of active spots              ||");
        System.out.println("\t\t\t|| 3.  Number of inactive spots            ||");
        System.out.println("\t\t\t|| 4.  Number of Highly visited spots      ||");
        System.out.println("\t\t\t|| 5.  Back                                ||");
        System.out.println("\t\t\t|| 6.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput.nextInt();
        switch (choice) {
            case 1 -> printTheTotalNumberOfRegisteredSpots();
            case 2 -> printTheTotalNumberOfActiveSpots();
            case 3 -> printTheTotalNumberOfInActiveSpots();
            case 4 -> printTheTotalNumberOfHighlyVisitedSpots();
            case 5 -> navigateToSpotsManagement();
            case 6 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }

    }
    public static void printTheTotalNumberOfRegisteredSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t Number of registered spots                   ");

        spotReportController.getTheTotalNumberOfRegisteredSpots();

    }

    public static void printTheTotalNumberOfActiveSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t   Number of Active spots                     ");

        spotReportController.getTheTotalNumberOfActiveSpots();
    }

    public static void printTheTotalNumberOfInActiveSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t    Number of Inactive spots               ");
        spotReportController.getTheTotalNumberOfInactiveSpots();
    }

    public static void printTheTotalNumberOfHighlyVisitedSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t    Number of trending spots                  ");

        spotReportController.getTheTotalNumberOfHighlyVisitedSpots();
    }

    public static void printAllSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
        System.out.println("\t\t\t============================================= ");
        spotReportController.viewAllSpots();
    }

    public static void viewSpotsByStatus() throws Exception {

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
        int choice = scanInput.nextInt();

        switch (choice) {
            case 1:
                spotReportController.viewAllActiveSpots();
                break;
            case 2:
                printAllInactiveSpots();
                break;
            case 3:
                PrintTop5HighlyVisitedSpots();
                break;
            case 4:
                navigateToSpotsManagement();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    public static void printAllInactiveSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-Inactive       = ");
        System.out.println("\t\t\t============================================= ");
        spotReportController.viewAllInactiveSpots();
    }

    public static void PrintTop5HighlyVisitedSpots() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/On-Fire            = ");
        System.out.println("\t\t\t============================================= ");
        spotReportController.ViewHighlyVisitedSpots();
    }

//=======================              END OF SPOTS             ===================================


//=================================================================================================
//=======================              USERS RELATED           ====================================

    public static void navigateToUsersManagement() throws Exception {
        Scanner scanInput=new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Users                     = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View user Statistics                ||");
        System.out.println("\t\t\t|| 2.  View all users                      ||");
        System.out.println("\t\t\t|| 3.  View user activities                ||");
        System.out.println("\t\t\t|| 4.  Back                                ||");
        System.out.println("\t\t\t|| 5.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");

        System.out.println("\t\t\t Make a choice: ");
        choice=scanInput.nextInt();

        switch (choice) {
            case 1 -> viewUserStatistics();
            case 2 -> viewAllUsers();
            case 3 -> viewUserActivities();
            case 4 -> navigateToHome();
            case 5 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }
    }

    private static void viewUserStatistics() {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Users/STATISTICS          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  Number of registered Users          ||");
        System.out.println("\t\t\t|| 2.  Number of active users              ||");
        System.out.println("\t\t\t|| 3.  Number of inactive users            ||");
        System.out.println("\t\t\t|| 4.  settings                            ||");
        System.out.println("\t\t\t|| 5.  Back                                ||");
        System.out.println("\t\t\t|| 6.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput.nextInt();

        switch (choice) {
            case 1:
                //get the number of registered users method
                break;
            case 2:
                //get the number of active users method
                break;
            case 3:
                //get the number of inactive users method
                break;
            case 4:
                //settings
                break;
            case 5:
                //back
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("\t\t\t\t Invalid input");
        }
    }

    public static void viewAllUsers() {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t=       ADMIN DASHBOARD/USERS/View-All      = ");
        System.out.println("\t\t\t============================================= ");

        //viewAllUsers();
    }

    public static void viewUserActivities() throws Exception {
        System.out.println("\t\t\t=============================================");
        System.out.println("\t\t\t=       ADMIN DASHBOARD/USERS/Activities    =");
        System.out.println("\t\t\t=============================================");
        System.out.println("\t\t\t|| 1.  In general                          ||");
        System.out.println("\t\t\t|| 2.  Specific User                       ||");
        System.out.println("\t\t\t|| 3.  Back                                ||");
        System.out.println("\t\t\t|| 4.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");

        System.out.println("\t\t\t Make a choice: ");
        choice=scanInput.nextInt();

        switch (choice) {
            case 1 -> usersActivities();
            case 2 -> userActivities();
            case 3 -> navigateToUsersManagement();
            case 4 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }
    }

    public static void usersActivities() {
        System.out.println("\t\t\t=============================================");
        System.out.println("\t\t\t=    ADMIN DASHBOARD/All USERS/Activities   =");
        System.out.println("\t\t\t=============================================");
    }

    public static void userActivities() {
        //user activities methods
    }

    public static class report {
        public static void userReportDashboard() throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\t============================================= ");
            System.out.println("\t\t\t=                ADMIN DASHBOARD            = ");
            System.out.println("\t\t\t============================================= ");
            System.out.println("\t\t\t|| 1.  View your spots                     ||");
            System.out.println("\t\t\t|| 2.  View statistics                     ||");
            System.out.println("\t\t\t|| 3.  Users Management                    ||");
            System.out.println("\t\t\t|| 4.  Exit                                ||");
            System.out.println("\t\t\t============================================ ");

            System.out.println("Make a choice ");
            int choice = scanner.nextInt();
        }
    }


//=======================              END OF Users             ===================================


//=================================================================================================
//=======================          Locations RELATED           ====================================

    public static void navigateToLocationsManagement() throws Exception {
        Scanner scanInput=new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Locations                 = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View Locations Statistics           ||");
        System.out.println("\t\t\t|| 2.  View all Locations                  ||");
        System.out.println("\t\t\t|| 3.  View Locations by status            ||");
        System.out.println("\t\t\t|| 4.  View spots in a location            ||");
        System.out.println("\t\t\t|| 5.  Back                                ||");
        System.out.println("\t\t\t|| 6.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");

        System.out.println("\t\t\t Make a choice: ");
        choice=scanInput.nextInt();

        switch (choice) {
            case 1 -> viewLocationsStatistics();
            case 2 -> LocationReportController.viewAllLocations();
            case 3 -> locationsByStatus();
            case 4 -> LocationReportController.getSpotsByLocation();
            case 5 -> navigateToHome();
            case 6 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }
    }

    public static void viewLocationsStatistics() throws Exception {

        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Locations/STATISTICS      = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  Number of registered Locations      ||");
        System.out.println("\t\t\t|| 2.  Number of active Locations          ||");
        System.out.println("\t\t\t|| 3.  Number of inactive Locations        ||");
        System.out.println("\t\t\t|| 4.  Back                                ||");
        System.out.println("\t\t\t|| 5.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput.nextInt();
        switch (choice) {
            case 1 -> LocationReportController.totalNumberOfRegisteredLocations();
            case 2 -> LocationReportController.totalNumberOfLocationsByStatus("Active");
            case 3 -> LocationReportController.totalNumberOfLocationsByStatus("Inactive");
            case 4 -> navigateToHome();
            case 5 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }

    }

    public static void locationsByStatus() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Locations/View-by-status  = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View active Locations               ||");
        System.out.println("\t\t\t|| 2.  View inactive Locations             ||");
        System.out.println("\t\t\t|| 3.  Back                                ||");
        System.out.println("\t\t\t|| 4.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput.nextInt();
        switch (choice) {
            case 1 -> LocationReportController.viewLocationsByStatus("Active");
            case 2 -> LocationReportController.viewLocationsByStatus("Inactive");
            case 3 -> navigateToHome();
            case 4 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }
    }



}

