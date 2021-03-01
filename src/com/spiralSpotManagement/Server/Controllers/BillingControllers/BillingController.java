package com.spiralSpotManagement.Server.Controllers.BillingControllers;

import com.spiralSpotManagement.Server.Model.BillingModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public class BillingController {

    public List<Object> mainMethod(RequestBody requestBody) throws Exception {

        String action = requestBody.getAction();
        List<Object> billing = new ArrayList<>();

        switch (action){
            case "delete":
                ResponseStatus deleteBilling = new BillingActions().DeleteBilling((BillingModel) requestBody.getObject());
                billing.add((Object) deleteBilling);
                return billing;

//           *******************************
//           ***OTHER CASE SHOULD GO HERE***
//           *******************************
        }

        return  null;
    }

}
