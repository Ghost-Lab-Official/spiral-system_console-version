package com.spiralSpotManagement.Client.Middleware;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class UserAuthMiddleware {
    public Integer checkForUserExistence()throws Exception{
        InputStream inputStream = new FileInputStream("config.properties");
        // Writing token and other required credentials
        Properties properties = new Properties();
        properties.load(inputStream);
        Integer userId = Integer.valueOf(properties.getProperty("UserId"));

        return userId;
    }

    public Integer checkIfIsAdmin()throws Exception{
        InputStream inputStream = new FileInputStream("config.properties");
        // Writing token and other required credentials
        Properties properties = new Properties();
        properties.load(inputStream);
        Integer userRole = Integer.valueOf(properties.getProperty("ROLE"));

        return userRole;
    }
}
