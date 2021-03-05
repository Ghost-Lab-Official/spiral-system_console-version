package com.spiralSpotManagement;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.ReportModule.ReportModule;
//import com.spiralSpotManagement.UsersModule.UsersModule;


public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());

        new ReportModule();
        ReportModule.printDashboard();
    }

}