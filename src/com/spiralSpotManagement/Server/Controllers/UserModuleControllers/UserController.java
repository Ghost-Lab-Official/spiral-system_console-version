package com.spiralSpotManagement.Server.Controllers.UserModuleControllers;

import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.Users;

import java.util.ArrayList;
import java.util.List;
/*

            UNTILL HERE LET'S TRY TO SEND SOME LIST OF DATA : USERS TO THE SERVER TO CLIENT
 */

public class UserController {
    public List<Object> mainMethod(RequestBody requestBody){
        String action = requestBody.getIndex();
        switch (action){
            case "post":
                // all logic goes here
                break;
        }

        List<Users> usersList = new ArrayList<>();
        Users userExample = new Users();
        userExample.setEmail("ntwari@test.com");
        userExample.setFullName("gervais ntwari");

        // Lest duplicate users to get more users dummy data
        usersList.add(userExample);
        usersList.add(userExample);
        usersList.add(userExample);

        // because we need to return the object type not user type, let's type cast
        List<Object> usersObject = new ArrayList<>();
        for (Users user:usersList){
            usersObject.add((Object) user);
        }

        System.out.printf("users: "+usersObject);
        return  usersObject;
    }
}
