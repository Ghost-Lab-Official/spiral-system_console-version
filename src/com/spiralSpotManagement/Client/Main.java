package com.spiralSpotManagement.Client;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.Users;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        CloudStorageConnectionHandler cloudStorageConnectionHandler = new CloudStorageConnectionHandler();
        cloudStorageConnectionHandler.checkDbWorking(cloudStorageConnectionHandler.getConnection());

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");

        Users testingObject = new Users();
        testingObject.setEmail("ntwari@gmal.test");
        testingObject.setFullName("ntwari testing");
        requestBody.setObject(testingObject);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        // depending on clients need you will need to do type casting

        List<Object> usersFoundObject =  responseBody.getResponse();

        for (Object userObject: usersFoundObject){
            Users userFound = (Users) userObject;

            System.out.println("Server replied "
            + (userFound.getEmail()));
        }
    }
}
