package com.spiralSpotManagement.Client;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;

public class Main {
    public static void main(String[] args) throws Exception{
        CloudStorageConnectionHandler cloudStorageConnectionHandler = new CloudStorageConnectionHandler();
        cloudStorageConnectionHandler.checkDbWorking(cloudStorageConnectionHandler.getConnection());
    }
}
