package com.spiralSpotManagement.LocationModule;

public class Location {
    private String location_id;
    private String level_id;
    private String parent_id;
    private String name;
    private String location_GPS;
    private String description;
    public Location(){};
    public Location(String loc_id, String lev_id, String par_id, String nam, String GPS, String desc){
        this.location_id = loc_id;
        this.level_id = lev_id;
        this.parent_id = par_id;
        this.name = nam;
        this.location_GPS = GPS;
        this.description = desc;
    };
}
