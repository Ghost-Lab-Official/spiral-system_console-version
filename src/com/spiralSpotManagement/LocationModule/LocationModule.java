package com.spiralSpotManagement.LocationModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.util.HashMap;

public class LocationModule {
    /**
     * Registering a location
     */
    public static boolean registerLocation(){
        HashMap<String,String> location = new HashMap<>();
        location.put("_id", "12345");
        return false;
    }

    public static void main(String[] args){
        registerLocation();
    }
}
