package com.spiralSpotManagement.Server.DbController;




<<<<<<< HEAD
import com.mysql.jdbc.CommunicationsException;
<<<<<<< HEAD:src/com/spiralSpotManagement/DbConnection/CloudStorageConnection.java
=======
>>>>>>> 51666fed57da40a6e9afada80fe9a5551ed01084

import javax.naming.CommunicationException;
=======

import java.io.FileReader;
>>>>>>> 2b68fcb513352b9a109da749e395689cf8f87cbe:src/com/spiralSpotManagement/Server/DbController/CloudStorageConnectionHandler.java
import java.sql.*;
import java.util.Properties;

public class CloudStorageConnectionHandler {
    public  Connection getConnection()throws Exception{
        Connection connection = null;
        FileReader reader = new FileReader("config.properties");
        Properties propertiesStored = new Properties();
        propertiesStored.load(reader);

        try{
//            String url = "jdbc:mysql://remotemysql.com:3306/2YQ7auowc7?" + "autoReconnect=true&useSSL=false";
//            String username = "2YQ7auowc7";
//            String password = "R2IMVJC67L";

            String url = propertiesStored.getProperty("dbUrl");
            String username = propertiesStored.getProperty("dbUsername");
            String password = propertiesStored.getProperty("dbPassword");

<<<<<<< HEAD:src/com/spiralSpotManagement/DbConnection/CloudStorageConnection.java
//            Class.forName("com.mysql.jdbc.Driver");
=======


            //Class.forName("com.mysql.jdbc.Driver");
>>>>>>> 2b68fcb513352b9a109da749e395689cf8f87cbe:src/com/spiralSpotManagement/Server/DbController/CloudStorageConnectionHandler.java
            connection = DriverManager.getConnection(url,username,password);
//            System.out.println("database connection is done ... ");
            return connection;
        } catch (SQLException e){
            System.out.println("sql connection exception is occurring ... ");
            System.out.println(e);
        }

        catch (Exception e){
            System.out.println("High level failure of the system .... ");
            System.out.println(e);
        }
        return connection;
    }
    /*
            THIS IS  HOW WE WILL BE CALLING THE CONNECTION TO THE ONLINE DATABASE
     */

    public static void checkDbWorking(Connection connection)throws Exception{
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select  * from test_tb");
        System.out.println(" -------------------------- TEST TABLE DATA  ------------------------- ");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("username");
            String description = rs.getString("description");
            System.out.println(" Id : "+id+"\t\t name: "+name+"\t\t Desc: "+description);
            System.out.println(" ------------------------------------------------------------------------------------ ");
        }
    }

//    public static void main(String[] args)throws Exception {
//        checkDbWorking(getConnection());
//    }
}