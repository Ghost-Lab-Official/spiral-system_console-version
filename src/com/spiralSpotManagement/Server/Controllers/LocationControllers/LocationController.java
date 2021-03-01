package com.spiralSpotManagement.Server.Controllers.LocationControllers;

import com.spiralSpotManagement.Server.Controllers.LocationLevelControllers.LocationLevelsActions;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Location actions  (Synchronizing the classes)
 * @author Gervais Ishimwe
 */
public class LocationController {

    public List<Object> mainMethod(RequestBody requestBody) throws Exception{
        String action = requestBody.getAction();
        List<Object> location = new ArrayList<>();

        switch (action){
            case "register":
                ResponseStatus registerLocation = new LocationActions().registerLocation((LocationModel) requestBody.getObject());
                location.add((Object)registerLocation);
                return location;

            case "update":
                ResponseStatus updateLocation = new LocationActions().UpdateLocation((LocationModel) requestBody.getObject());
                location.add((Object) updateLocation);
                return  location;

            case "delete":
                ResponseStatus deleteLocation = new LocationActions().DeleteLocation((LocationModel) requestBody.getObject());
                location.add((Object) deleteLocation);
                return  location;
            case "recover":
                ResponseStatus recoverLocation = new LocationActions().RecoverLocation((LocationModel) requestBody.getObject());
                location.add((Object) recoverLocation);
                return location;
//                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------
        }
        return  null;
    }

}