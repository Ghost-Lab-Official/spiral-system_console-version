package com.spiralSpotManagement.Client.Middleware;

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
<<<<<<< HEAD
        Object userId = (Object) properties.getProperty("UserId");

        if(userId == null){
            InputStream input = new FileInputStream("config.properties");
            // Writing token and other required credentials
            Properties props = new Properties();
            properties.load(input);
            properties.setProperty("Token","");
            properties.setProperty("UserId", String.valueOf(0));

            properties.store(new FileOutputStream("config.properties"),null);

            return 0;
        }
    return (Integer) userId;

=======

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
    }
    public Integer checkIfIsAdmin()throws Exception{
        InputStream inputStream = new FileInputStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Integer userRole = Integer.valueOf(properties.getProperty("ROLE"));

        return userRole;
>>>>>>> 4d5cf33725243552fe1a8eee1105460b9b2a3fcb
    }
}
