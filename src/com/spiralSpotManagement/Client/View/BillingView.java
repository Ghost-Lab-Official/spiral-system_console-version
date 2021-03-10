package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Controllers.UserModuleControllers.CounterResponse;
import com.spiralSpotManagement.Server.Model.*;

import java.util.Scanner;

public class BillingView {


    public void mainMethod()throws Exception{
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t+             BILLING MANAGEMENT            + ");
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t|| 1.  REGISTER BILLING PLAN               ||");
        System.out.println("\t\t\t|| 2.  UPDATE BILLING PLAN                 ||");
        System.out.println("\t\t\t|| 3.  PREVIEW BILLING PLANS               ||");
        System.out.println("\t\t\t|| 4.  BUY A PLAN                          ||");
        System.out.println("\t\t\t|| 5.  Back                                ||");
        System.out.println("\t\t\t|| 6.  Exit                                ||");
        System.out.println("\t\t\t---------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 :
                if (new UserAuthMiddleware().checkForUserExistence() != 0 && new UserAuthMiddleware().checkIfIsAdmin() == 2){
                    registerBillingPlan();
                }
                else {
                    System.out.println("You have to login as an admin to view reports\n");
                    new UserView().loginUser();
                }

                break;
            case 2 :
                if (new UserAuthMiddleware().checkForUserExistence() != 0 && new UserAuthMiddleware().checkIfIsAdmin() == 2){

                    previewBillingPlans();
                    System.out.println("-------------------");
                    updateBillingPlan();
                }
                else {
                    System.out.println("You have to login as an admin to view reports\n");
                    new UserView().loginUser();
                }

                break;
            case 3 :
                previewBillingPlans();
                break;
            case 4 :
                previewBillingPlans();
                System.out.println("-------------------");
                new UserBillingView().userPayPlan();


                break;
            case 5 : System.exit(0);
            default : System.out.println("\t\t\t\t Invalid input");
        }


    }

    public void registerBillingPlan()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the billing plan: ");
        String planName = scanner.next();
        System.out.println("Enter the price: ");
        Integer planPrice = scanner.nextInt();

        System.out.println("Enter the period: ");
        Integer period = scanner.nextInt();

        Billing billingPlan = new Billing();
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
        System.out.println("Enter the index of plan from list: ");
        Integer billPlanId  = scanner.nextInt();
        System.out.println("Enter the new billing plan name: ");
        String planName = scanner.next();
        System.out.println("Enter the new price: ");
        Integer planPrice = scanner.nextInt();

        System.out.println("Enter the new period: ");
        Integer period = scanner.nextInt();

        System.out.println("Enter the new status [ACTIVE/INACTIVE]: ");
        String bill_status = scanner.next();

        Billing billingPlan = new Billing();
        billingPlan.setBilling_name(planName);
        billingPlan.setPrice(planPrice);
        billingPlan.setBilling_period(period);
        billingPlan.setBilling_status(bill_status);

//       hardcoding the billing id to update

        billingPlan.setBilling_id(billPlanId);

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

    public void previewBillingPlans()throws Exception{
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/billing");
        requestBody.setAction("selectAllBillingPlans");
        requestBody.setObject(null);

        ClientServerConnector clientServerConnector = new ClientServerConnector();

        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        Integer index = 1;

        System.out.println("+---------------------------------------------------------+");
        System.out.println("|Number\t\t Plan \t\tPrice \t\t\tPeriod \t\tStatus|");
        System.out.println("+---------------------------------------------------------+");
        for(Object response : responseBody.getResponse()){
            Billing response1 = (Billing) response;
            System.out.println(index+"\t\t | "+response1.getBilling_name()
                    +" \t\t| "+response1.getPrice()+"\t\t\t | "
                    +response1.getBilling_period()
                    +" \t\t| "+response1.getBilling_status());
            index++;
        }

    }

    public void previewBillingPlanById()throws Exception{

        Billing billingModel = new Billing();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the billing ID: ");
        Integer billId = scanner.nextInt();

        billingModel.setBilling_id(billId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/billing");
        requestBody.setAction("selectBillingById");
        requestBody.setObject(billingModel);

        ClientServerConnector clientServerConnector = new ClientServerConnector();

        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        Integer index = 1; // increment index counter for rows
        System.out.println("+--------------------------------------------------+");
        System.out.println("|Number\t\t Plan \t\tPrice \t\t\tPeriod \t\tStatus|");
        System.out.println("+--------------------------------------------------+");
        for(Object response : responseBody.getResponse()){
            Billing response1 = (Billing) response;
            System.out.println(index+"\t\t | "+response1.getBilling_name()
                    +" \t\t| "+response1.getPrice()+"\t\t\t | "
                    +response1.getBilling_period()
                    +" \t\t| "+response1.getBilling_status());
            index++;
        }

    }

    //    this method deals with activating or deactivating an existing billing plan
    public void actionOnBillingPlan()throws Exception {

        Billing billingModel = new Billing();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the billing ID: ");
        Integer billId = scanner.nextInt();

        System.out.println("Choose:\n1.ACTIVE\n2.INACTIVE");
        Integer statusChoice = scanner.nextInt();
        switch (statusChoice) {
            case 1 -> billingModel.setBilling_status("ACTIVE");
            case 2 -> billingModel.setBilling_status("INACTIVE");
            default -> System.out.println("Invalid choice");
        }

        billingModel.setBilling_id(billId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/billing");
        requestBody.setAction("actionOnStatus");
        requestBody.setObject(billingModel);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);
        for (Object response : responseBody.getResponse()) {
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: " + responseStatus.getStatus() + " ---------------------------");
            System.out.println("\t\t --------------         Meaning: " + responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: " + responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");

        }

    }
}