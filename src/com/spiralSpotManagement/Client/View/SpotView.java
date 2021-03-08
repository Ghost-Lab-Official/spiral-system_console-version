
/*
            @author : Cyebukayire Peace
            Created on: 2021 Jan 25
            What these codes are doing:
            . Inserting Spot : This means creating a new spot into the database
            . Updating Spot: Editing information about the spot
            . Changing Spot Status (Deleting): We just change the status from active to inactive instead of deleting
                                               permanently because we don't want to loose any data.
 */
package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Main;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.sql.PreparedStatement;
import java.util.Scanner;

import static com.spiralSpotManagement.Client.Main.welcomeToSpiral;

public class SpotView {
<<<<<<< HEAD

    //Creating a spot
    public void createSpot()throws Exception{
        Integer spot_id = 13;
        Integer user_id = 1;
        Integer category_id = 45;
        Integer location_id =5655;
        String spot_name = "Mouse";
        String spot_description = "Black and brown mouse found in class B";
        String registration_date = "2021-09-21";
        String status ="1";
=======
    FormsView formClient = new FormsView();

    public void createSpot()throws Exception{
        Spot customSpot = new Spot();
        customSpot = formClient.createSpotViewForm();
        Integer user_id = customSpot.getUserId();
        Integer category_id = customSpot.getCategoryId();
        Integer location_id = customSpot.getLocationId();
        String spot_name = customSpot.getSpotName();
        String spot_description = customSpot.getSpotDescription();
        Integer status = customSpot.getStatus();
>>>>>>> d1ba61f70642d41ccb4db008cc7cd141a99612ad


        Spot spotToCreate = new Spot(user_id,category_id,location_id,spot_name,spot_description,status);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot");
        requestBody.setAction("register");
        requestBody.setObject(spotToCreate);

        ClientServerConnector  clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    //Updating Spot Information
    public void updateSpot()throws Exception{
        Spot spotToUpdate = new Spot();
        spotToUpdate = formClient.updateSpotViewForm();

        Integer spot_id;
        spot_id = spotToUpdate.getSpotId();
        Integer user_id = spotToUpdate.getUserId();
        Integer category_id = spotToUpdate.getCategoryId();
        Integer location_id = spotToUpdate.getLocationId();
        String spot_name = spotToUpdate.getSpotName();
        String spot_description = spotToUpdate.getSpotDescription();
        Integer status = spotToUpdate.getStatus();

        Scanner scan = new Scanner(System.in);
<<<<<<< HEAD
        Integer spot_id = 8;
        Integer user_id = 1;
        Integer category_id = 45;
        Integer location_id =5655;
        String spot_name = "jacket";
        String spot_description = "Black and brown mouse found in class A";
        String registration_date = "2021-01-31";
        String status = "1";
=======
>>>>>>> d1ba61f70642d41ccb4db008cc7cd141a99612ad

        Spot spotToCreate = new Spot(spot_id,user_id,category_id,location_id,spot_name,spot_description,status);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot");
        requestBody.setAction("update");
        requestBody.setObject(spotToCreate);

        ClientServerConnector  clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    //Changing the status of the spot
    public void deleteSpotContent()throws Exception{
<<<<<<< HEAD
        Integer id =12;
=======
        Integer spotIdToDelete = formClient.deleteSpotViewForm();
>>>>>>> d1ba61f70642d41ccb4db008cc7cd141a99612ad

        Spot spotToDelete = new Spot();
        spotToDelete.setSpotId(spotIdToDelete);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot");
        requestBody.setAction("delete");
        requestBody.setObject(spotToDelete);

        ClientServerConnector  clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    public void spotViewMenu() throws Exception {
        Main systemEntry=new Main();
        /*@Bethiane
         * This is the entry of spotView */
        int choice;
        Scanner scanner = new Scanner(System.in);
        SpotView formClient = new SpotView();
        UserLog userLogToInsert = new UserLog();
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t||------------------    1. CREATE A SPOT           ------------------||");
        System.out.println("\t\t\t||------------------    2. UPDATE A SPOT           ------------------||");
        System.out.println("\t\t\t||------------------    3. DELETE A SPOT           ------------------||");
        System.out.println("\t\t\t||------------------    4. RETURN HOME             ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t\t  Enter your choice                                              ");
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                if(new UserAuthMiddleware().checkForUserExistence() != 0) {
                    formClient.createSpot();

                    UserLog userLogToInsertonSpotCreation = new UserLog();
                    userLogToInsertonSpotCreation.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                    userLogToInsertonSpotCreation.setAction("Created spots ");
                    new ReportsView().createUserlog(userLogToInsertonSpotCreation);
                }

                else{
                    System.out.println("You have to login first\n");
                    new UserView().loginUser();
                }
            }
            case 2 -> {
                if(new UserAuthMiddleware().checkForUserExistence() != 0){
                    userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                    userLogToInsert.setAction("updated a spot");
                    formClient.updateSpot();
                }

                else{
                    System.out.println("You have to login first\n");
                    new UserView().loginUser();
                }
            }
            case 3 -> {
                if(new UserAuthMiddleware().checkForUserExistence() != 0) {
                    userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                    userLogToInsert.setAction("Deleted a spot");
                    formClient.deleteSpotContent();
                }
                else{
                    System.out.println("You have to login first\n");
                    new UserView().loginUser();
                }
            }
            case 4 -> welcomeToSpiral();
            default -> System.out.println("Invalid input");
        }
    }
}

