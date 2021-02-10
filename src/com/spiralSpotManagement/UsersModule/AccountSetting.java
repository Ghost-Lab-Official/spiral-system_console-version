/*
@author:Twizeyimana Elissa
class contain methods
 1.update settings
 2.delete account
 3.view profile
 4.reset password
 5.update user category
 */
package com.spiralSpotManagement.UsersModule;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.spiralSpotManagement.Server.Controllers.UserModuleControllers.SendEmail;
import org.mindrot.jbcrypt.BCrypt;


public class AccountSetting {
    /**
     * @author: Twizeyimana Elissa
     * @param con
     * @throws Exception
     */
    public static void UpdateSettings(Connection con)throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Your old user_name Name:\t");
        String oldUserName = scanner.nextLine();
        Statement stmt = con.createStatement();
        ResultSet check = stmt.executeQuery("SELECT * from users_table WHERE user_name='" + oldUserName + "'");
        if(check.next()) {
            System.out.println(check.getString("user_name"));
        }else {
            System.out.println("No username found");
            return;
        }
        System.out.println("New Data");
        System.out.println("-----------------------\n");
        System.out.println("Enter your first name");
        String first_name=scanner.nextLine();
        System.out.println("Enter your last name");
        String last_name=scanner.nextLine();
        System.out.println("Enter your user name");
        String user_name=scanner.nextLine();
        System.out.println("Enter gender");
        String gender=scanner.nextLine();
        System.out.println("Enter your date of birth");
        String birth_date=scanner.nextLine();

