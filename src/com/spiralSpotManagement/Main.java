package com.spiralSpotManagement;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import com.spiralSpotManagement.ReportModule.ReportModule;
//import com.spiralSpotManagement.UsersModule.UsersModule;


public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
//        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());

<<<<<<< HEAD
=======
        UsersModule usersModule=new UsersModule();
      //   usersModule.getUsersList(cloudStorageConnection.getConnection());
        usersModule.sendNotification("sagemuho@gmail.com","***************","mugaboverite@gmail.com","testing notification","this is small notification checking system");

>>>>>>> 312a634390dc4a1a0d6ec1e9ab16057e256b8a1a
    }

}