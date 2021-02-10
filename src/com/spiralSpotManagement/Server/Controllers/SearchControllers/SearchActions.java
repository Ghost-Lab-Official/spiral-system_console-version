package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RecentSearch;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.Spot;
import com.spiralSpotManagement.Server.Model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abizera Oreste
 * @author: Kwizera Emmanuel
 **/

public class SearchActions {

    /**
     *
     * @param spot
     * @return List of found spots
     * @throws Exception
     * @comment This method is used to search spots according to the search query given. It searches according to spot name and spot description
     * @limit at most 10 spots at a time
     */
    public List<Spot> getSpots(Spot spot) throws Exception {
        List<Spot> spotsList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            String searchKey = spot.getSpotName();
            System.out.println(searchKey);
            String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%" + searchKey
                    + "%' OR spot_description LIKE '%" + searchKey + "%' AND status = 1 ORDER BY viewers DESC LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Spot spot1 = new Spot();
                spot1.setSpotName(rs.getString("spot_name"));
                spot1.setSpotDescription(rs.getString("spot_description"));
                spot1.setSpotId(rs.getInt("spot_id"));
                spot1.setStatus((rs.getInt("status")));
                spot1.setRegistrationDate(rs.getString("registration_date"));
                // spot1.setLocationId(rs.getInt("location_id"));
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
     * @param user
     * @return: list of found users
     * @throws Exception
     * @comment: This method is used to search people in the database according to searchKey provided
     * limit is 10 users at a fetch
     */

    public List<User> getPeople(User user) throws Exception {
        List<User> peopleList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            String searchKey = user.getUserName();
            System.out.println(searchKey);
            String sql = "SELECT * from users_table WHERE user_name LIKE '%"+searchKey+"%' OR first_name LIKE '%"+searchKey+"%' OR last_name LIKE '%"+searchKey+"%' OR email LIKE '%"+searchKey+"%' LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
        } catch (Exception e) {
            return peopleList;
        }
    }

    /**
     * @Author : Pauline Ishimwe this method will help you to display your recent searches
     * as a logged in user (last 10 at most)
     */
    public List<Object> DisplayRecentSearches(User user) throws Exception {

        List<Object> recentSearches = new ArrayList<>();
        try {
            Connection con = new CloudStorageConnectionHandler().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("select DISTINCT searched_query,search_date,history_id from searchHistory where user_id ='" + user.getUserId() + "' ORDER BY search_date DESC LIMIT 10");
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
        }catch (Exception e){
            return recentSearches;
        }
    }

    /**
     * @Author: MUGISHA ISAAC.
     * @Comment: this is  a method called RemoveRecentSearch which takes id of a query to delete
     * and also takes the user_id who is logged in. Then this method deletes the query where query_id is the same as
     * That stored in the table where the user_id is the same as that of the user who is logged in.
     * Thanks for whoever who will use this method as well as this class.
     * @Date: 9 Feb 2021
     * @copyright all right reserved.
     **/
    public ResponseStatus RemoveRecentSearch(User user, RecentSearch recentSearch) throws Exception {
        try {
            Connection con = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM searchHistory WHERE history_id=? AND user_id=? ");
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
  }
