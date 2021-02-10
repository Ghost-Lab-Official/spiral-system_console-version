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
    public static void ExampleOfUsageOfClientServerConnector()throws Exception{
        RequestBody requestBody = new RequestBody();

        Users testingObject = new Users();
        testingObject.setEmail("ntwari@gmal.test");
        testingObject.setFullName("ntwari testing");
        requestBody.setObject(testingObject);

        requestBody.setUrl("/users");
        requestBody.setAction("register");

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        // depending on clients need you will need to do type casting

        List<Object> usersFoundObject =  responseBody.getResponse();

        for (Object userObject: usersFoundObject){
            ResponseStatus responseStatus = (ResponseStatus) userObject;

            System.out.println("Server replied "
                    + (responseStatus.getStatus()));
        }

        /*
            WORKING ON USER REGISTRATION
        */
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
                    searchForms.mainMethod();
                    break;
                case 7:
                    new ReportsView().reportDashboard();
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));


    }

}
