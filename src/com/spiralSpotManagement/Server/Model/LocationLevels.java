package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class LocationLevels implements Serializable {
  private Integer level_id;
  private String level_name;
  private String description;

    public LocationLevels(Integer level_id, String level_name, String description) {
        this.level_id = level_id;
        this.level_name = level_name;
        this.description = description;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
