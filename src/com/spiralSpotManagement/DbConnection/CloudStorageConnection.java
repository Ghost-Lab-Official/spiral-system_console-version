package com.spiralSpotManagement.DbConnection;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.SQLError;

import java.sql.*;

public class CloudStorageConnection {
    public  Connection getConnection()throws Exception{
        Connection connection = null;

        try{
            String url = "jdbc:mysql://remotemysql.com:3306/2YQ7auowc7?" + "autoReconnect=true&useSSL=false";
            String username = "2YQ7auowc7";
            String password = "R2IMVJC67L";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
//            System.out.println("database connection is done ... ");
            return connection;
        }
//        catch (CommunicationsException e){
//            System.out.println("connection problem is occurring on the host server ");
//        }

        catch (SQLException e){
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

    public void checkDbWorking(Connection connection)throws Exception{
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
