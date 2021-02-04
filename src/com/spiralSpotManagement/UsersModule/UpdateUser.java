package com.spiralSpotManagement.UsersModule;
//by elissa
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

public class UpdateUser

{
    public static void UpdateUser(Connection con)throws Exception {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter new first name ");
        String firstName=scanner.nextLine();
        System.out.println("Enter new last name ");
        String lastName=scanner.nextLine();
        System.out.println("Enter old user name ");
        String userName=scanner.nextLine();
        System.out.println("Enter new email ");
        String email=scanner.nextLine();
        System.out.println("Enter new gender ");
        String gender=scanner.nextLine();
        System.out.println("Enter new birthDate");
        String birthDate=scanner.nextLine();
        System.out.println("Enter new password ");
        String password=scanner.nextLine();
        System.out.println("Enter new location ");
        String location=scanner.next();

        String command2 = "UPDATE users_table SET first_name=?, last_name=?,email=?,gender=?,birth_date=?,password=?,user_category=?,location=? WHERE user_name=?";
        PreparedStatement preparedStatement = con.prepareStatement(command2);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,userName);
        preparedStatement.setString(4,email);
        preparedStatement.setString(5,gender);
        preparedStatement.setString(6,birthDate);
        preparedStatement.setString(7,password);
        preparedStatement.setInt(8,1);
        preparedStatement.setString(9,location);
        int inserted=preparedStatement.executeUpdate();

    }
    public static void DeleteUser(Connection con)throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User name of the user you want to delete");
        String spanish = scanner.nextLine();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM user_table WHERE user_name=?");) {
            statement.setString(1, user_name);
            int rows = statement.executeUpdate();
            System.out.println(rows);
        } catch (SQLException ex) {
            System.out.println("Error while communicating with the database");
        }


    }

    public static void ViewUserProfile(Connection con)throws Exception{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select from users_table WHERE user_name=?,");
        while (rs.next()) {

            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String user_name = rs.getString("user_name");
            String email = rs.getString("email");
            String gender = rs.getString("gender");
            String birth_date = rs.getString("birth_date");
            String password = rs.getString("password");
            int user_category=rs.getInt("user_category");
            String location=rs.getString("location");

            System.out.println("*----------------------------------*");
            System.out.println("First name:" + first_name);
            System.out.println("last name:" + last_name);
            System.out.println("user name:" + user_name);
            System.out.println("email:" + email);
            System.out.println("gender:" + gender);
            System.out.println("password:" + password);
            System.out.println("user category:" + user_category);
            System.out.println("location:" + location);

            System.out.println("*----------------------------------*");
        }
    }

}
