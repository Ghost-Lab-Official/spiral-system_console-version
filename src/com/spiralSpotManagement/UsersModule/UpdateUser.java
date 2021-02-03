package com.spiralSpotManagement.UsersModule;

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
//

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your first name ");
        String firstName=scanner.nextLine();
        System.out.println("Enter your last name ");
        String lastName=scanner.nextLine();
        System.out.println("Enter your user name ");
        String userName=scanner.nextLine();
        System.out.println("Enter your email ");
        String email=scanner.nextLine();
        System.out.println("Enter your gender ");
        String gender=scanner.nextLine();
        System.out.println("Enter your birthDate");
        String birthDate=scanner.nextLine();
        System.out.println("Enter your password ");
        String password=scanner.nextLine();
        System.out.println("Enter your location ");
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
        String command3 = "DELETE  FROM users_table WHERE user_name=?";
        PreparedStatement preparedStatement = con.prepareStatement(command3);

    }



}
