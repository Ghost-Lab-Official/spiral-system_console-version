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
             break;
         case "get":
             break;
<<<<<<< HEAD
// ADD CASE TO GO HERE
//         ------------------------------------
=======
>>>>>>> 4d5cf33725243552fe1a8eee1105460b9b2a3fcb

     }
     return null;
 }


}
