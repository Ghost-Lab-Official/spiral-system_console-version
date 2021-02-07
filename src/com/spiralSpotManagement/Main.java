package com.spiralSpotManagement;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import com.spiralSpotManagement.SpotModule.SpotRatings;
import com.spiralSpotManagement.Server.Server;

public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
//        SpotCategory category=new SpotCategory();
//       category.theMainMethod();
        SpotRatings spotRating = new SpotRatings();
        spotRating.Add_spot_rating();
//        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
//        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        cloudStorageConnection.   checkDbWorking(cloudStorageConnection.getConnection());

        Server newServer = new Server();
        newServer.startServer();
    }

}