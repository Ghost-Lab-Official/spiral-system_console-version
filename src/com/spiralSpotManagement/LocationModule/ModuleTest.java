package com.spiralSpotManagement.LocationModule;

import java.util.HashMap;
import java.util.List;

/**
 * This class is created for testing The whole location module
 * It extends <b>Location</b> class
 *
 * If You are using this location module, this class will serve as a documentation.
 *
 * @author Harerimana Egide
 * @since 2021-02-04
 * @version 1.0
 */
public class ModuleTest {

    private final Location location = new Location();

    public void testDbConnection(){
        try {
            location.checkDbWorking(location.getConnection());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckCreateLocationLevelsTable(){
        try {
            location.createLocationLevelsTable();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckCreateLocationsTable(){
        try {
            location.createLocationsTable();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckGetAllLocationsLevels(){
        try {
            List<HashMap> resultList = location.getAllLocationsLevels();
            System.out.println(resultList);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckRegisterLocationLevel(){
        try {
            String new_level_id = location.newLocationLevel("country");
            System.out.println(new_level_id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void checkRegisterLocation(){
        try {
            String new_location_id = location.newLocation("Nyabihu District","(1,2,3,4)", "Nyabihu district located in north rwanda", "2089b2b6-1b6e-46d0-8cde-3f532c4e9210", "");
            System.out.println(new_location_id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        /**
         *
         * test.CheckGetAllLocationsLevels();
         */
        ModuleTest test = new ModuleTest();
        test.CheckCreateLocationsTable();
        test.checkRegisterLocation();
    }
}
