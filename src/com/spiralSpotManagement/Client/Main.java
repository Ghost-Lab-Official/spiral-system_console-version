package com.spiralSpotManagement.Client;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.View.*;
import com.spiralSpotManagement.Client.View.LocationLevelsView;
import com.spiralSpotManagement.Client.View.LocationView;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.*;
import com.spiralSpotManagement.Server.ServerMain.SpiralMultiThreadedServer;

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
//       spotCategoryModuleMain();
//        spotMainContent();
//        new CommentView().makeComment();
//        new CommentView().updateTheComment();
//        new CommentView().makeReplyComment();
//        new CommentView().updateCommentStatus();
//        new CommentReactionView().insertCommentReaction();
//        loginUser();
//        new LocationLevelsView().registerLocationLevels();
//          new LocationView().registerLocation();
//        new UserCategoryView().mainMethod();

//    new BillingView().registerBillingPlan();
//        new BillingView().updateBillingPlan();
//    new BillingView().previewBillingPlans();
        new BillingView().previewBillingPlanById();
    }




    public static void spotCategoryModuleMain()throws Exception{

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
            case 3 :
                new SpotCategoryView().GetSpotCategory();
                break;
            case 4 :
                new SpotCategoryView().ChangeSpotStatus();
                break;
            default :
                System.out.println("Invalid choice");
        }
    }


    public static void spotMainContent()throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("||\t\tSpot  Section\t\t||");
        System.out.println("==============================\n");
        System.out.println("||\t\t1.Create Spot\t\t||\n");
        System.out.println("||\t\t2.Edit Spot\t\t\t||\n");
        System.out.println("||\t\t3.Delete Spot\t\t||\n");
        System.out.println("||\t\t4.Exit         \t\t||\n");
        System.out.println("==============================");
        String choose = input.nextLine();


        switch (choose) {
            case "1":
                new SpotView().createSpot();
                break;
          case "2":
                new SpotView().updateSpot();
            break;
          case "3":
            new SpotView().deleteSpotContent();
            break;
            case "4":
                System.exit(0);
            default:
                System.out.println("Incorrect Input!!");
        }
    }

}
