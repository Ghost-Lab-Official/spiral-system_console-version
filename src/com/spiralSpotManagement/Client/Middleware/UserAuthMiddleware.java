package com.spiralSpotManagement.Client.Middleware;

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
        String returnedId = userId.toString();
    return Integer.parseInt(returnedId);

    }
}
