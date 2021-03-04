package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Controllers.LocationControllers.LocationActions;
import com.spiralSpotManagement.Server.Model.Location;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.spiralSpotManagement.Client.Main.welcomeToSpiral;

/**
            @author : Gervais Ishimwe
            @outhor : EGIDE Harerimana
            LCOATION CONTROLLER  - SERVER CONTROLLER
            Synchronizing all the methods
 */
public class LocationView {

    public void registerLocation()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("CREATING A NEW LOCATION\n=====================================\n");
        System.out.println("Enter the Location name: ");
        String locationName = scanner.nextLine();

        System.out.println("Enter the level: ");
        String level_id = scanner.nextLine();

        System.out.println("Enter the parent: ");
        String parent_id = scanner.nextLine();

        System.out.println("Enter the location GPS: ");
        String location_GPS = scanner.nextLine();

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        Location location = new Location();
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
     *location management class. Method updateLocation for updating given location
     * @author Felix DUSENGIMANA
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  04-02-2021
     * return boolean to indicated the success or fail to update.
     *
     */

    public void updateLocation(){

        System.out.println("UPDATING EXISTING LOCATION\n=====================================\n");
    try {
        LocationActions location = new LocationActions();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Location ID::  ");
        String locId = scanner.nextLine();
        //chek location id going to be update exists
        if (!location.CheckLocationId(locId)){
            System.out.println("The location doesn't exists.\n\t1. TRY AGAIN\n\t2. REGISTER IT NOW.\n\t0. BACK HOME.\n\tEnter your choice here:: ");
            int userChoose;
            userChoose = scanner.nextInt();
              if (userChoose==1){
                 updateLocation();
              }else if (userChoose==2){
                  registerLocation();
              }else if(userChoose==0){
                  welcomeToSpiral();
              }else {
                  System.out.println("Bad choice! you returned to home");
                  welcomeToSpiral();
              }
            //System.exit(1);
        }
        System.out.println("\nIf you don't want to update some fields.\nEnter -1 in that input\n\n");
        System.out.print("New Level ID: ");
        String levelID = scanner.nextLine();
        //check new level going to be updated exists

        if (!levelID.equals("-1") &&!location.CheckLevelId(levelID)){

            System.out.println("The location level doesn't exists.\n\t1. TRY AGAIN\n\t2. REGISTER IT NOW.\n\t0. BACK HOME.\n\tEnter your choice here:: ");
            int userChoose;
            userChoose = scanner.nextInt();
            if (userChoose==1){
                updateLocation();
            }else if (userChoose==2){
                registerLocation();
            }else if(userChoose==0){
                welcomeToSpiral();
            }else {
                System.out.println("Bad choice! you returned to home");
                welcomeToSpiral();
            }
          //  System.exit(1);
        }
        if(levelID.equals("-1")) levelID=null;

        System.out.print("New Parent ID: ");
        String parentId = scanner.nextLine();
        //chek new location parent id going to be update exists
        if (!parentId.equals("-1") && !location.CheckLocationId(parentId)){
            System.out.println("The location doesn't exists.\n\t1. TRY AGAIN\n\t2. REGISTER IT NOW.\n\t0. BACK HOME.\n\tEnter your choice here:: ");
            int userChoose;
            userChoose = scanner.nextInt();
            if (userChoose==1){
                updateLocation();
            }else if (userChoose==2){
                registerLocation();
            }else if(userChoose==0){
                welcomeToSpiral();
            }else {
                System.out.println("Bad choice! you returned to home");
                welcomeToSpiral();
            }
           // System.exit(1);
        }
        if(parentId.equals("-1")) parentId=null;

        System.out.print("New Location Name: ");
        String locname = scanner.nextLine();
        if(locname.equals("-1")) locname=null;

        System.out.print("New GPS Coordinate: ");
        String gps = scanner.nextLine();
        if(gps.equals("-1")) gps=null;

        System.out.print("New Description: ");
        String decript = scanner.nextLine();
        if(decript.equals("-1")) decript=null;

         Location updateData = new Location();
         updateData.setDescription("The best continent where people collaborates");
         updateData.setLocation_id(locId);
         updateData.setParent_id(parentId);
         updateData.setLevel_id(levelID);
         updateData.setLocation_name(locname);
         updateData.setDescription(decript);
         updateData.setLocation_GPS(gps);

         RequestBody updateRequest = new RequestBody();
         updateRequest.setUrl("/location");
         updateRequest.setAction("update");
         updateRequest.setObject(updateData);

         ClientServerConnector server = new ClientServerConnector();
         ResponseBody responseBody = server.ConnectToServer(updateRequest);

         System.out.println("\n\n");
         for (Object response:responseBody.getResponse()){
             ResponseStatus responseStatus = (ResponseStatus) response;
             System.out.println("Update Status:: "+responseStatus.getStatus());
             System.out.println("Update Message:: "+responseStatus.getMessage());
             System.out.println("Action To do:: "+responseStatus.getActionToDo());
         }
    }catch (Exception e){
        System.out.println("Error Occurred "+e.getMessage());
    }
    }

