package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.UserCategory;

import java.util.Scanner;

public class UserCategoryView {


    public static void createCategory()throws Exception{


        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t Enter category name: \"");
        String category =scanner.nextLine();

        UserCategory userCategoryToInsert = new UserCategory();
        userCategoryToInsert.setCatName(category);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/user-category");
        requestBody.setAction("register");
        requestBody.setObject(userCategoryToInsert);

        ResponseBody responseBody =
                new ClientServerConnector().ConnectToServer(requestBody);

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
        System.out.println("Enter the Category name to update");
        String category =scan.nextLine();

        System.out.println("Enter the category status");
        String categoryStatus = scan.nextLine();

        UserCategory userCategoryToUpdate = new UserCategory();
        userCategoryToUpdate.setCatId(1);
        userCategoryToUpdate.setCatName(category);
        userCategoryToUpdate.setCatStatus(categoryStatus);

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
    public void deleteCategory() throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the category id to delete");
        int categoryId = scan.nextInt();
        UserCategory userCategoryToDelete = new UserCategory();
        userCategoryToDelete.setCatId(categoryId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/user-category");
        requestBody.setAction("deleteUserCategory");
        requestBody.setObject(userCategoryToDelete);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    public void UserCategoryMenu()throws Exception{

        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t||------------------    1.CREATE A USER CATEGORY        ------------------||");
        System.out.println("\t\t\t||------------------    2.UPDATE USER CATEGORY          ------------------||");
        System.out.println("\t\t\t||------------------    3.VIEW USER CATEGORIES          ------------------||");
        System.out.println("\t\t\t||------------------    4.DELETE USER CATEGORY          ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t\t  Enter your choice                                              ");
        choice = scanner.nextInt();


        switch (choice){
            case 1:
                createCategory();
                break;
            case 2:
                updateUserCategories();
                break;
            case 3:
                selectUserCategories();
                break;
            case 4:
                deleteCategory();
                break;
            default:
                System.out.println("Invalid input");
        }
    }


}
