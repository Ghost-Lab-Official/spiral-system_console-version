package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.util.Scanner;

import static com.spiralSpotManagement.Client.Main.welcomeToSpiral;

/**
 * @author : Gervais Ishimwe
 * @author : Harerimana Egide
 * LOCATION LEVELS CONTROLLER  - SERVER CONTROLLER
 * Synchronizing all the methods
 */
public class LocationLevelsView {

    public void registerLocationLevels()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Location level name: ");
        String locationLevel = scanner.nextLine();

        LocationLevels llevel = new LocationLevels();
        llevel.setLevel_name(locationLevel);

        //Send the data as an object of request body
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/location-levels");
        requestBody.setAction("register");
        requestBody.setObject(llevel);
        //send the request body
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

    public void getAllLocationLevelsView() throws Exception{
        //Send the request
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/location-levels");
        requestBody.setAction("getAllLevels");
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

    public void LocationLevelsViewMenu() throws Exception {
        /* LocationLevelsView entry  */

        String toContinue;
        do{
            int choice;
            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.CREATE A LOCATION LEVEL  ------------------||");
            System.out.println("\t\t\t||------------------    2.GET ALL LOCATION LEVELS  ------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");
            choice = scanner.nextInt();
            switch (choice){
                case 1 :
                    registerLocationLevels();
                    break;
                case 2:
                    getAllLocationLevelsView();
                    break;
                default:
                    System.out.println("Invalid input");

                    break;
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }
}
