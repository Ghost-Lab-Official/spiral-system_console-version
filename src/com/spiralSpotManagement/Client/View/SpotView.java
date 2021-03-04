package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Main;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;
import static com.spiralSpotManagement.Client.Main.ikazeSpiral;

import java.sql.PreparedStatement;
import java.util.Scanner;
import static com.spiralSpotManagement.Client.Main.ikazeSpiral;

public class SpotView {
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

    public void deleteSpotContent()throws Exception{
        Integer spotIdToDelete = formClient.deleteSpotViewForm();

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
        /*@Bethiane
         * This is the entry of spotView */
        String toContinue;
        do {
            int choice;
            Scanner scanner = new Scanner(System.in);
            SpotView formClient = new SpotView();

            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------       SPIRAL ~ SPOTS           ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1. CREATE A SPOT           ------------------||");
            System.out.println("\t\t\t||------------------    2. UPDATE A SPOT           ------------------||");
            System.out.println("\t\t\t||------------------    3. DELETE A SPOT           ------------------||");
            System.out.println("\t\t\t||------------------    4. RETURN HOME             ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");

            UserLog userLogToInsert = new UserLog();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    if (new UserAuthMiddleware().checkForUserExistence() != 0) {
                        formClient.createSpot();

                        userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                        userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
                        userLogToInsert.setAction("created spot");
                        userLogToInsert.setDateTimeLoggedOut(null);
                        userLogToInsert.setTotalIn(5);
                        userLogToInsert.setTotalOut(3);
                        new ReportsView().createUserlog(userLogToInsert);
                    } else {
                        System.out.println("You have to login first\n");
                        new UserView().loginUser();
                    }
                }
                case 2 -> {
                    if (new UserAuthMiddleware().checkForUserExistence() != 0) {
                        userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                        userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
                        userLogToInsert.setAction("updated a spot");
                        userLogToInsert.setDateTimeLoggedOut(null);
                        userLogToInsert.setTotalIn(5);
                        userLogToInsert.setTotalOut(3);
                        formClient.updateSpot();
                    } else {
                        System.out.println("You have to login first\n");
                        new UserView().loginUser();
                    }
                }
                case 3 -> {
                    if (new UserAuthMiddleware().checkForUserExistence() != 0) {
                        userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                        userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
                        userLogToInsert.setAction("Deleted a spot");
                        userLogToInsert.setDateTimeLoggedOut(null);
                        userLogToInsert.setTotalIn(5);
                        userLogToInsert.setTotalOut(3);
                        formClient.deleteSpotContent();
                    } else {
                        System.out.println("You have to login first\n");
                        new UserView().loginUser();
                    }
                }


                case 4-> {
                    ikazeSpiral();
                }

                default -> System.out.println("Invalid input");
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }
}


