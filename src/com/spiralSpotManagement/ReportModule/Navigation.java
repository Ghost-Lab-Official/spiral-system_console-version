package com.spiralSpotManagement.ReportModule;

import java.util.Scanner;

public class Navigation {
//    public static  String toContinue;
    public static Scanner scanInput=new Scanner(System.in);
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
//=================================================================================================
//=======================              SPOTS RELATED           ====================================
    public static void navigateToSpotsManagement(){

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
            case 2 -> viewAllSpots();
            case 3 -> viewSpotsByStatus();
            case 4 -> navigateToHome();
            case 5 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
        }
    }


    public static void viewAllStatistics(){

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
        int choice = scanInput.nextInt();

        switch (choice) {
            case 1 -> getTheTotalNumberOfRegisteredSpots();
            case 2 -> getTheTotalNumberOfActiveSpots();
            case 3 -> getTheTotalNumberOfInactiveSpots();
            case 4 -> getTheNumberOfHighlyVisitedSpots();
            case 5 -> navigateToSpotsManagement();
            case 6 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
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
        System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
        System.out.println("\t\t\t============================================= ");

//        viewAListOfAllSpots();
    }

    public static void viewSpotsByStatus(){

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
//                viewAllActiveSpots();
                break;
            case 2:
//                viewAllArchivedSpots();
                break;
            case 3:
//                highlyVisitedSpots();
                break;
            case 4:
//                go back
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

//=======================              END OF SPOTS             ===================================


//=================================================================================================
//=======================              USERS RELATED           ====================================

    public static void navigateToUsersManagement() {
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

    public static void viewUserActivities() {
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
            case 3 -> navigateToHome();
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

}
