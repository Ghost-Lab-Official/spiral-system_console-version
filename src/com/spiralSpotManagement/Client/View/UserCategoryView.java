package com.spiralSpotManagement.Client.View;

/**
 * @Author: Ineza Aimee Annabelle
 * @Description : client side of the user category actions
 */

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.UserCategory;
import com.spiralSpotManagement.Server.Utils.UserCategories;

import java.util.*;

public class UserCategoryView {
    public void mainMethod()throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================");
        System.out.println("||\t\tUser Category Section     \t\t||\n");
        System.out.println("||\t\t1.Create Category         \t\t||\n");
        System.out.println("||\t\t2.Edit Category           \t\t||\n");
        System.out.println("||\t\t3.View Categories         \t\t||\n");
        System.out.println("||\t\t4.Delete Category         \t\t||\n");
        System.out.println("||\t\t5.Select category by Id   \t\t||\n");
        System.out.println("==========================================");
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
            case "4":
                deleteCategory();
                break;
            case "5":
                selectUserCategoryById();
                break;
            default:
                System.out.println("Incorrect Input!!");
        }
    }
    public static void createCategory()throws Exception{
        Scanner scan = new Scanner(System.in);
        List<UserCategories> allPossibleUserCategories = new ArrayList<UserCategories>(EnumSet.allOf(UserCategories.class));
        System.out.println("---- Allowed User Categories ---------");
        int i=1;
        for (UserCategories category : allPossibleUserCategories){
            System.out.println(i+" "+category.toString());
            i++;
        }

        System.out.println("Insert the Category you want to create");
        int category =scan.nextInt();
        String categoryName;
        switch (category){
            case 1:
                categoryName = UserCategories.NORMAL_USER.toString();
                break;
             case 2:
                categoryName = UserCategories.SUPER_ADMIN.toString();
                break;
            case 3:
                categoryName = UserCategories.ADMIN.toString();
                break;
            case 4:
                categoryName = UserCategories.CONTRIBUTOR.toString();
                break;
            case 5:
                categoryName = UserCategories.EMPLOYEE.toString();
                break;
            default:
                categoryName = null;
                break;
        }


        UserCategory userCategoryToInsert = new UserCategory();
        userCategoryToInsert.setCatName(categoryName);

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
        System.out.println("\t\t\t Categories\t\n");
        System.out.println("\t ID  \t Category name\n");
        for (Object response: responseBody.getResponse()){
            UserCategory userCategory = (UserCategory) response;
            System.out.println("\t "+userCategory.getCatId()+" \t\t "+userCategory.getCatName());
        }
    }

    public static void selectUserCategoryById() throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the category id to select");
        int categoryId = scan.nextInt();
        UserCategory userCat = new UserCategory();
        userCat.setCatId(categoryId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/user-category");
        requestBody.setAction("selectCategoryById");
        requestBody.setObject(userCat);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        System.out.println("\t\t\t Categories\t\n");
        System.out.println("\t ID  \t Category name\n");
//        System.out.println(responseBody.getResponse());
        for (Object response: responseBody.getResponse()){
            UserCategory userCategory = (UserCategory) response;
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
        System.out.println("\t\t\t||------------------    5.VIEW USER CATEGORY BY ID      ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t\t  Enter your choice:                                              ");
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
            case 5:
                selectUserCategoryById();
                break;
            default:
                System.out.println("Invalid input");
        }
    }


}
