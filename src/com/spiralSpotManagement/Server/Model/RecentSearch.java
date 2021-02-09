package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class RecentSearch implements Serializable {
    private Integer queryId;
    private String searchQuery;
    private String date;
<<<<<<< HEAD
    private Integer user_id;

//    public Integer getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Integer user_id) {
//        this.user_id = user_id;
//    }

    public RecentSearch() {

    }

    public RecentSearch(String searchQuery, String date) {
        this.searchQuery = searchQuery;
        this.date = date;
//        this.user_id = user_id;
    }

=======
    public RecentSearch() { }
>>>>>>> f7ef698bb72af71567e23c67b00d1182f3f1078f
    public String getSearchQuery() {
        return searchQuery;
    }
    public void setSearchQuery(String searchQuery){
        this.searchQuery = searchQuery;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public Integer getQueryId() {
        return queryId;
    }
    public void setQueryId(Integer queryId){
        this.queryId = queryId;
    }
}
