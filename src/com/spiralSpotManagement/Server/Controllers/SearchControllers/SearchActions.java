package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abizera Oreste
 * @author: Kwizera Emmanuel
 **/

public class SearchActions {


    public List<Spot> getSpots(Spot spot) throws Exception {
        List<Spot> spotsList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            String searchKey = spot.getSpotName();
            System.out.println(searchKey);
            String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%" + searchKey + "%' OR spot_description LIKE '%" + searchKey + "%' AND status = 1 ORDER BY viewers DESC LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Spot spot1 = new Spot();
                spot1.setSpotName(rs.getString("spot_name"));
                spot1.setSpotDescription(rs.getString("spot_description"));
                spot1.setSpotId(rs.getInt("spot_id"));
                spot1.setStatus((rs.getInt("status")));
                spot1.setRegistrationDate(rs.getString("registration_date"));
//                spot1.setLocationId(rs.getInt("location_id"));
                spot1.setCategoryId(rs.getInt("category_id"));
                spot1.setUserId(rs.getInt("user_id"));
                spotsList.add(spot1);
            }
            return spotsList;
        } catch (Exception e) {
            return spotsList;
        }
    }

    /**
     *
     * @author: by Blessing and Izere Kerie
     * @return Array of Popular spots
     * @description  method to  make array of all popular stops to be displayed  and return it
     *@throws Exception
     */

    public List<User> getPeople(User user) throws Exception{
        List<User> peopleList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try{
            String searchKey = user.getUserName();
            System.out.println(searchKey);
            String sql = "SELECT * from users_table WHERE user_name LIKE '%"+searchKey+"%' OR first_name LIKE '%"+searchKey+"%' OR last_name LIKE '%"+searchKey+"%' LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User user1 = new User();
                user1.setFirstName(rs.getString("first_name"));
                user1.setLastName(rs.getString("last_name"));
                user1.setUserName(rs.getString("user_name"));
                user1.setEmail((rs.getString("email")));
                user1.setGender(rs.getString("gender"));
                user1.setLocation(rs.getString("location"));
                user1.setUserId(rs.getInt("user_id"));
                user1.setBirthDate(rs.getString("birth_date"));
                user1.setUserCategory(rs.getString("user_category"));
                peopleList.add(user1);
            }
            return peopleList;
        }catch (Exception e){
            return peopleList;
        }
    }

/**
 *
 * @Author : Pauline Ishimwe this method will help you to display your recent searches
 * as a logged in user (last 10 at most)
 * */

    public List<Object> DisplayRecentSearches(User user) throws Exception {

        List<Object> recentSearches = new ArrayList<>();
        try {
            Connection con = new CloudStorageConnectionHandler().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select DISTINCT searched_query,search_date,history_id from searchHistory where user_id ='"
                            + user.getUserId() + "' ORDER BY search_date DESC LIMIT 10");
            while (rs.next()) {
                String searchQuery = rs.getString("searched_query");
                String date = rs.getString("search_date");
                Integer historyId = rs.getInt("history_id");
                RecentSearch recentSearch = new RecentSearch();
                recentSearch.setSearchQuery(searchQuery);
                recentSearch.setDate(date);
                recentSearch.setQueryId(historyId);
                recentSearches.add((Object) recentSearch);
            }

            return recentSearches;
        } catch (Exception e) {
            return recentSearches;
        }
    }

    /**
     * @Author: MUGISHA ISAAC
     * @Comment: this is a method called RemoveRecentSearch which takes id of a
     *           query to delete and also takes the user_id who is logged in. Then
     *           this method deletes the query where query_id is the same as That
     *           stored in the table where the user_id is the same as that of the
     *           user who is logged in. 
     **/
    public ResponseStatus RemoveRecentSearch(User user, RecentSearch recentSearch) throws Exception {
        try {
            Connection con = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement statement = con
                    .prepareStatement("DELETE FROM searchHistory WHERE history_id=? AND user_id=? ");
            statement.setInt(1, recentSearch.getQueryId());
            statement.setInt(2, user.getUserId());
            int isDeleted = statement.executeUpdate();
            if (isDeleted == 1) {
                return new ResponseStatus(200, "OK", "QUERY DELETED SUCCESSFULLY");
            } else {
                return new ResponseStatus(400, "BAD REQUEST", "DELETION FAILED");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseStatus(500, "EXCEPTION ERROR", ex.getMessage());
        }
    }

    /**
     * @Author: Blessing Hirwa, Izere kerie
     * @Comment: this is a method called getMostPopularSearches which will get all popular searches i.e
     * most searched queries/spots
     * @ReturnType: list of type object
     * @Date: 9 Feb 2021
     * @copyright all rights reserved.
     **/
    public  List<Object> getMostPopularSearches() throws Exception {

        Connection connection = new CloudStorageConnectionHandler().getConnection();
        List<String>  PopularSearches = new ArrayList<>();
        List<Object> popularSearchesObject = new ArrayList<>();
        try {
            String SelectRatesquery = "select *from Spot_table order by rates desc limit 5";
            String SelectViewsquery = "select *from Spot_table order by views desc limit 5";

            String SelectMostSearchedQuery = "SELECT searched_query FROM searchHistory GROUP BY searched_query ORDER BY COUNT(searched_query) DESC LIMIT 5";
            PreparedStatement ptRates = connection.prepareStatement(SelectRatesquery);
            PreparedStatement ptViews = connection.prepareStatement(SelectViewsquery);
            PreparedStatement sq = connection.prepareStatement(SelectMostSearchedQuery);
            ResultSet Ratesresults = ptRates.executeQuery();
            ResultSet Viewsresults = ptViews.executeQuery();
            ResultSet searchResults = sq.executeQuery();

            while (Ratesresults.next()) {
                String spotName = Ratesresults.getString("spot_name");
                if (!PopularSearches.contains(spotName)) {
                    PopularSearches.add(spotName);
                }


            }
            while (Viewsresults.next()) {
                String spotName = Viewsresults.getString("spot_name");


                if (!PopularSearches.contains(spotName)) {
                    PopularSearches.add(spotName);
                }
            }

            while (searchResults.next()) {

                String searchedSpot = searchResults.getString("searched_query");
                PopularSearches.add(searchedSpot);
            }

            for (String popularSearch: PopularSearches){
                PopularSearch search = new PopularSearch ();
                search.setSearched(popularSearch);
                popularSearchesObject.add(search);
            }
            return popularSearchesObject;
        } catch (Exception e) {
            return popularSearchesObject;
        }

    }
}
