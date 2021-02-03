package com.spiralSpotManagement.UsersModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

//somehow done

public class Register {
    public static void  UpdateUser(Connection connection) throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your user name ");
        String userName = scanner.nextLine();
        System.out.println("Enter your email ");
        String email = scanner.nextLine();
        System.out.println("Enter your gender ");
        String gender = scanner.nextLine();
        System.out.println("Enter your birthDate");
        String birthDate = scanner.nextLine();
        System.out.println("Enter your password ");
        String password = scanner.nextLine();
        System.out.println("Enter your location ");
        String location = scanner.next();

        byte[] salt = genSalt();
        String securePassword = getSecurePassword(password,salt);


        String sql = "INSERT INTO users_table(first_name,last_name,user_name,email,gender,birth_date,password,user_category,location) values (?,?,?,?,?,?,?,?,?)";
//        getUsers
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,userName);
        preparedStatement.setString(4,email);
        preparedStatement.setString(5,gender);
        preparedStatement.setString(6,birthDate);
        preparedStatement.setString(7,securePassword);
        preparedStatement.setInt(8,1);
        preparedStatement.setString(9,location);
        int inserted = preparedStatement.executeUpdate();
        if(inserted == 1){
            System.out.println("Inserted");
        }
        else{
            System.out.println("an error occurred");
        }
    }

    private static byte[] genSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
       //A secure random generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG","SUN");
        //an array of salt
        byte[] salt = new byte[16];
        //get random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
    private static String getSecurePassword(String password, byte[] salt){
        String generatedPassword = null;
        try {
            //create messageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            //this bytes[] has bytes in decimal format;
            //convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<bytes.length;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
            }
            generatedPassword = sb.toString();
         } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return  generatedPassword;
    }

    public static void main(String[] args) throws Exception{
        registerUser(new CloudStorageConnection().getConnection());
    }
}

