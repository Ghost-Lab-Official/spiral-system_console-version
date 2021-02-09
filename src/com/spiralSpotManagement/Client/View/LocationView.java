package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Main;
import com.spiralSpotManagement.Server.Model.LocationModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.Scanner;
/*
            @author : Gervais Ishimwe
            LCOATION CONTROLLER  - SERVER CONTROLLER
            Synchronizing all the methods
 */
public class LocationView {

    public void registerLocation()throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Location name: ");
        String locationName = scanner.nextLine();

//        CALL API FOR GETTING ALL LEVELS
        System.out.println("Enter the level: ");
        String level_id = scanner.nextLine();

//        CALL API FOR THE PARENT IF AVAILABLE
        System.out.println("Enter the parent: ");
        String parent_id = scanner.nextLine();

        System.out.println("Enter the location GPS: ");
        String location_GPS = scanner.nextLine();

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        LocationModel location = new LocationModel();
        location.setLocation_name(locationName);
        location.setDescription(description);
        location.setLocation_GPS(location_GPS);
        location.setParent_id(parent_id);
        location.setLevel_id(level_id);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/location");
        requestBody.setAction("register");
        requestBody.setObject(location);



        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for(Object response : responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");

        }


    }

    public void LocationViewMenu() throws Exception {
        /*@Bethiane
         * This is the entry of spotView */
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t||------------------    1. CREATE A SPOT           ------------------||");
        System.out.println("\t\t\t||------------------    2. UPDATE A SPOT           ------------------||");
        System.out.println("\t\t\t||------------------    3. DELETE A SPOT           ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t\t  Enter your choice                                              ");
        choice = scanner.nextInt();
        switch (choice){
            case 1 :
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
    }
}
