package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.LocationModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
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

        for(Object response : responseBody.getResponse()) {
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: " + responseStatus.getStatus() + " ---------------------------");
            System.out.println("\t\t --------------         Meaning: " + responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: " + responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");

        }
    }

    /**
     *location management class. Method updating for updating given location
     * @author Felix DUSENGIMANA
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-02-2021
     * return boolean to indicated the success or fail to update.
     *
     */

    public void updateLocation(){
    try {
         LocationModel updateData = new LocationModel();
         updateData.setDescription("The best continent where people collaborates");
         updateData.setLocation_id("LOC001HQT");
         updateData.setParent_id("LOC001HQT");
         updateData.setLevel_id("84fc4a8d-9720-406e-9b7b-2c020277f725");

         RequestBody updateRequest = new RequestBody();
         updateRequest.setUrl("/location");
         updateRequest.setAction("update");
         updateRequest.setObject(updateData);

         ClientServerConnector server = new ClientServerConnector();
         ResponseBody responseBody = server.ConnectToServer(updateRequest);

         for (Object response:responseBody.getResponse()){
             ResponseStatus responseStatus = (ResponseStatus) response;
             System.out.println("Update Status:: "+responseStatus.getStatus());
             System.out.println("Update Message:: "+responseStatus.getMessage());
             System.out.println("Action To do:: "+responseStatus.getActionToDo());
         }
    }catch (Exception e){
        System.out.println("Error Occurred");
    }
    }

    /**
     *location management class. Method deleting given location
     * @author Felix DUSENGIMANA
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-08-2021
     * return boolean to indicated the success or fail to update.
     *
     */

    public void DeleteLocation(){
        try {
            System.out.println("Provide Location ID to delete:: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String locationName= reader.readLine();

            LocationModel updateData = new LocationModel();
            updateData.setLocation_id(locationName);
            RequestBody updateRequest = new RequestBody();
            updateRequest.setUrl("/location");
            updateRequest.setAction("delete");
            updateRequest.setObject(updateData);

            ClientServerConnector server = new ClientServerConnector();
            ResponseBody responseBody = server.ConnectToServer(updateRequest);

            for (Object response:responseBody.getResponse()){
                ResponseStatus responseStatus = (ResponseStatus) response;
                System.out.println("Update Status:: "+responseStatus.getStatus());
                System.out.println("Update Message:: "+responseStatus.getMessage());
                System.out.println("Action To do:: "+responseStatus.getActionToDo());
            }
        }catch (Exception e){
            System.out.println("Error Occurred");
        }
    }
}
