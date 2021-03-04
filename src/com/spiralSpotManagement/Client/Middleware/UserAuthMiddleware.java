package com.spiralSpotManagement.Client.Middleware;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.View.ReportsView;
import com.spiralSpotManagement.Server.Model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class UserAuthMiddleware {
    public Integer checkForUserExistence()throws Exception{
        InputStream inputStream = new FileInputStream("config.properties");
        // Writing token and other required credentials
        Properties properties = new Properties();
        properties.load(inputStream);

        return Integer.valueOf(properties.getProperty("UserId"));
    }

    /**
     * @author : Muhodari Sage
     * @description: Logout functionality
     * @throws Exception
     */
    public void logoutMiddleWare()throws Exception {
        File file;
        InputStream inputStream = new FileInputStream("config.properties");
        // Writing token and other required credentials
        Properties properties = new Properties();
        properties.load(inputStream);
        properties.setProperty("Token", "");
        properties.setProperty("UserId", "0");

        properties.store(new FileOutputStream("config.properties"), null);

        System.out.println("\t\t -------------------------------------- STATUS: 200  ---------------------------");
        System.out.println("\t\t --------------         Meaning: LOGOUT IS DONE ");
        System.out.println("\t\t --------------         Action: Goodbye , See you later !");
        System.out.println("\t\t ------------------------------------------------------------------------------");

        UserLog userLogToInsert = new UserLog();
        userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
        userLogToInsert.setAction("logOut");


        new ReportsView().createUserlog(userLogToInsert);

    }
    public Integer checkIfIsAdmin()throws Exception{
        InputStream inputStream = new FileInputStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        return Integer.valueOf(properties.getProperty("ROLE"));
    }
}
