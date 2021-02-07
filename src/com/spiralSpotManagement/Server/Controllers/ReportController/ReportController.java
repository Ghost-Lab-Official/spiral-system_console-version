package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.Controllers.LocationControllers.LocationActions;
import com.spiralSpotManagement.Server.Model.LocationModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.UserLog;

import java.util.ArrayList;
import java.util.List;

public class ReportController {
    public List<Object> mainMethod(RequestBody request)throws Exception{
        String action = request.getAction();
        List<Object> userLogsReport = new ArrayList<>();

        switch (action){
            case "getUserLogs":
                List<Object> allUserLogs = new UserLogsActions().getAllUserLogs();
                return allUserLogs;

            case "createUserLog":
                ResponseStatus userLogResponseStatus = new UserLogsActions().recordUserLogs((UserLog) request.getObject());
                userLogsReport.add((Object) userLogResponseStatus);
                return userLogsReport;
//                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------
        }

        return null;
    }
}