    /**
     *location management class. Method deleting given location
     * @author Felix DUSENGIMANA
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  08-02-2021
     * return boolean to indicated the success or fail to update.
     *
     */

    public void DeleteLocation(){
        System.out.println("DELETING EXISTING LOCATION\n=====================================\n");
        try {
            System.out.println("Provide Location ID to delete:: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String locationName= reader.readLine();

            Location updateData = new Location();
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

/**
 *location management class. Method Recovering a deleted given location
 * @author Felix DUSENGIMANA
 * @powered by Rwanda Coding Academy
 * instructor Donatien MASHENGESHO
 * @since  08-02-2021
 * return boolean to indicated the success or fail to update.
 *
 */

public  void RecoverLocation(){
    System.out.println("RECOVERING A DELETED LOCATION\n=====================================\n");
    try{
        LocationActions location = new LocationActions();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Location ID::  ");
        String locId = scanner.nextLine();

        //chek location id going to be update exists
        if (!location.CheckLocationId(locId)){

            System.out.println("The location doesn't exists.\n\t1. TRY AGAIN\n\t2. REGISTER IT NOW.\n\t0. BACK HOME.\n\tEnter your choice here:: ");
            int userChoose;
            userChoose = scanner.nextInt();
            if (userChoose==1){
                updateLocation();
            }else if (userChoose==2){
                registerLocation();
            }else if(userChoose==0){
                welcomeToSpiral();
            }else {
                System.out.println("Bad choice! you returned to home");
                welcomeToSpiral();
            }

        }

        Location RecoverData = new Location();
        RecoverData.setLocation_id(locId);
        RequestBody recoverRequest = new RequestBody();
        recoverRequest.setUrl("/location");
        recoverRequest.setAction("recover");
        recoverRequest.setObject(RecoverData);

        ClientServerConnector server = new ClientServerConnector();
        ResponseBody responseBody = server.ConnectToServer(recoverRequest);

        for (Object response:responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("Recover Status:: "+responseStatus.getStatus());
            System.out.println("Recover Message:: "+responseStatus.getMessage());
            System.out.println("Action To do:: "+responseStatus.getActionToDo());
        }

    }catch (Exception e){
        System.out.println("Error message:: "+e.getMessage());
    }
}

    /**
     *location management class. Method Recovering a deleted given location
     * @author Felix DUSENGIMANA && landrada iradukunda
     * @powered by Rwanda Coding Academy
     * instructor Donatien MASHENGESHO
     * @since  08-02-2021
     * return boolean to indicated the success or fail to update.
     *
     */


    public void SearchByParentLocation(){
        Scanner scanner = new Scanner(System.in);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/location");
        requestBody.setAction("getLocationsByParent");
        String parent;
        System.out.println("Enter the location you want to search by: ");
        parent = scanner.nextLine();
        requestBody.setObject(parent);
        try {
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);
            for (Object i : responseBody.getResponse()) {
                System.out.println(i);
            }
            if ((responseBody.getResponse().isEmpty())) {
                System.out.println("No locations found😀! "+requestBody);
            }
        }catch (Exception e){
            System.out.println("Error Message:: "+e.getMessage());
        }
    }


    public void LocationViewMenu() throws Exception {
        /* LocationView entry  */

        String toContinue;
        do{
            int choice;
            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.CREATE A LOCATION        ------------------||");
            System.out.println("\t\t\t||------------------    2.UPDATE LOCATION          ------------------||");
            System.out.println("\t\t\t||------------------    3.DELETE A LOCATION        ------------------||");
            System.out.println("\t\t\t||------------------    4.RECOVER A LOCATION       ------------------||");
            System.out.println("\t\t\t||------------------    5.GET BY PARENT            ------------------||");
            System.out.println("\t\t\t||------------------    6.RETURN HOME              ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");
            choice = scanner.nextInt();
            switch (choice){
                case 1 :
                    registerLocation();
                    break;
                case 2:
                    updateLocation();
                    break;
                case 3:
                    DeleteLocation();
                    break;
                case 4:
                    RecoverLocation();
                    break;
                case 5:
                    SearchByParentLocation();
                case 6:
                    welcomeToSpiral();
                default:
                    System.out.println("Invalid input");

                    break;
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }

}
