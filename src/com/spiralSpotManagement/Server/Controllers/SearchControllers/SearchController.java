package com.spiralSpotManagement.Server.Controllers.SearchControllers;


import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.Spot;
import com.spiralSpotManagement.Server.Model.User;

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


//                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------


            case "viewRecentSearches":
<<<<<<< HEAD
           spots= new SearchActions().DisplayRecentSearches((User) requestBody.getObject());
//                spots.add(recentSearchesList);
                    return spots;
=======
                List<Object> recentSearchesList= new SearchActions().DisplayRecentSearches((User) requestBody.getObject());
                return recentSearchesList;
            case "RemoveRecentSearch":
                User user = new User();
                user.setUserId(1);
                ResponseStatus responseStatus = new SearchActions().RemoveRecentSearch(user,(RecentSearch) requestBody.getObject());
                results.add(responseStatus);
                return results;

>>>>>>> f7ef698bb72af71567e23c67b00d1182f3f1078f
            default :

                System.out.println("the url is wrong");
        }
        return  null;
    }

}