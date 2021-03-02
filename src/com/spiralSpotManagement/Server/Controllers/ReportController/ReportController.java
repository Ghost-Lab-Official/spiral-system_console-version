package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.Controllers.LocationControllers.LocationActions;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

public class ReportController {
    public List<Object> mainMethod(RequestBody request)throws Exception{
        String action = request.getAction();
        List<Object> userLogsReport = new ArrayList<>();
        List<Object> reportList = new ArrayList<>();

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

            case "getTotalNumberOfInactiveUsers":
                List<Object> getTotalNumberOfInactiveUsers = new UsersReportActions().getTheTotalNumberOfAllUsersByStatus("inactive");
                return getTotalNumberOfInactiveUsers;

            case "getAllLocations":
                List<Object> AllLocations = new LocationsReportsActions().viewAllLocations();
                return AllLocations;


                //Spot Cases


            case "getTotalNumberOfRegisteredSpots":
                List<Object> getTotalNumberOfRegisteredSpots = new SpotReportsActions().getTheTotalNumbersOfRegisteredSpots();
                return getTotalNumberOfRegisteredSpots;

            case "getTotalNumberOfActiveSpots":
                List<Object> getTotalNumberOfActiveSpots = new SpotReportsActions().getTheTotalNumberOfActiveSpots();
                return getTotalNumberOfActiveSpots;

            case "getTotalNumberOfInactiveSpots":
                List<Object> getTotalNumberOfInactiveSpots = new SpotReportsActions().getTheTotalNumberOfInactiveSpots();
                return getTotalNumberOfInactiveSpots;

            case "getTotalNumberOfTrendingSpots":
                List<Object> getTotalNumberOfTrendingSpots = new SpotReportsActions().getTheTotalNumbersOfTrendingSpots();
                return getTotalNumberOfTrendingSpots;
            case "getAllSpots":
                List<Object> allSpots = new SpotReportsActions().viewAllSpots();
                return allSpots;
            case "getAllActiveSpots":
                List<Object> allActiveSpots = new SpotReportsActions().viewAllActiveSpots();
                return allActiveSpots;
            case "getAllTrendingSpots":
                List<Object> allTrendingSpots = new SpotReportsActions().ViewHighlyVisitedSpots();
                return allTrendingSpots;
            case "getAllInactiveSpots":
                List<Object> allInactiveSpots = new SpotReportsActions().viewAllInactiveSpots();
                return allInactiveSpots;

            case "getTheNumberOfAllRegisteredLocations":
                List<Object> getTotalNumberOfAllLocations = new LocationsReportsActions().totalNumberOfRegisteredLocations();
                return getTotalNumberOfAllLocations;

            case "getTheNumberOfActiveLocations":
                List<Object> getTheNumberOfActiveLocations = new LocationsReportsActions().totalNumberOfLocationsByStatus("active");
                return getTheNumberOfActiveLocations;

            case "getTheNumberOfInActiveLocations":
                List<Object> getTheNumberOfInActiveLocations = new LocationsReportsActions().totalNumberOfLocationsByStatus("inactive");
                return getTheNumberOfInActiveLocations;

            case "viewAllActiveLocations":
                List<Object> allActiveLocations = new LocationsReportsActions().viewLocationsByStatus("active");
                return allActiveLocations;

            case "viewAllInActiveLocations":
                List<Object> allInactiveLocations = new LocationsReportsActions().viewLocationsByStatus("inactive");
                return allInactiveLocations;

            case "getSpotsByLocations":
                List<Object> SpotsByLocations = new LocationsReportsActions().getSpotsByLocation((String) request.getObject());
                return SpotsByLocations;

            case "viewTodaysSpots":
                List<Object> todaySpots = new SpotReportsActions().viewTodaysSpots();
                return todaySpots;
            case "viewTodaysTrendingSpots":
                List<Object> todayTrendingSpots = new SpotReportsActions().viewTodaysTrendingSpots();
                return todayTrendingSpots;

            case "viewThisMonthsSpots":
                List<Object> thismonthsSpots = new SpotReportsActions().viewThisMonthsSpots();
                return thismonthsSpots;
            case "viewThisMonthsTrendingSpots":
                List<Object> thismonthstrendingSpots = new SpotReportsActions().viewThisMonthsTrendingSpots();
                return thismonthstrendingSpots;

//            case "viewReportForAnotherDay":
//                List<Object> reportsForAnotherDay = new SpotReportsActions().getReportForAnotherDay();
//                return reportsForAnotherDay;
            //                OTHER ACTIONS SHOULD GO HERE
//            --------------------------------------
        }

        return null;
    }
}
