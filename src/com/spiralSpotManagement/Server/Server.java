package com.spiralSpotManagement.Server;

public class Server {
    public void startServer()throws Exception{
        PropertyVariables propertyVariables = new PropertyVariables("dburl","username","dbpass",1000l,1000l);
        propertyVariables.setPropertiesInFile();
    }
}
