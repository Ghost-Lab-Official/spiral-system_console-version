package com.spiralSpotManagement;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import com.spiralSpotManagement.UsersModule.UsersModule;

public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());

        UsersModule usersModule=new UsersModule();
         usersModule.getUsersList(cloudStorageConnection.getConnection());

    }
}
