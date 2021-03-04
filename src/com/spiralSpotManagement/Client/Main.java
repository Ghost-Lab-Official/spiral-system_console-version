package com.spiralSpotManagement.Client;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Client.View.*;
import com.spiralSpotManagement.Client.View.LocationView;
import com.spiralSpotManagement.Client.View.SpotView;
import com.spiralSpotManagement.Client.View.UserView;
import com.spiralSpotManagement.Client.View.SpotCategoryView;
import com.spiralSpotManagement.Server.Model.*;

import java.util.List;
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

            System.out.println("\t\t\t||------------------    8.Pay plan                   ------------------||");
            System.out.println("\t\t\t||------------------    9. check if user plan is active                   ------------------||");
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
                    Integer user_id;
                    Integer plan_id;
                    System.out.println("Enter the user ID: ");
                    user_id= scanner.nextInt();
                    System.out.println("Enter the plan ID: ");
                    plan_id = scanner.nextInt();
                    UserBilling userBilling = new UserBilling(user_id,plan_id);

                    requestBody.setUrl("/users-billing");
                    requestBody.setAction("pay");
                    requestBody.setObject(userBilling);

                    ClientServerConnector clientServerConnector = new ClientServerConnector();
                    ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);
                    for (Object response: responseBody.getResponse()){
                        ResponseStatus responseStatus = (ResponseStatus) response;
                        System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
                        System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
                        System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
                        System.out.println("\t\t ------------------------------------------------------------------------------");
                    }


                case 9:
                    Integer userID;
                    System.out.println("Enter the user ID: ");
                    userID= scanner.nextInt();
                    UserBillingServices userBillingTwo = new UserBillingServices();
                    UserBilling userBill = new UserBilling();
                    userBill.setUser_id(userID);

                    userBillingTwo.setUserBilling(userBill);
                    userBillingTwo.setService("CREATE_SPOT_");

                    requestBody.setUrl("/users-billing");
                    requestBody.setAction("checkUserPlan");
                    requestBody.setObject(userBillingTwo);



                    ClientServerConnector clientServerConnectorTwo = new ClientServerConnector();
                    ResponseBody responseBodyTwo=  clientServerConnectorTwo.ConnectToServer(requestBody);
                    for (Object response: responseBodyTwo.getResponse()){
                        ResponseStatus responseStatus = (ResponseStatus) response;
                        System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
                        System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
                        System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
                        System.out.println("\t\t ------------------------------------------------------------------------------");
                    }


                default:
                    System.out.println("Invalid input");
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));


    }

}
