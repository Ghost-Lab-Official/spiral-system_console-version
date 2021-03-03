package com.spiralSpotManagement.Client;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Client.View.*;
import com.spiralSpotManagement.Client.View.LocationLevelsView;
import com.spiralSpotManagement.Client.View.LocationView;
import com.spiralSpotManagement.Client.View.SpotView;
import com.spiralSpotManagement.Client.View.UserView;
import com.spiralSpotManagement.Client.View.SpotCategoryView;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.*;
import com.spiralSpotManagement.Server.ServerMain.SpiralMultiThreadedServer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
/*
            @author : Anne Bethiane, Blessing Hirwa
            This is the entry of Spiral;
            WELCOME!
 */

public class Main {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void spiralWelcomePage(){
       System.out.println("\n\n\n                                                                                                       ");
       System.out.println("\t\t\t\t                                                                                                       ");
       System.out.println(ANSI_BLUE +"\t\t\t\t    -------------                                                             ----                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    |  ----------                                                             |  |                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    |  |                               __                                     |  |                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    |  |                              |__|                                    |  |                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    |   ---------   ----  ---------   ----   ----  -----      ------   --     |  |                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    '--------   |   |  ' '-----.  |   |  |   |  ''_____|    /  ----- \\   |    |  |                    "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t             |  |   |  |       |  |   |  |   |  |          |  |      |   |    |  |                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t             |  |   |  |       |  |   |  |   |  |          |  |      |   |    |  |                     "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    ---------|  |   |  |-----------   |  |   |  |          |  --------   |    |  |_______              "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t    ------------'   |  |-----------   ----   ----           \\________,\\__|    '__________            "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t                    |  |                                                                               "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t                    |  |                                                                               "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t                    |  |                                                                               "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t                    |  |                                                                               "+ANSI_RESET);
       System.out.println(ANSI_BLUE +"\t\t\t\t                    ----                                                                               "+ANSI_RESET);
   }

    public void loadingPageWrapper() throws InterruptedException {
        System.out.println("\t\t-------------------------------------------------------------------------------------------------");
        System.out.print("\t\t\t\tSpiraling \t");
        for (int i = 0; i < 20; i++) {
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.print("\n");
        System.out.println("\t\t-------------------------------------------------------------------------------------------------\n\n");
        System.out.println("\n");
    }

    public static void main(String[] args) throws Exception {
        RequestBody requestBody = new RequestBody();
        UserView userForms = new UserView();
        SpotView spotForms = new SpotView();
        UserCategoryView userCategoryForms = new UserCategoryView();
        SpotCategoryView spotCategories= new SpotCategoryView();
        LocationView locationForms = new LocationView();
        SearchView searchForms = new SearchView();
        String toContinue;
        do {
            int choice;
            Scanner scanner = new Scanner(System.in);
            new Main().spiralWelcomePage();
            new Main().loadingPageWrapper();
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      WELCOME TO SPIRAL        ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.LOGIN                    ------------------||");
            System.out.println("\t\t\t||------------------    2.REGISTER                 ------------------||");
            System.out.println("\t\t\t||------------------    3.SPOT INFO                ------------------||");
            System.out.println("\t\t\t||------------------    4.SPOT CATEGORY INFO       ------------------||");
            System.out.println("\t\t\t||------------------    5.LOCATION INFO            ------------------||");
            System.out.println("\t\t\t||------------------    6.SEARCH                   ------------------||");
            System.out.println("\t\t\t||------------------    7.REPORT                   ------------------||");
            System.out.println("\t\t\t||------------------    8.USER SETTINGS            ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");
            choice = scanner.nextInt();
            switch (choice){
                case 1 :
                    userForms.loginUser();
                    break;
                case 2:
//                    System.out.println("here"+userExistence);
                    if(new UserAuthMiddleware().checkForUserExistence() != 0){
                        userForms.registerUser();
                    }
                    else {
                        System.out.println("You have to login first\n");
                        userForms.loginUser();
                    }
                    break;
                case 3:
                    spotForms.spotViewMenu();
                    break;
                case 4:
                    //spotCategories.SpotCategoryMenu();
                    userCategoryForms.UserCategoryMenu();
                    break;
                case 5:
                    if (new UserAuthMiddleware().checkForUserExistence() != 0)
                    locationForms.LocationViewMenu();
                    else{
                        System.out.println("You have to login first\n");
                        new UserView().loginUser();
                    }

                case 6:
                    //        create user log
                    UserLog userLogToInsert = new UserLog();
                    userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                    userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
                    userLogToInsert.setAction("searching");
                    userLogToInsert.setDateTimeLoggedOut(null);
                    userLogToInsert.setTotalIn(5);
                    userLogToInsert.setTotalOut(3);
                    new ReportsView().createUserlog(userLogToInsert);
                    searchForms.mainMethod();
                    break;
                case 7:
                    if (new UserAuthMiddleware().checkForUserExistence() != 0)
                    new ReportsView().reportDashboard();
                    else {
                        System.out.println("You have to login first\n");
                        new UserView().loginUser();
                    }
                    break;
                case 8:
                    new UserView().mainMethod();
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }

}
