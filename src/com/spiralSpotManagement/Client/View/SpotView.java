package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.Spot;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class SpotView {
    public void createSpot()throws Exception{
        Integer spot_id = 13;
        Integer user_id = 1;
        Integer category_id = 45;
        Integer location_id =5655;
        String spot_name = "Mouse";
        String spot_description = "Black and brown mouse found in class B";
        String registration_date = "2021-09-21";
        String status ="1";


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

    public void updateSpot()throws Exception{
        Scanner scan = new Scanner(System.in);
        Integer spot_id = 8;
        Integer user_id = 1;
        Integer category_id = 45;
        Integer location_id =5655;
        String spot_name = "jacket";
        String spot_description = "Black and brown mouse found in class A";
        String registration_date = "2021-01-31";
        String status = "1";

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

    public void deleteSpotContent()throws Exception{
        Integer id =12;

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

}
