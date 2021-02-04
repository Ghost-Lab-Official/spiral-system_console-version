package com.spiralSpotManagement.SpotModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.*;
import java.util.Scanner;


public class CreateSpot {

//    private static int spotId;
    private static String spot_id, user_id, category_id, location_id, spot_name, spot_description, views, viewers, rates, registration_date, update_date, status;

    public CreateSpot(String user_id, String category_id, String location_id, String spot_name, String spot_description, String registration_date, String status, String spot_id){
        this.user_id=user_id; this.category_id=category_id; this.location_id=location_id; this.spot_name=spot_name; this.spot_description=spot_description; this.registration_date=registration_date; this.status=status; this.spot_id=spot_id;
    }

    public String getSpotId() { return spot_id; }
    public void setSpotId(String spot_id) { this.spot_id = spot_id; }

    public String getUserId() { return user_id; }
    public void setUserId(String user_id) { this.user_id = user_id; }

    public String getSpotCategory() { return category_id; }
    public void setSpotCategory(String category_id) { this.category_id = category_id; }

    public String getSpotLocation() { return location_id; }
    public void setSpotLocation(String location_id) { this.location_id = location_id; }

    public String getSpotName() { return spot_name; }
    public void setSpotName(String spotName) { this.spot_name = spot_name; }

    public String getSpot_description() { return spot_description; }
    public void setSpot_description(String spot_description) { this.spot_description = spot_description; }

    public String getRegistration_date() { return registration_date; }
    public void setRegistration_date(String registration_date) { this.registration_date = registration_date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }





    private static final String InsertSql = "INSERT INTO Spot_table (user_id, category_id, location_id, spot_name, spot_description, registration_date, status, spot_id) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UpdateSql = "UPDATE Spot_table SET category_id=?, location_id=?, spot_name=?, spot_description=? WHERE spot_id=?";
    private static final String deleteSQL = "DELETE FROM Spot_table where spot_id=?";



//    Insert spot function

    public static void insertSpot(Connection connection) throws SQLException{
        String spot_id="6", user_id="1", category_id="45", location_id="5655", spot_name="Computer", spot_description="Balck and brown table found in Ouuaagaadouuguuu", registration_date="2021-01-31", status="1";
        Scanner scan = new Scanner(System.in);
        CreateSpot spot = new CreateSpot(user_id, category_id, location_id, spot_name, spot_description, registration_date, status, spot_id);
        try (PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)){
            preparedStatement.setString(1,spot.getUserId());
            preparedStatement.setString(2,spot.getSpotCategory());
            preparedStatement.setString(3,spot.getSpotLocation());
            preparedStatement.setString(4,spot.getSpotName());
            preparedStatement.setString(5,spot.getSpot_description());
            preparedStatement.setString(6,spot.getRegistration_date());
            preparedStatement.setString(7,spot.getStatus());
            preparedStatement.setString(8,spot.getSpotId());
//            preparedStatement.setString(1,spot.getUserId());
            preparedStatement.execute();
            System.out.println(preparedStatement);
            System.out.println("\nInserted New Spot Successfully!\n");
        }
        catch (SQLException a){
            a.printStackTrace();
        }
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

        String id = "1";
        try (PreparedStatement sql= connection.prepareStatement(deleteSQL)){
            sql.setString(1,id);
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
