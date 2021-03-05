
/*
        @author : Manzi Gusave
        @Description: the controller is for the Spot Ratings
 */

package com.spiralSpotManagement.Server.Controllers.SpotController;

import com.spiralSpotManagement.Server.Controllers.SpotCategoryControllers.SpotCategoryActions;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

public class SpotRatingController {
    public  List<Object> mainSpotController(RequestBody requestBody)throws Exception{
        String action = requestBody.getAction();
        List<Object> usersObject = new ArrayList<>();
        switch (action){
            case "register":
                ResponseStatus registeredSpotRating= new SpotRatingActions().createSpotRating((SpotRatings) requestBody.getObject());
                usersObject.add((Object) registeredSpotRating);
                return  usersObject;

            case "update":
                ResponseStatus updatedSpotRating= new SpotRatingActions().updateTheSpotRating((SpotRatings) requestBody.getObject());
                usersObject.add((Object) updatedSpotRating);
                return  usersObject;

            case "getRatings":
                List<SpotRatings> spotRatingsList = new SpotRatingActions().getRatings((Integer) requestBody.getObject());
                for (SpotRatings spotRating:spotRatingsList){
                    usersObject.add((Object) spotRating);
                }


//            case "updateStatus":
//                ResponseStatus updatedSpotRatingStatus = new SpotRatingActions().updateTheSpotRatingStatus((SpotRatings) requestBody.getObject());
//                usersObject.add((Object) updatedSpotRatingStatus);
//
//                return  usersObject;

            default:
                return usersObject;
        }
    }
}