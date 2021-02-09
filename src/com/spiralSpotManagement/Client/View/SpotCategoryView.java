package com.spiralSpotManagement.Client.View;
import java.io.BufferedReader;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Main;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.SpotCategory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



/* @author  Bethiane */


public class SpotCategoryView {
    /* This the view for spotCategory. it has functions for creating,update,
    view, change status of spot category */

    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader entered = new BufferedReader(isr);
    Scanner scanner = new Scanner(System.in);

    public void CreateCategory() throws Exception {
        /* Create category form */
        System.out.println("\t\t\tEnter User id: ");
        int userId = scanner.nextInt();
        System.out.println("\t\t\t Enter category name: ");
        String categoryName = entered.readLine();
        System.out.println("\t\t\t  Enter category description: ");
        String description = entered.readLine();
        System.out.println("\t\t\t Enter the status: ");
        String status = entered.readLine();

        SpotCategory spotCategoryToInsert = new SpotCategory();

        spotCategoryToInsert.setUserId(userId);
        spotCategoryToInsert.setCategoryName(categoryName);
        spotCategoryToInsert.setDescription(description);
        spotCategoryToInsert.setStatus(status);

        /*
               Define Request Body
         */

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/sportCategory");
        requestBody.setAction("register");
        requestBody.setObject(spotCategoryToInsert);

        /*
            Send Request Body
         */
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }

    }

    public void UpdateCategory() throws Exception {
        /* Update category form */
        System.out.println("\t\t\t Enter category id to be updated: ");
        int categoryId = scanner.nextInt();
        System.out.println("\t\t\t Enter category name: ");
        String categoryName = entered.readLine();
        System.out.println("\t\t\t  Enter category description: ");
        String description = entered.readLine();
        System.out.println("\t\t\t Enter the status: ");
        String status = entered.readLine();

        SpotCategory spotCategoryToInsert = new SpotCategory();
        spotCategoryToInsert.setCategoryName(categoryName);
        spotCategoryToInsert.setDescription(description);
        spotCategoryToInsert.setCategoryId(categoryId);
        spotCategoryToInsert.setStatus(status);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/sportCategory");
        requestBody.setAction("update");
        requestBody.setObject(spotCategoryToInsert);

        /*
            Send Request Body
         */
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response : responseBody.getResponse()) {
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: " + responseStatus.getStatus() + " ---------------------------");
            System.out.println("\t\t --------------         Meaning: " + responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: " + responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }
//
    public void GetSpotCategory() throws Exception {
        /* Get all registered categories */
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/sportCategory");
        requestBody.setAction("getAll");
        requestBody.setObject(null);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            SpotCategory spotCategory = (SpotCategory) response;
            System.out.println("\t\t-----------------------------------------------------------------------------------");
            System.out.println("\t\t---Category Id: "+spotCategory.getCategoryId());
            System.out.println("\t\t---User Id: "+spotCategory.getUserId());
            System.out.println("\t\t---Name: "+spotCategory.getCategoryName());
            System.out.println("\t\t---Description: "+spotCategory.getDescription());
            System.out.println("\t\t-----------------------------------------------------------------------------------");
        }


    }

    public void ChangeSpotStatus() throws Exception {
        /* Change category status*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t Enter category_id: ");
        int categoryId  = scanner.nextInt();
        System.out.println("\t\t\t Enter the status: ");
        String status = scanner.nextLine();

        SpotCategory spotCategory = new SpotCategory();
        spotCategory.setCategoryId(categoryId);
        spotCategory.setStatus(status);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/sportCategory");
        requestBody.setAction("updateStatus");
        requestBody.setObject(spotCategory);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

   public void SpotCategoryMenu() throws Exception {
      /* SpotcategoryMenu entry  */
       String toContinue;
       do{
            int choice;
            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    SPIRAL ~ SPOT CATEGORY     ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.CREATE A CATEGORY        ------------------||");
            System.out.println("\t\t\t||------------------    2.UPDATE CATEGORY          ------------------||");
            System.out.println("\t\t\t||------------------    3.GET SPORT CATEGORIES     ------------------||");
            System.out.println("\t\t\t||------------------    4.CHANGE CATEGORY STATUS   ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");
            choice = scanner.nextInt();
            switch (choice){
                case 1 :
                    CreateCategory();
                    break;
                case 2:
                    UpdateCategory();
                    break;
                case 3:
                    GetSpotCategory();
                    break;
                case 4:
                    ChangeSpotStatus();
                    break;
                default:
                    System.out.println("Invalid input");

                    break;
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
   }
   }

