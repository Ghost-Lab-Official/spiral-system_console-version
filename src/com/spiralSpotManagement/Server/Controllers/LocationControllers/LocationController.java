package com.spiralSpotManagement.Server.Controllers.LocationControllers;

import com.spiralSpotManagement.Server.Model.LocationModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

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
<<<<<<< HEAD
                break;
            case "getLocationsByParent":
                List<Object> locations = new LocationActions().fetchByParent((String) requestBody.getObject());
                return locations;
=======
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
>>>>>>> 7ba587afdf8e7651dee72448cacac6a475cb9991
//                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------
        }
        return  null;
    }

}