package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.BillingModel;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.Scanner;

public class BillingView {
    public void registerBillingPlan()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the billing plan: ");
        String planName = scanner.next();
        System.out.println("Enter the price: ");
        Integer planPrice = scanner.nextInt();

        BillingModel billingPLan = new BillingModel();
        billingPLan.setBilling_name(planName);
        billingPLan.setPrice(planPrice);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/billing");
        requestBody.setAction("register");
        requestBody.setObject(billingPLan);

        ClientServerConnector clientServerConnector = new ClientServerConnector();

        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);
        for(Object response : responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");

        }

    }
}
