package com.spiralSpotManagement.Server.Controllers.BillingControllers;

import com.spiralSpotManagement.Server.Model.BillingModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public class BillingController {

    public List<Object> mainMethod(RequestBody requestBody)throws Exception{
        String action = requestBody.getAction();
        List<Object> plans = new ArrayList<>();

        switch (action){
            case "register":
                ResponseStatus responseStatus = new BilllingActions().registerBillPlan((BillingModel) requestBody.getObject());
                plans.add((Object) responseStatus);
                return plans;
        }
        return  null;

    }


}
