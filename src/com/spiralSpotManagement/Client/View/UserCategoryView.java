package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.UserCategory;

import java.util.Scanner;

public class UserCategoryView {
    public void mainMethod()throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("||\t\tUser Category Section\t||\n");
        System.out.println("||\t\t1.Create Category\t\t||\n");
        System.out.println("||\t\t2.Edit Category  \t\t||\n");
        System.out.println("||\t\t3.View Categories\t\t||\n");
        System.out.println("||\t\t4.Delete Category\t\t||\n");
        System.out.println("||\t\t5.Exit           \t\t||\n");
        System.out.println("==================================");
        String choose =input.nextLine();

        switch (choose){
            case "1":
                createCategory();
                break;
            case "2":
                updateUserCategories();
                break;
            case "3":
                selectUserCategories();
                break;
//            case "4":
//                deleteCategory(cloudStorageConnection.getConnection());
//                break;
            case "5":
                System.exit(0);
            default:
                System.out.println("Incorrect Input!!");
        }
    }

    public static void createCategory()throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the Category Name");
        String category =scan.nextLine();

        UserCategory userCategoryToInsert = new UserCategory();
        userCategoryToInsert.setCatName(category);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/user-category");
        requestBody.setAction("register");
        requestBody.setObject(userCategoryToInsert);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
}
    public static void selectUserCategories() throws Exception{
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/user-category");
        requestBody.setAction("getUserCategories");
        requestBody.setObject(null);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            UserCategory userCategory = (UserCategory) response;
            System.out.println("\t\t\t Categories\t\n");
            System.out.println("\t ID  \t Category name\n");
            System.out.println("\t "+userCategory.getCatId()+" \t\t "+userCategory.getCatName());
        }
    }

    public static void updateUserCategories() throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the category id to update");
        String categoryId = scan.nextLine();
        System.out.println("Enter the Category Name to update");
        String category =scan.nextLine();

        UserCategory userCategoryToUpdate = new UserCategory();
        userCategoryToUpdate.setCatId(1);
        userCategoryToUpdate.setCatName(category);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/user-category");
        requestBody.setAction("updateUserCategory");
        requestBody.setObject(userCategoryToUpdate);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }
}
