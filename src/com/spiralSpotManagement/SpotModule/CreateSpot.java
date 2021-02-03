package com.spiralSpotManagement.SpotModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.*;
import java.util.Scanner;


public class CreateSpot {

    private static int spotId;
    private static String spotName, user_id, category_id, location_id, spot_name, spot_description, views, viewers, rates, registration_date, update_date, status;

    public CreateSpot(String user_id, String category_id, String location_id, String spot_name, String spot_description, String views, String viewers, String rates, String registration_date, String update_date, String status){
        this.user_id=user_id; this.category_id=category_id; this.location_id=location_id; this.spot_name=spot_name; this.spot_description=spot_description; this.views=views; this.viewers=viewers; this.rates=rates; this.registration_date=registration_date; this.update_date=update_date; this.status=status;
    }

    public int getSpotId() { return spotId; }
    public void setSpotId(int spotId) { this.spotId = spotId; }
//    public String getSpotName() { return spotName; }
    public void setSpotName(String spotName) { this.spotName = spotName; }



    private static final String InsertSql = "INSERT INTO Spot_table (user_id, category_id, location_id, spot_name, spot_description, views, viewers, rates, registration_date, update_date, status) VALUES(?)";
    private static final String UpdateSql = "UPDATE Spot_table SET category_id=?, location_id=?, spot_name=?, spot_description=? WHERE spot_id=?";
    private static final String deleteSQL = "DELETE FROM Spot_table where spot_id=?";



//    Insert spot function

    public static void insertSpot(Connection connection) throws SQLException{
    }

    //    Update Spot function
    public static void UpdateSpot(Connection connection) throws Exception{

        Scanner scan = new Scanner(System.in);
        String user_id="1", category_id="", location_id="", spot_name="", spot_description="", views="", viewers="", rates="", registration_date="", update_date="", status="";
//        CreateSpot spot = new CreateSpot(user_id, category_id, location_id, spot_name, spot_description, views, viewers, rates, registration_date, update_date, status);
        try (PreparedStatement sql = connection.prepareStatement(UpdateSql)){
            sql.setString(1,category_id);
            sql.setString(2,location_id);
            sql.setString(3,spot_name);
            sql.setString(4,spot_description);
            sql.execute();
            System.out.println(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //delete function
    public static void deleteSpot(Connection connection) throws Exception{

        Scanner scan = new Scanner(System.in);
        System.out.println("Insert Spot id: ");
        int id = scan.nextInt();
        try (PreparedStatement sql= connection.prepareStatement(deleteSQL)){
            sql.setInt(1,id);
            sql.execute();
            System.out.println("\nSpot Is Deleted Successfully!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    //    main function
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("||\t\tSpot  Section\t\t||");
        System.out.println("==============================\n");
        System.out.println("||\t\t1.Create Spot\t\t||\n");
        System.out.println("||\t\t2.Edit Spot\t\t\t||\n");
        System.out.println("||\t\t3.Delete Spot\t\t||\n");
        System.out.println("||\t\t4.Exit         \t\t||\n");
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
                deleteSpot(cloudStorageConnection.getConnection());
                break;
            case "4":
                System.exit(0);
            default:
                System.out.println("Incorrect Input!!");
        }
    }
}
