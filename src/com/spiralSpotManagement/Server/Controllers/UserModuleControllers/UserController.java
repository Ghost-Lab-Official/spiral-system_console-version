package com.spiralSpotManagement.Server.Controllers.UserModuleControllers;

import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.User;
import com.spiralSpotManagement.Server.Model.Users;

import java.util.ArrayList;
import java.util.List;
/*

            UNTILL HERE LET'S TRY TO SEND SOME LIST OF DATA : USER
 */

public class UserController {
    public List<Object> mainMethod(RequestBody requestBody)throws Exception{
        String action = requestBody.getAction();
        List<Object> usersObject = new ArrayList<>();
        switch (action){
            case "register":
                ResponseStatus registeredStatus = new UsersActions().registerUserInDb((User) requestBody.getObject());
                usersObject.add((Object) registeredStatus);
                return  usersObject;

            case "login":
                ResponseStatus loggedInStatus = new UsersActions().loginUser((User) requestBody.getObject());
                usersObject.add((Object) loggedInStatus);
                return  usersObject;
            case "getUsers":
                usersObject = new UsersActions().selectUsers();
                return usersObject;
            case "update-user-settings":
                ResponseStatus updatedStatus = new UsersActions().updateUserSettings((User) requestBody.getObject());
                usersObject.add((Object) updatedStatus);
                return usersObject;
            case "view-user-profile":
                 usersObject = new UsersActions().ViewUserProfile((User) requestBody.getObject());

                return usersObject;
        }

        return null;
    }
}
