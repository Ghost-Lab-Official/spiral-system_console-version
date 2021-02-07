package com.spiralSpotManagement.ReportModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import static com.spiralSpotManagement.ReportModule.Navigation.view;

public class userReportController {
    /**
     * @author Ntezirizaza Erneste
     * @description This method shows the list of all registered users
     * @throws Exception
     */
    public static void viewAllUsers() throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT users_table.user_id , users_table.first_name , users_table.first_name ," +
                        " users_table.last_name , users_table.user_name  , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , " +
                        " users_categories.user_category from users_table " +
                        "left join users_categories on users_table.user_id=users_categories.category_id"
        );
        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
        while (rs.next()) {
            usersModel  myUsers = new usersModel(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getDate("birth_date"),
                    rs.getString("user_category"),
                    rs.getString("location"),
                    rs.getString("user_status"),
                    rs.getDate("registration_date")
                    );
            AllUsers.add(myUsers);
        }
        Iterator it = AllUsers.iterator();
        System.out.println("\t\t\t  #Id" + "\t\t\t First Name" +  "\t\t\t\t Last Name " +  "\t\t\t Username" +  "\t\t\t Email " +
                "\t\t\t\t Gender" +"\t\t\t\t Birth Date" +  "\t\t\t\t User Category" +  "\t\t\t Location Name " + "\t\t\t User Status " + "\t\t\t Registration Date ");
        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while(it.hasNext()){
            usersModel user = (usersModel)it.next();
            System.out.println(" \t\t\t\t "+user.getUserId() + " \t\t\t\t " + user.getFirstName()+ " \t\t\t\t " + user.getLastName() +
                    " \t\t\t\t " + user.getUserName() + " \t\t\t\t " + user.getEmail()+ " \t\t\t\t " +" \t\t\t\t " +user.getGender()
                    + " \t\t\t\t "+ user.getBirthDate()+" \t\t\t\t " + user.getUserCategoryName()+ " \t\t\t\t " + user.getUserStatus() + " \t\t\t\t " + user.getRegistrationDate());
        }
        connection.close();
    }

    /**
     * @author Ntezirizaza Erneste
     * @description This method shows the list of all active users
     * @throws Exception
     */

