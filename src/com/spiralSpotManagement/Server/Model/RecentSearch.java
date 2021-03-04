package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

/**
 * @Author : Pauline Ishimwe
 * description : this method will help you to deal with a recent searches it will help in viewing recent searches
 * */

public class RecentSearch implements Serializable {
    private Integer queryId;
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

    public Integer getQueryId() {
        return queryId;
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
