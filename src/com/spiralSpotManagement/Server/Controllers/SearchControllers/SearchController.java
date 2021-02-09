package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abizera Oreste
 */
public class SearchController {

    public List<Object> mainMethod(RequestBody requestBody) throws Exception{
        String action = requestBody.getAction();
        List<Object> spots = new ArrayList<>();

        switch (action){
            case "getSpots":
                List<Spot> spotsList = new SearchActions().getSpots((Spot) requestBody.getObject());
                for (Spot spot: spotsList){
                    spots.add((Object) spot);
                }
                return spots;

            case "searchByPopularity":
                List<Object> popularSpots=new SearchActions().popularityArray();
               return popularSpots;

            //                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------
        }
        return  null;
    }

}