//    public static void viewAllActiveUsers() throws Exception {
//        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        Connection connection= cloudStorageConnection.getConnection();
//        Statement stmt = connection.createStatement();
//        String query= "SELECT users_table.user_id , users_table.first_name , users_table.first_name ," +
//                " users_table.last_name , users_table.user_name  , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , " +
//                " users_categories.user_category from users_table " +
//                "left join users_categories on users_table.user_id=users_categories.category_id " +
//                " where user_status='active' ";
//        ResultSet rs = stmt.executeQuery(query);
//        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
//        while (rs.next()) {
//            usersModel  myUsers = new usersModel(
//                    rs.getString("user_id"),
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getString("user_name"),
//                    rs.getString("email"),
//                    rs.getString("gender"),
//                    rs.getDate("birth_date"),
//                    rs.getString("user_category"),
//                    rs.getString("location"),
//                    rs.getString("user_status"),
//                    rs.getDate("registration_date")
//            );
//            AllUsers.add(myUsers);
//        }
//        Iterator it = AllUsers.iterator();
//        System.out.println("\t\t\t  #Id" + "\t\t\t First Name" +  "\t\t\t\t Last Name " +  "\t\t\t Username" +  "\t\t\t Email " +
//                "\t\t\t\t Gender" +"\t\t\t\t Birth Date" +  "\t\t\t\t User Category" +  "\t\t\t Location Name " + "\t\t\t User Status " + "\t\t\t Registration Date ");
//        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        while(it.hasNext()){
//            usersModel user = (usersModel)it.next();
//            System.out.println(" \t\t\t\t "+user.getUserId() + " \t\t\t\t " + user.getFirstName()+ " \t\t\t\t " + user.getLastName() +
//                    " \t\t\t\t " + user.getUserName() + " \t\t\t\t " + user.getEmail()+ " \t\t\t\t " +" \t\t\t\t " +user.getGender()
//                    + " \t\t\t\t "+ user.getBirthDate()+" \t\t\t\t " + user.getUserCategoryName()+ " \t\t\t\t " + user.getUserStatus() + " \t\t\t\t " + user.getRegistrationDate());
//        }
//        connection.close();
//    }
//
//    /**
//     * @author Ntezirizaza Erneste
//     * @description This method shows the list of all guest users
//     * @throws Exception
//     */
//
//    public static void viewAllGuestUsers() throws Exception {
//        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        Connection connection= cloudStorageConnection.getConnection();
//        Statement stmt = connection.createStatement();
//        String query= "SELECT users_table.user_id , users_table.first_name , users_table.first_name ," +
//                " users_table.last_name , users_table.user_name  , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , " +
//                " users_categories.user_category from users_table " +
//                "left join users_categories on users_table.user_id=users_categories.category_id " +
//                " where user_status='guest' ";
//        ResultSet rs = stmt.executeQuery(query);
//        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
//        while (rs.next()) {
//            usersModel  myUsers = new usersModel(
//                    rs.getString("user_id"),
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getString("user_name"),
//                    rs.getString("email"),
//                    rs.getString("gender"),
//                    rs.getDate("birth_date"),
//                    rs.getString("user_category"),
//                    rs.getString("location"),
//                    rs.getString("user_status"),
//                    rs.getDate("registration_date")
//            );
//            AllUsers.add(myUsers);
//        }
//        Iterator it = AllUsers.iterator();
//        System.out.println("\t\t\t  #Id" + "\t\t\t First Name" +  "\t\t\t\t Last Name " +  "\t\t\t Username" +  "\t\t\t Email " +
//                "\t\t\t\t Gender" +"\t\t\t\t Birth Date" +  "\t\t\t\t User Category" +  "\t\t\t Location Name " + "\t\t\t User Status " + "\t\t\t Registration Date ");
//        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        while(it.hasNext()){
//            usersModel user = (usersModel)it.next();
//            System.out.println(" \t\t\t\t "+user.getUserId() + " \t\t\t\t " + user.getFirstName()+ " \t\t\t\t " + user.getLastName() +
//                    " \t\t\t\t " + user.getUserName() + " \t\t\t\t " + user.getEmail()+ " \t\t\t\t " +" \t\t\t\t " +user.getGender()
//                    + " \t\t\t\t "+ user.getBirthDate()+" \t\t\t\t " + user.getUserCategoryName()+ " \t\t\t\t " + user.getUserStatus() + " \t\t\t\t " + user.getRegistrationDate());
//        }
//        connection.close();
//    }
//
//    /**
//     * @author Ntezirizaza Erneste
//     * @description This method shows the list of all disabled users
//     * @throws Exception
//     */
//
//    public static void viewAllDisabledUsers() throws Exception {
//        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//        Connection connection= cloudStorageConnection.getConnection();
//        Statement stmt = connection.createStatement();
//        String query= "SELECT users_table.user_id , users_table.first_name , users_table.first_name ," +
//                " users_table.last_name , users_table.user_name  , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , " +
//                " users_categories.user_category from users_table " +
//                "left join users_categories on users_table.user_id=users_categories.category_id " +
//                " where user_status='disabled' ";
//        ResultSet rs = stmt.executeQuery(query);
//        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
//        while (rs.next()) {
//            usersModel  myUsers = new usersModel(
//                    rs.getString("user_id"),
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getString("user_name"),
//                    rs.getString("email"),
//                    rs.getString("gender"),
//                    rs.getDate("birth_date"),
//                    rs.getString("user_category"),
//                    rs.getString("location"),
//                    rs.getString("user_status"),
//                    rs.getDate("registration_date")
//            );
//            AllUsers.add(myUsers);
//        }
//        Iterator it = AllUsers.iterator();
//        System.out.println("\t\t\t  #Id" + "\t\t\t First Name" +  "\t\t\t\t Last Name " +  "\t\t\t Username" +  "\t\t\t Email " +
//                "\t\t\t\t Gender" +"\t\t\t\t Birth Date" +  "\t\t\t\t User Category" +  "\t\t\t Location Name " + "\t\t\t User Status " + "\t\t\t Registration Date ");
//        System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        while(it.hasNext()){
//            usersModel user = (usersModel)it.next();
//            System.out.println(" \t\t\t\t "+user.getUserId() + " \t\t\t\t " + user.getFirstName()+ " \t\t\t\t " + user.getLastName() +
//                    " \t\t\t\t " + user.getUserName() + " \t\t\t\t " + user.getEmail()+ " \t\t\t\t " +" \t\t\t\t " +user.getGender()
//                    + " \t\t\t\t "+ user.getBirthDate()+" \t\t\t\t " + user.getUserCategoryName()+ " \t\t\t\t " + user.getUserStatus() + " \t\t\t\t " + user.getRegistrationDate());
//        }
//        connection.close();
//    }

    public static void viewAllUsersByStatus(String statusValue) throws Exception {
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        Connection connection= cloudStorageConnection.getConnection();
        Statement stmt = connection.createStatement();
//        String query= "SELECT users_table.user_id , users_table.first_name , users_table.first_name ,users_table.last_name , users_table.user_name , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , users_categories.user_category from users_table left join users_categories " +
//                "on users_table.user_id=users_categories.category_id where user_status ='"+statusValue+"'";

        String query = "SELECT users_table.user_id , users_table.first_name , users_table.first_name ,users_table.last_name , users_table.user_name , users_table.email , users_table.gender,users_table.birth_date, users_table.location, users_table.user_status , users_table.registration_date , users_categories.user_category from users_table left join users_categories " +
                "on users_table.user_category=users_categories.category_id where user_status ='"+statusValue+"'";

        ResultSet rs = stmt.executeQuery(query);
        ArrayList<usersModel> AllUsers = new ArrayList<usersModel>();
        while (rs.next()) {
            usersModel  myUsers = new usersModel(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getDate("birth_date"),
                    rs.getString("user_category"),
                    rs.getString("location"),
                    rs.getString("user_status"),
                    rs.getDate("registration_date")
            );
            AllUsers.add(myUsers);
        }
        Iterator it = AllUsers.iterator();

        view();

        System.out.println("\t\t\t  #Id" + "\t\t\t First Name" +  "\t\t\t\t Last Name " +  "\t\t\t Username" +  "\t\t\t Email " +
                "\t\t\t\t\t Gender" +"\t\t\t\t Birth Date" +  "\t\t\t\t User Category" +  "\t\t\t Location Name " + "\t\t\t User Status " + "\t\t\t Registration Date ");
        System.out.println("\t\t-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while(it.hasNext()){
            usersModel user = (usersModel)it.next();
            System.out.println(" \t\t\t\t "+user.getUserId() + " \t\t\t\t " + user.getFirstName()+ " \t\t\t\t " + user.getLastName() +
                    " \t\t\t\t " + user.getUserName() + " \t\t\t\t " + user.getEmail()+ " \t\t\t\t " +" " +user.getGender()
                    + " \t\t\t\t "+ user.getBirthDate()+" \t\t\t\t " + user.getUserCategoryName()+ " \t\t\t\t " + user.getLocationName()  + " \t\t\t\t\t " + user.getUserStatus() + " \t\t\t\t " + user.getRegistrationDate());
        }
        connection.close();
    }
}
