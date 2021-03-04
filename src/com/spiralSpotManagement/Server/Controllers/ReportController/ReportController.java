package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class ReportController {

    public List<Object> mainMethod(RequestBody requestBody) throws Exception{
       String action = requestBody.getAction();
       List<Object> reportList = new ArrayList<>();

       switch (action){

           case "totalNumbersOfViews":
               ResponseBody responseBody = new ReportActions().numberOfAllRegisteredUsers();
               reportList.add(responseBody);
               return reportList;

           case "":
               break;

       }
        return  null;
    }
}
