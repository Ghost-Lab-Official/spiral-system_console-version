package com.spiralSpotManagement.Server.Controllers.UserBilling;


import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

public class UserBillingController {

    public List<Object> mainMethod(RequestBody requestBody) throws Exception{
        String action = requestBody.getAction();
        List<Object> billProcess = new ArrayList<>();

        switch (action){
            case "pay":
                ResponseStatus responseStatus = new UserBillingActions().payBilling((UserBilling) requestBody.getObject());
                billProcess.add((Object) responseStatus);
                return billProcess;

            case "checkUserPlan":
                ResponseStatus checkResponseStatus = new UserBillingActions().checkUserBillingStatus((UserBillingServices) requestBody.getObject());
                billProcess.add((Object) checkResponseStatus);
                return billProcess;

        }
        return null;
    }



}
