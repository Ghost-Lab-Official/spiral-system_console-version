package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
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

public class SpotCategoryView {
    public void CreateCategory() throws Exception {

        Scanner reader = new Scanner(System.in);
        System.out.println("\t\t\t Enter User id: ");
        int userId = reader.nextInt();
        System.out.println("\t\t\t Enter category name: ");
        String categoryName = reader.nextLine();
        System.out.println("\t\t\t  Enter category description: ");
        String description = reader.nextLine();
        System.out.println("\t\t\t Enter the status: ");
        String status = reader.nextLine();

        SpotCategory spotCategoryToInsert = new SpotCategory();
        spotCategoryToInsert.setUserId(userId);
        spotCategoryToInsert.setCategoryName("category Name 1");
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

        Scanner read = new Scanner(System.in);
        System.out.println("\t\t\t Enter category ID: ");
        int categoryId = read.nextInt();
        System.out.println("\t\t\t Enter category name: ");
        String categoryName = read.nextLine();
        System.out.println("\t\t\t  Enter category description: ");
        String description = read.nextLine();
        System.out.println("\t\t\t Enter the status: ");
        String status = read.nextLine();

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
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/sportCategory");
        requestBody.setAction("getAll");
        requestBody.setObject(null);

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

    public void ChangeSpotStatus() throws Exception {

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
}
