package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportActions {


    public ResponseBody numberOfAllRegisteredUsers() throws Exception, SQLException {
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT count(*) AS TotalUsers FROM users_table";
        ResultSet result = stmt.executeQuery(query);
        result.next();

        int count = result.getInt("TotalUsers");
        List<Object> counter = new ArrayList<>();
        counter.add(count);
        ResponseBody responseBody = new ResponseBody(counter);
        return responseBody;

    }




}
