package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class RecentSearch implements Serializable {
    private String searchQuery;
    private String date;

    public RecentSearch() {

    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }


  public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
