package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;

import java.util.Scanner;
/*
            @author : Gervais Ishimwe
            LCOATION CONTROLLER  - SERVER CONTROLLER
            Synchronizing all the methods
 */
public class LocationView {

    public void registerLocation()throws Exception{
        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter the Location name: ");
//        String locationName = scanner.nextLine();
//
////        CALL API FOR GETTING ALL LEVELS
//        System.out.println("Enter the level: ");
//        String level_id = scanner.nextLine();
//
////        CALL API FOR THE PARENT IF AVAILABLE
//        System.out.println("Enter the parent: ");
//        String parent_id = scanner.nextLine();
//
//        System.out.println("Enter the location GPS: ");
//        String location_GPS = scanner.nextLine();
//
//        System.out.println("Enter the description: ");
//        String description = scanner.nextLine();
//
//        LocationModel location = new LocationModel();
//        location.setLocation_name(locationName);
//        location.setDescription(description);
//        location.setLocation_GPS(location_GPS);
//        location.setParent_id(parent_id);
//        location.setLevel_id(level_id);
//
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/location");
        requestBody.setAction("getLocationsByParent");
        String parent;
        System.out.println("Enter the location you want to search by: ");
        parent = scanner.nextLine();
        requestBody.setObject(parent);
//
//
//
//        ClientServerConnector clientServerConnector = new ClientServerConnector();
//        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);
//
//        for(Object response : responseBody.getResponse()){
//            ResponseStatus responseStatus = (ResponseStatus) response;
//            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
//            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
//            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
//            System.out.println("\t\t ------------------------------------------------------------------------------");
//
//        }

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);
        for(Object i: responseBody.getResponse()){
            System.out.println(i);
        }
        if((responseBody.getResponse().isEmpty())){
            System.out.println("No locations found!");
        }
        //System.out.println(responseBody.getResponse());
//        for(Object response : responseBody.getResponse()){
//            ResponseStatus responseStatus = (ResponseStatus) response;
//            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
//            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
//            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
//            System.out.println("\t\t ------------------------------------------------------------------------------");
//
//        }

    }
}
