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


    public List<Spot> getSpots(Spot spot) throws Exception{
        List<Spot> spotsList = new ArrayList<>();
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try{
            String searchKey = spot.getSpotName();
            System.out.println(searchKey);
            String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' AND status = 1 ORDER BY viewers DESC LIMIT 10";
            PreparedStatement stmt = connection.prepareStatement(sql);
//
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


}
