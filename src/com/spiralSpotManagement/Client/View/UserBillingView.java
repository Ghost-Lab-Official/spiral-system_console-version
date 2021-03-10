package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.util.Random;
import java.util.Scanner;

public class UserBillingView {

    public void userPayPlan()throws Exception{
        Integer user_id;
        Integer plan_id;
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the user ID: ");
//        user_id= scanner.nextInt();
        user_id = new UserAuthMiddleware().checkForUserExistence();
        System.out.println("Enter the plan Index: ");
        plan_id = scanner.nextInt();
        UserBilling userBilling = new UserBilling(user_id,plan_id);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users-billing");
        requestBody.setAction("pay");
        requestBody.setObject(userBilling);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);
        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }


    public Boolean checkUserPlanExistence(Integer userID,String service)throws Exception{

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the user ID: ");
//        userID= scanner.nextInt();
        UserBillingServices userBillingTwo = new UserBillingServices();
        UserBilling userBill = new UserBilling();
        userBill.setUser_id(userID);

        userBillingTwo.setUserBilling(userBill);
        userBillingTwo.setService(service);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users-billing");
        requestBody.setAction("checkUserPlan");
        requestBody.setObject(userBillingTwo);



        ClientServerConnector clientServerConnectorTwo = new ClientServerConnector();
        ResponseBody responseBodyTwo=  clientServerConnectorTwo.ConnectToServer(requestBody);
        for (Object response: responseBodyTwo.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;

        if(responseStatus.getStatus()==200){
            return true;
        } else{
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
            return false;
        }
        }

        return null;
    }




    public void getuserPlanInfo()throws Exception{
        RequestBody requestBody = new RequestBody();
        UserBilling userBilling = new UserBilling();
        userBilling.setUser_id(new UserAuthMiddleware().checkForUserExistence());

        requestBody.setUrl("/users-billing");
        requestBody.setAction("userPlanInfo");
        requestBody.setObject(userBilling);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response:responseBody.getResponse()){
            Billing responseBody1 = (Billing) response;
            System.out.println("+---------------------------------------+");
            System.out.println("|              MY PLAN INFO             |");
            System.out.println("+---------------------------------------+");
            System.out.println("Plan name: "+ responseBody1.getBilling_name());
            System.out.println("Plan period: "+ responseBody1.getBilling_period());
            System.out.println("Plan price: "+responseBody1.getPrice());
            System.out.println("\n");

        }
    }
}
