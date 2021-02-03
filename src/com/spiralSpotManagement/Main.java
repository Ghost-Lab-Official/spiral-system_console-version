package com.spiralSpotManagement;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import com.spiralSpotManagement.ReportModule.ReportModule;

public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
<<<<<<< HEAD
//        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());

        ReportModule report =new ReportModule();
        report.reportDashboard();

=======
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
//
        ReportModule report1=new ReportModule();
        report1.reportDashboard();
>>>>>>> 80a02f7299e56b8016e2c2b0497e172e9964e10e
    }

}