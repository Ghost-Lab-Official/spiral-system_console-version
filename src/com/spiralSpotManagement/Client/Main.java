package com.spiralSpotManagement.Client;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.View.SpotCategoryView;
import com.spiralSpotManagement.Client.View.UserView;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.*;
import com.spiralSpotManagement.Server.ServerMain.SpiralMultiThreadedServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/*
            @author : Ntwari Egide - Scrum Master
            USER CONTROLLER  - SERVER CONTROLLER
            Synchronizing all the methods on the
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
    public static void main(String[] args) throws Exception{
        new SpiralMultiThreadedServer().startServer();
//        registerUser();
//        new UserView().registerUser();
//        new UserView().loginUser();
       spotModuleMain();
    }




    public static void spotModuleMain()throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t=                SPOT CATEGORY DASHBOARD          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  create spot category                                ||");
        System.out.println("\t\t\t|| 2.  update spot category                     ||");
        System.out.println("\t\t\t|| 3.  select all categories                    ||");
        System.out.println("\t\t\t|| 4.  change spot status                            ||");
        System.out.println("\t\t\t============================================ ");
        System.out.println("\t\t\tEnter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 :
                new SpotCategoryView().CreateCategory();
                break;
            case 2:
                new SpotCategoryView().UpdateCategory();
                break;
//            case 3 :
//                GetspotCategory();
//                break;
//            case 4 :
//                Changespotstatus();
//                break;
            default :
                System.out.println("Invalid choice");
        }
    }

}
