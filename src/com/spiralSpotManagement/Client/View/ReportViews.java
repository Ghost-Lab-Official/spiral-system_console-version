package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.TokenIssued;

public class ReportViews {

    public void showReports()throws Exception{

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/report");
        requestBody.setAction("totalNumbersOfViews");
        requestBody.setObject(null);

        /*
            Send Request Body
         */
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseBody responseBody1 = (ResponseBody) response;
            System.out.println("\t\t\t============================================= ");
            System.out.println("\t\t\t= ADMIN DASHBOARD/USERS/STATISTICS          = ");
            System.out.println("\t\t\t============================================= ");
            System.out.println("\t\t\t||   Number of registered users:     " +responseBody1.getResponse()+ "     ||");

        }


    }

}
