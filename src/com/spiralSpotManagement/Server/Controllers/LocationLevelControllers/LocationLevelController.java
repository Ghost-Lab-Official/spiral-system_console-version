package com.spiralSpotManagement.Server.Controllers.LocationLevelControllers;

import com.spiralSpotManagement.Server.Model.LocationLevels;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
/**
 * Location levels (Synchronizing the classes)
 * @author Gervais Ishimwe
 */
public class LocationLevelController {
 public List<Object> mainMethod(RequestBody requestBody)throws Exception
 {
     String action = requestBody.getAction();
     List<Object> locationLevels = new ArrayList<>();
     switch (action){
         case "register":
             ResponseStatus  registerLocationLevel = new LocationLevelsActions().registerLocationLevel((LocationLevels) requestBody.getObject());
             locationLevels.add((Object)registerLocationLevel);
             return locationLevels;

         case "update":
<<<<<<< HEAD
             ResponseStatus updateLocationLevelResponse = new LocationLevelsActions().updateLocationLevel((LocationLevels) requestBody.getObject());
             locationLevels.add((Object) updateLocationLevelResponse);
             return locationLevels;

         case "delete":
             ResponseStatus deleteLocationLevelResponse = new LocationLevelsActions().deleteLocationLevel((LocationLevels) requestBody.getObject());
             locationLevels.add((Object) deleteLocationLevelResponse);
             return locationLevels;
=======
// call action method here
             break;

         case "get":


             break;
// ADD CASE TO GO HERE
//         ------------------------------------
>>>>>>> 218467288a7a24b68d7dc2e19adab6ee203a5662

     }
     return null;
 }


}
