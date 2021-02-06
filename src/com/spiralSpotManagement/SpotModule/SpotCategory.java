package com.spiralSpotManagement.SpotModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class SpotCategory {

//    public int category_id;
//    public int user_id;
//    public String category_name;
//    public String description;
//    public String status;


    public void CreateCategory() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\t\t\t Enter User_id: ");
        int user_id = parseInt(reader.readLine());
        System.out.println("\t\t\t Enter category name: ");
        String category_name = reader.readLine();
        System.out.println("\t\t\t  Enter category description: ");
        String description = reader.readLine();
        System.out.println("\t\t\t Enter the status: ");
        String status = reader.readLine();

        try {
            CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "INSERT INTO spot_category(user_id,category_name,description,status) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setString(2, category_name);
            statement.setString(3, description);
            statement.setString(4, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User Not created! Try again Later");
            e.printStackTrace();
        }

    }

    public void UpdateCategory() throws Exception {

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\t\t\t Enter category ID: ");
        int category_id = parseInt(reader2.readLine());
        System.out.println("\t\t\t Enter category name: ");
        String category_name = reader2.readLine();
        System.out.println("\t\t\t  Enter category description: ");
        String description = reader2.readLine();
        System.out.println("\t\t\t Enter the status: ");
        String status = reader2.readLine();

        try {
            CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "UPDATE spot_category SET category_name=?,description=?,status=? WHERE category_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, category_name);
            statement.setString(2, description);
            statement.setString(3, status);
            statement.setInt(4, category_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sorry! Try again Later");
            e.printStackTrace();
        }
    }

    public void GetspotCategory() throws Exception {


        try {
            CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "SELECT * FROM spot_category";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int category_id = result.getInt("category_id");
                int user_id = result.getInt("user_id");
                String category_name = result.getString("category_name");
                String description = result.getString("description");
                String status = result.getString("status");
                System.out.format("\n %s,%s,%s,%s,%s \n", category_id, user_id, category_name, description, status);

            }
        } catch (
                SQLException ex) {
            System.out.println("Sorry! Try again Later");
            ex.printStackTrace();
        }


    }

    public void Changespotstatus() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\t\t\t Enter category_id: ");
        int category_id = parseInt(reader.readLine());
        System.out.println("\t\t\t Enter the status: ");
        String status = reader.readLine();


        try {


            CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
            Connection connection = cloudStorageConnection.getConnection();
            String sql = "UPDATE spot_category SET status=? WHERE category_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, category_id);
            statement.executeUpdate();


        } catch (
                SQLException ex) {
            System.out.println("Sorry! Try again Later");
            ex.printStackTrace();
        }


    }
    public void theMainMethod()throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t=                SPOT CATEGORY DASHBOARD          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  create spot category                                ||");
        System.out.println("\t\t\t|| 2.  update spot category                     ||");
        System.out.println("\t\t\t|| 3.  select all categories                    ||");
        System.out.println("\t\t\t|| 4.  change spot status                            ||");
        System.out.println("\t\t\t============================================ ");
        System.out.println("\t\t\tEnter your choice: ");
        int choice = parseInt(reader.readLine());
        switch (choice) {
            case 1 -> CreateCategory();
            case 2 -> UpdateCategory();
            case 3 -> GetspotCategory();
            case 4 -> Changespotstatus();
            default -> System.out.println("Invalid choice");
        }

    }

}