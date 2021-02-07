package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.UserCategory;
import com.spiralSpotManagement.Server.Model.UserLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class UserLogsActions {
    String getAllUserLogsQuery = "select * from user_logs";
    public List<Object> getAllUserLogs()throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getAllUserLogsQuery);
        List<Object> userLogList = new ArrayList();
        while (result.next()){
            UserLog userLog = new UserLog();
            userLog.setId(result.getInt("id"));
            userLog.setUser_id(result.getInt("user_id"));
            userLog.setDateTimeLoggedIn(result.getString("date_Time_logged_Out"));
            userLog.setAction(result.getString("action"));
            userLog.setDateTimeLoggedOut(result.getString("date_Time_logged_Out"));
            userLog.setTotalIn(result.getInt("Total_in"));
            userLog.setTotalIn(result.getInt("Total_out"));
            userLogList.add((Object) userLog);
        }

        return userLogList;
    }

     public static String dateParser(){
         TimeZone tz = TimeZone.getTimeZone("UTC");
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss"); // Quoted "Z" to indicate UTC, no timezone offset
         df.setTimeZone(tz);
         String nowAsISO = df.format(new Date());

         return nowAsISO;
     }

    public ResponseStatus recordUserLogs(UserLog userLog) throws Exception{
        String recordUserLogsQuery="INSERT into user_logs(user_id, date_Time_logged_In,Action, date_Time_logged_Out,Total_In, Total_out) values (?, ?, ?, ?, ?, ?)";
        String getPreviousRowQuery="SELECT Total_In, Total_out  FROM user_logs ORDER by id DESC LIMIT 1";
        Connection connection=new CloudStorageConnectionHandler().getConnection();
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery(getPreviousRowQuery);
        while (rs.next()){
           if(userLog.getAction().equals("logIn")){
               System.out.println("Reached here");
               int loginCurrentTotalIn = rs.getInt("Total_in");
               int logoutCurrentTotalOut = rs.getInt("Total_out");
               userLog.setTotalIn(loginCurrentTotalIn+1);
               userLog.setTotalOut(logoutCurrentTotalOut);
           }
           else if(userLog.getAction().equals("logOut")){
               int loginCurrentTotalIn = rs.getInt("Total_in");
               int logoutCurrentTotalOut = rs.getInt("Total_out");
               System.out.println("date: "+dateParser());
               userLog.setDateTimeLoggedOut(dateParser());
               userLog.setTotalIn(loginCurrentTotalIn-1);
               userLog.setTotalOut(logoutCurrentTotalOut+1);
           }
           else{
               userLog.setTotalIn(rs.getInt("Total_in"));
               userLog.setTotalOut(rs.getInt("Total_out"));
           }
        }

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(recordUserLogsQuery);
            preparedStatement.setInt(1,userLog.getUser_id());
            preparedStatement.setString(2,userLog.getDateTimeLoggedIn());
            preparedStatement.setString(3,userLog.getAction());
            preparedStatement.setString(4,userLog.getDateTimeLoggedOut());
            preparedStatement.setInt(5,userLog.getTotalIn());
            preparedStatement.setInt(6,userLog.getTotalOut());

            int inserted =preparedStatement.executeUpdate();

            if (inserted == 1){
                return new ResponseStatus(200,"USER LOG ADDED","You have inserted the user log successfully");
            }

        }
        catch (Exception e){
            return new ResponseStatus(400,"BAD REQUEST",e.getMessage());
        }

        return null;
    }
}
