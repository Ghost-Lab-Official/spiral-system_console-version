package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Main;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.Spot;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class SpotView {
    public void CreateSpot()throws Exception{
        Integer spot_id = 8;
        Integer user_id = 1;
        Integer category_id = 45;
        Integer location_id =5655;
        String spot_name = "Computer";
        String spot_description = "Balck and brown table found in Ouuaagaadouuguuu";
        String registration_date = "2021-01-31";
        String status = "active";


        Spot spotToCreate = new Spot(spot_id,user_id,category_id,location_id,spot_name,spot_description,registration_date,status);

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

    public void UpdateSpot()throws Exception{
        Scanner scan = new Scanner(System.in);
        Integer spot_id = 8;
        Integer user_id = 1;
        Integer category_id = 45;
        Integer location_id =5655;
        String spot_name = "Updated Computer";
        String spot_description = "Balck and brown table found in Ouuaagaadouuguuu";
        String registration_date = "2021-01-31";
        String status = "active";

        Spot spotToCreate = new Spot(spot_id,user_id,category_id,location_id,spot_name,spot_description,registration_date,status);

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

    public void DeleteSpotContent()throws Exception{
        Integer id =8;

        Spot spotToDelete = new Spot();
        spotToDelete.setSpotId(id);

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
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------       SPIRAL ~ SPOTS           ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1. CREATE A SPOT           ------------------||");
            System.out.println("\t\t\t||------------------    2. UPDATE A SPOT           ------------------||");
            System.out.println("\t\t\t||------------------    3. DELETE A SPOT           ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CreateSpot();
                    break;
                case 2:
                    UpdateSpot();
                    break;
                case 3:
                    DeleteSpotContent();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        } while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }
    }


