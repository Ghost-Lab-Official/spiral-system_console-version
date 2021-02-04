package com.spiralSpotManagement;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

public class Main {

    public static void main(String[] args) throws Exception{
        Server newServer = new Server();
        newServer.startServer();

        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
    }

}