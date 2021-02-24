package com.spiralSpotManagement;

<<<<<<< HEAD
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

=======
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.ReportModule.ReportModule;
//import com.spiralSpotManagement.UsersModule.UsersModule;
>>>>>>> f9b73a3f64d93e651b022b46fadf4a047d219def


public class Main {

<<<<<<< HEAD
    public static void main(String[] args) throws Exception {
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
    }















































































































































































































=======
    public static void main(String[] args) throws Exception{
        System.out.println("HELLO SPIRAL SYSTEM\n\n ");
        CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
        cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());

        new ReportModule();
        ReportModule.printDashboard();
    }
>>>>>>> f9b73a3f64d93e651b022b46fadf4a047d219def

}