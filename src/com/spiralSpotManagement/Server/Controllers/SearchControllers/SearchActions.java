package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.Spot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abizera Oreste
 *
 */
public class SearchActions {


    public List<Spot> getSpots(Spot spot) throws Exception {
        List<Spot> spotsList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            String searchKey = spot.getSpotName();
            System.out.println(searchKey);
            String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%" + searchKey + "%' OR spot_description LIKE '%" + searchKey + "%' AND status = 1 ORDER BY viewers DESC LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);
//
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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

    public  ArrayList<String> popularityArray() throws Exception {
        
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        ArrayList<String> spots = new ArrayList<String>();
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
                if (!spots.contains(spotName)) {
                    spots.add(spotName);
                }


            }
            while (Viewsresults.next()) {
                String spotName = Viewsresults.getString("spot_name");


                if (!spots.contains(spotName)) {
                    spots.add(spotName);
                }
            }

            while (searchResults.next()) {

                String searchedSpot = searchResults.getString("searched_query");
                spots.add(searchedSpot);
            }

            return spots;
        } catch (Exception e) {
            return spots;
        }

    }
}