        PreparedStatement updateSql=con.prepareStatement("Update users_table SET first_name=?," +
                "last_name=?,user_name=?,gender=?,birth_date=? WHERE user_name=?");
        updateSql.setString(1,first_name);
        updateSql.setString(2,last_name);
        updateSql.setString(3,user_name);
        updateSql.setString(4,gender);
        updateSql.setString(5,birth_date);
        updateSql.setString(6,oldUserName);
        int rowsUpdate=updateSql.executeUpdate();
        if (rowsUpdate>0){
            System.out.println("existing user was updated successful");
        }
    }

    /**
     * @author: Twizeyimana Elissa
     * @param con
     * @throws Exception
     */
    public static void DeleteProfile(Connection con)throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your user name");
        String oldUserName = scanner.nextLine();
        Statement stmt = con.createStatement();
        ResultSet check = stmt.executeQuery("SELECT * from users_table WHERE user_name='" + oldUserName + "'");
        if(check.next()) {
            System.out.println(check.getString("user_name"));
        }else {
            System.out.println("No username found");
            return;
        }
        PreparedStatement deleteSql=con.prepareStatement("DELETE FROM users_table where user_name=?");
        deleteSql.setString(1, oldUserName);
        int rowsUpdate=deleteSql.executeUpdate();
        if (rowsUpdate>0){
            System.out.println("Account  was deleted successful");
        }
    }

    /**
     * @author: Twizeyimana Elissa
     * @param con
     * @throws Exception
     */
    public static void ViewProfile(Connection con)throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter user name!");
        String oldUserName = scanner.nextLine();
        Statement stmt = con.createStatement();
        ResultSet check = stmt.executeQuery("SELECT * from users_table WHERE user_name='" + oldUserName + "'");
        if(check.next()) {
            System.out.println("ON "+check.getString("user_name")+"'s Desk");
        }else {
            System.out.println("No username found");
            return;
        }
        Statement view = con.createStatement();
        ResultSet rs = view.executeQuery("SELECT * from users_table WHERE user_name='" + oldUserName + "'");
        while (rs.next()) {
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String user_name = rs.getString("user_name");
            String email = rs.getString("email");
            String gender = rs.getString("gender");
            String birth_date = rs.getString("birth_date");
            String location = rs.getString("location");
            System.out.println("----------------------------------");
            System.out.println("First name:" + first_name);
            System.out.println("last name:" + last_name);
            System.out.println("user name:" + user_name);
            System.out.println("email:" + email);
            System.out.println("gender:" + gender);
            System.out.println("Birth day:"+birth_date);
            System.out.println("location:" + location);
            System.out.println("----------------------------------");
        }
    }
    /* this is method is for resetting password in case user forgot it
    here verification code is sent to his email
     */
    /**
     * @author: Twizeyimana Elissa
     * @param con
     * @description: for the case use forgot passoword ,he will be able to reset it by
     * sending verfication code to his email
     * @throws Exception
     */

    public static void ResetPassword(Connection con) throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your email");
        String email=scanner.nextLine();
        Statement CheckEmail = con.createStatement();
        ResultSet check =CheckEmail.executeQuery("SELECT * from users_table WHERE email='" + email + "'");
        if(check.next()) {
            System.out.println("code sent to "+check.getString("email"));
        }else {
            System.out.println("No email found!");
            return;
        }

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();

        new SendEmail().send("tzyelissa90@gmail.com","doordie16","twizelissa@gmail.com",
                "Verification Code","verification code:"+randomString);

        System.out.println("----------------------------");
        System.out.println("Enter code we send to "+email);
        String code=scanner.nextLine();
        if(code.equals(randomString)){
            System.out.println("Enter new password");
            String password=scanner.nextLine();

            String securePassword = hashPassword(password);

            PreparedStatement updateSql=con.prepareStatement("Update users_table SET password=? where email=?");
            updateSql.setString(1,securePassword);
            updateSql.setString(2,email);
            int PassUpdate=updateSql.executeUpdate();
            if (PassUpdate>0){
                System.out.println("Password reset successful");
            }

        }
        else {
            System.out.println("Try to Enter sent verification code");
        }
    }

    protected static String hashPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(12));

    }
    // method to update user category
    /**
     * @author: Twizeyimana Elissa,update user category by only admin when he want
     * @param con
     * @throws Exception
     */
    public static void UpdateUserCategory(Connection con) throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter user name");
        String user_name=scanner.nextLine();
        Statement CheckUser = con.createStatement();
        ResultSet check =CheckUser.executeQuery("SELECT * from users_table WHERE user_name='" + user_name + "'");
        if(check.next()) {
            System.out.println("User "+check.getString("user_name")+" Found!");


        }else {
            System.out.println("sorry, requested user name not found!");
            return;
        }

        System.out.println("Enter new  user category[1 || 2]");
        int userCategory=scanner.nextInt();
        PreparedStatement updateUserCategory= con.prepareStatement("UPDATE users_table  SET user_category=? WHERE " +
                "user_name=?");
        updateUserCategory.setInt(1,userCategory);
        updateUserCategory.setString(2,user_name);

        int PassUpdate=updateUserCategory.executeUpdate();
        if(PassUpdate>0){
            System.out.println("user category updated successful!");
        }
    }
    protected static Connection getConnection() throws SQLException{
        Connection con;
        String url="jdbc:mysql://remotemysql.com:3306/2YQ7auowc7?" + "autoReconnect=true&useSSL=false";
        String name="2YQ7auowc7";
        String pass="R2IMVJC67L";
        con=DriverManager.getConnection(url,name,pass);
        return con;
    }



    public static void main(String[] args) throws Exception{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int choice;
        if(getConnection()!=null){
            System.out.println("Connection established");
            do{
                System.out.println("--------------------------------------");
                System.out.println(" User settings                        ");
                System.out.println("--------------------------------------");
                System.out.println("  1.Update Account                    ");
                System.out.println("  2.Delete Account                    ");
                System.out.println("  3.View Profile                      ");
                System.out.println("  4.Reset Password                    ");
                System.out.println("  5.Update user category              ");
                System.out.println("--------------------------------------");
                System.out.println("Enter your choice");
                choice=Integer.parseInt(reader.readLine());
                switch (choice){
                    case 1:
                        UpdateSettings(getConnection());
                        break;
                    case 2:
                        DeleteProfile(getConnection());
                        break;
                    case 3:
                        ViewProfile(getConnection());
                        break;
                    case 4:
                        ResetPassword(getConnection());
                        break;
                    case 5:
                        UpdateUserCategory(getConnection());
                        break;
                    default:
                        System.out.println("Invalid please");
                }
            }
            while (choice!=10);
        }
    }
}
