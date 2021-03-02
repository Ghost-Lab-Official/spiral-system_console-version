package com.spiralSpotManagement.Server.Controllers.SearchControllers;

import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abizera Oreste
 * @comment This is the controller to render different methods
 *          according to the action provided in the request
 */
public class SearchController {

    /**
     *
     * @param requestBody
     * @return List of Objects which will be sent to the client
     * @throws Exception
     * @comment This method checks the action in the requestbody and directs the user to a given action
     */
    public List<Object> mainMethod(RequestBody requestBody) throws Exception {
        String action = requestBody.getAction();
        List<Object> results = new ArrayList<>();

        switch (action) {
            case "getSpots":
<<<<<<< HEAD
                User user1 = new User();
                user1.setUserId(new  UserAuthMiddleware().checkForUserExistence());
                List<Spot> spotsList = new SearchActions().getSpots((Spot) requestBody.getObject(),user1.getUserId());
                for (Spot spot: spotsList){
=======
                List<Spot> spotsList = new SearchActions().getSpots((Spot) requestBody.getObject());
                for (Spot spot : spotsList) {
>>>>>>> ba08fc119ddd37e242d6adb23a8e585930dc05d0
                    results.add((Object) spot);
                }
                return results;

            case "getPeople":
                List<User> peopleList = new SearchActions().getPeople((User) requestBody.getObject());
                for (User user : peopleList) {
                    results.add((Object) user);
                }
                return results;

<<<<<<< HEAD
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
=======
            case "viewRecentSearches":
                List<Object> recentSearchesList= new SearchActions().DisplayRecentSearches((User) requestBody.getObject());
                return recentSearchesList;
            case "RemoveRecentSearch":
                User user = new User();
                user.setUserId(1);
                ResponseStatus responseStatus = new SearchActions().RemoveRecentSearch(user,(RecentSearch) requestBody.getObject());
>>>>>>> ba08fc119ddd37e242d6adb23a8e585930dc05d0
                results.add(responseStatus);
                return results;

            default :

                System.out.println("the url is wrong");
        }
        return null;
    }

}
