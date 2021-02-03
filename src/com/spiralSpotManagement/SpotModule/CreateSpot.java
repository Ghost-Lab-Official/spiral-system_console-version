package com.spiralSpotManagement.SpotModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.*;
import java.util.Scanner;


public class CreateSpot {

    private static int spotId;
    private static String spotName;

    public CreateSpot(){}

    public CreateSpot(String spotName){
        this.spotName=spotName;
    }

    public int getSpotId() { return spotId; }
    public void setSpotId(int spotId) { this.spotId = spotId; }
    public String getSpotName() { return spotName; }
    public void setSpotName(String spotName) { this.spotName = spotName; }



    private static final String InsertSql = "INSERT INTO Spot_table (user_id, category_id, location_id, spot_name, spot_description, views, viewers, rates, registration_date, update_date, status) VALUES(?)";
    private static final String UpdateSql = "UPDATE Spot_table SET spot_entity=? WHERE spot_id=?";
    private static final String deleteSQL = "DELETE FROM Spot_table where spot_id=?";



//    Insert spot function

    public static void insertSpot(Connection connection) throws SQLException{
    }



    //  select Spot function
    public static void selectSpot(Connection connection)throws Exception{
    }


    //    Update Spot function
    public static void UpdateSpot(Connection connection) throws Exception{
    }

    //delete function
    public static void deleteSpot(Connection connection) throws Exception{
    }


    //    main function
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("||\t\tSpot  Section\t\t||");
        System.out.println("==============================\n");
        System.out.println("||\t\t1.Create Spot\t\t||\n");
        System.out.println("||\t\t2.Edit Spot\t\t\t||\n");
        System.out.println("||\t\t3.View Spot\t\t\t||\n");
        System.out.println("||\t\t4.Delete Spot\t\t||\n");
        System.out.println("||\t\t5.Exit         \t\t||\n");
        System.out.println("==============================");
        String choose =input.nextLine();

        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();

        switch (choose){
            case "1":
                insertSpot(cloudStorageConnection.getConnection());
                break;
            case "2":
                UpdateSpot(cloudStorageConnection.getConnection());
                break;
            case "3":
                selectSpot(cloudStorageConnection.getConnection());
                break;
            case "4":
                deleteSpot(cloudStorageConnection.getConnection());
                break;
            case "5":
                System.exit(0);
            default:
                System.out.println("Incorrect Input!!");
        }
    }
}
