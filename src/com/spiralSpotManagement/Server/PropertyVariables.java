package com.spiralSpotManagement.Server;

import java.io.FileWriter;
import java.util.Properties;

public class PropertyVariables {
//    database configuration variables
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private Long dbPort;
//    server configuration variables
    private Long Port;

    public PropertyVariables(String dbUrl, String dbUsername, String dbPassword, Long dbPort, Long port) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbPort = dbPort;
        Port = port;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public Long getDbPort() {
        return dbPort;
    }

    public Long getPort() {
        return Port;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setDbPort(Long dbPort) {
        this.dbPort = dbPort;
    }

    public void setPort(Long port) {
        Port = port;
    }


    public void setPropertiesInFile()throws Exception{
        Properties properties = new Properties();
        properties.setProperty("dbUrl",getDbUrl());
        properties.setProperty("dbUsername",getDbUsername());
        properties.setProperty("dbPassword",getDbPassword());
        properties.setProperty("dbPort",getDbPort().toString());
        properties.setProperty("serverPort",getPort().toString());

        properties.store(new FileWriter("config.properties"),null);
    }
}
