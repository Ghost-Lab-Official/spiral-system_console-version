package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
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
                User user1 = new User();
                user1.setUserId(new  UserAuthMiddleware().checkForUserExistence());
                List<Spot> spotsList = new SearchActions().getSpots((Spot) requestBody.getObject(),user1.getUserId());
                for (Spot spot: spotsList){
                    results.add((Object) spot);
                }
                return results;

            case "getPeople":
                User user2 = new User();
                user2.setUserId(new  UserAuthMiddleware().checkForUserExistence());
                List<User> peopleList = new SearchActions().getPeople((User) requestBody.getObject(),user2.getUserId());
                for (User user: peopleList){
                    results.add((Object) user);
                }
                return results;

            case "searchByPopularity":
                List<Object> popularSpots=new SearchActions().getMostPopularSearches();
               return popularSpots;

            //                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------



            case "viewRecentSearches":
                List<Object> recentSearchesList = new SearchActions()
                        .DisplayRecentSearches((User) requestBody.getObject());
                return recentSearchesList;
            case "RemoveRecentSearch":
                User user = new User();
                user.setUserId(new UserAuthMiddleware().checkForUserExistence());
                ResponseStatus responseStatus = new SearchActions().RemoveRecentSearch(user,
                        (RecentSearch) requestBody.getObject());
                results.add(responseStatus);
                return results;

            default :

                System.out.println("the url is wrong");
        }
        return  null;
    }

}
