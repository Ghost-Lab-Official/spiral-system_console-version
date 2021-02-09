package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RecentSearch;
import com.spiralSpotManagement.Server.Model.Spot;
import com.spiralSpotManagement.Server.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abizera Oreste
 * @author: Kwizera Emmanuel
 **/

public class SearchActions {


    public List<Spot> getSpots(Spot spot) throws Exception{
        List<Spot> spotsList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try{
            String searchKey = spot.getSpotName();
            System.out.println(searchKey);
            String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' AND status = 1 ORDER BY viewers DESC LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Spot spot1 = new Spot();
                spot1.setSpotName(rs.getString("spot_name"));
                spot1.setSpotDescription(rs.getString("spot_description"));
                spot1.setSpotId(rs.getInt("spot_id"));
                spot1.setStatus((rs.getString("status")));
                spot1.setRegistrationDate(rs.getString("registration_date"));
//                spot1.setLocationId(rs.getInt("location_id"));
                spot1.setCategoryId(rs.getInt("category_id"));
                spot1.setUserId(rs.getInt("user_id"));
                spotsList.add(spot1);
            }
            return spotsList;
        }catch (Exception e){
            return spotsList;
        }
    }

/**
 *
 * @Author : Pauline Ishimwe this method will help you to display your recent searches
 * as a logged in user (last 10 at most)
 * */
    public List<Object> DisplayRecentSearches(User user) throws Exception {

        List<Object> recentSearches = new ArrayList<>();
        Connection con = new CloudStorageConnectionHandler().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery
                        ("select DISTINCT searched_query,search_date from searchHistory where user_id ="+user.getUserId());
        while (rs.next()) {
            String searchQuery = rs.getString("searched_query");
            String date = rs.getString("search_date");

            RecentSearch recentSearch = new RecentSearch();
            recentSearch.setSearchQuery(searchQuery);
            recentSearch.setDate(date);

            recentSearches.add((Object) recentSearch);
        }

        return recentSearches;
    }

  }