package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.Controllers.LocationControllers.LocationActions;
import com.spiralSpotManagement.Server.Model.*;

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
            case "getTotalNumberOfUsers":
                List<Object> numberofAllUsers = new UsersReportActions().getTheTotalNumberOfAllUsersRegistered();
                return numberofAllUsers;

            case "getAllUsers":
                List<Object> allUsers = new UsersReportActions().viewAllUsers();
                return allUsers;
            case "getAllActiveUsers":
                List<Object> allActiveUsers = new UsersReportActions().viewAllUsersByStatus("active");
                return allActiveUsers;
            case "getAllInactiveUsers":
                List<Object> allInActiveUsers = new UsersReportActions().viewAllUsersByStatus("inactive");
                return allInActiveUsers;
            case "getTotalNumberOfActiveUsers":
                List<Object> numberofAllActiveUsers = new UsersReportActions().getTheTotalNumberOfAllUsersByStatus("active");
                return numberofAllActiveUsers;
            case "getAllLocations":
                List<Object> AllLocations = new LocationsReportsActions().viewAllLocations();
                return AllLocations;
                 //                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------
        }

        return null;
    }
}
