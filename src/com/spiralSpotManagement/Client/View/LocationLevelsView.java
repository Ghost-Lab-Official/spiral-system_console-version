package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.LocationLevels;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.Scanner;

/*
            @author : Gervais Ishimwe
            LCOATION LEVELS CONTROLLER  - SERVER CONTROLLER
            Synchronizing all the methods
 */

public class LocationLevelsView {

    public void registerLocationLevels()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Location level name: ");
        String locationLevel = scanner.nextLine();

        LocationLevels llevel = new LocationLevels();
        llevel.setLevel_name(locationLevel);

//        Send the data as an object of request body
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/location-levels");
        requestBody.setAction("register");
        requestBody.setObject(llevel);
// send the request body
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
}
