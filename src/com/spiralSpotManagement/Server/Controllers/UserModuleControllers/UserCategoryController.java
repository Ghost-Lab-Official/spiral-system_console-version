package com.spiralSpotManagement.Server.Controllers.UserModuleControllers;

import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.User;
import com.spiralSpotManagement.Server.Model.UserCategory;

import java.util.ArrayList;
import java.util.List;

public class UserCategoryController {
    public List<Object> mainMethod(RequestBody requestBody)throws Exception{
        String action = requestBody.getAction();
        List<Object> usersObject = new ArrayList<>();
        switch (action){
            case "register":
                ResponseStatus responseStatus = new UserCategoryActions().createUserCategory((UserCategory) requestBody.getObject());
                usersObject.add((Object) responseStatus);
                return  usersObject;

            case "getUserCategories":
                usersObject = new UserCategoryActions().selectCategories();
                return  usersObject;
            case "updateUserCategory":
                ResponseStatus responseStatus2 = new UserCategoryActions().updateCategory((UserCategory) requestBody.getObject());
                usersObject.add((Object) responseStatus2);
                return usersObject;
        }

        return null;
    }
}
