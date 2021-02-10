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

        System.out.println("Enter the period: ");
        Integer period = scanner.nextInt();

        BillingModel billingPlan = new BillingModel();
        billingPlan.setBilling_name(planName);
        billingPlan.setPrice(planPrice);
        billingPlan.setBilling_period(period);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/billing");
        requestBody.setAction("register");
        requestBody.setObject(billingPlan);

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


    public void updateBillingPlan()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new billing plan name: ");
        String planName = scanner.next();
        System.out.println("Enter the new price: ");
        Integer planPrice = scanner.nextInt();

        System.out.println("Enter the new period: ");
        Integer period = scanner.nextInt();

        System.out.println("Enter the new status [ACTIVE/INACTIVE]: ");
        String bill_status = scanner.next();

        BillingModel billingPlan = new BillingModel();
        billingPlan.setBilling_name(planName);
        billingPlan.setPrice(planPrice);
        billingPlan.setBilling_period(period);
        billingPlan.setBilling_status(bill_status);

//       hardcoding the billing id to update

        billingPlan.setBilling_id(1);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/billing");
        requestBody.setAction("update");
        requestBody.setObject(billingPlan);

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
