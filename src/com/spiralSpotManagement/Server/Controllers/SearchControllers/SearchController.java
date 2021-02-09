package com.spiralSpotManagement.Server.Controllers.SearchControllers;


import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.Spot;
import com.spiralSpotManagement.Server.Model.User;

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

            case "update":
                break;


//                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------


            case "viewRecentSearches":
           spots= new SearchActions().DisplayRecentSearches((User) requestBody.getObject());
//                spots.add(recentSearchesList);
                    return spots;
            default :

                System.out.println("the url is wrong");
        }
        return  null;
    }

}