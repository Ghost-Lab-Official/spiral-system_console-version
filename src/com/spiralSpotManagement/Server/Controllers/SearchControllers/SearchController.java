package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abizera Oreste
 * This is the controller to render different methods according to the action provided in the request
 */
public class SearchController {

    public List<Object> mainMethod(RequestBody requestBody) throws Exception{
        String action = requestBody.getAction();
        List<Object> results = new ArrayList<>();

        switch (action){
            case "getSpots":
                List<Spot> spotsList = new SearchActions().getSpots((Spot) requestBody.getObject());
                for (Spot spot: spotsList){
                    results.add((Object) spot);
                }
                return results;

            case "getPeople":
                List<User> peopleList = new SearchActions().getPeople((User) requestBody.getObject());
                for (User user: peopleList){
                    results.add((Object) user);
                }
                return results;

<<<<<<< HEAD
=======
            case "searchByPopularity":
                List<Object> popularSpots=new SearchActions().popularityArray();
               return popularSpots;
>>>>>>> 31c7c92a6c1a10f9053b4a10a34cd5d958ad03dc

            //                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------


            case "viewRecentSearches":
                List<Object> recentSearchesList= new SearchActions().DisplayRecentSearches((User) requestBody.getObject());

                break;

            default :

                System.out.println("the url is wrong");
        }
        return  null;
    }

}
