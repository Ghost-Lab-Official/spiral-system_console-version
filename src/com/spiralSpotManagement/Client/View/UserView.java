package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.*;

import java.util.Scanner;

public class UserView {
    public void registerUser()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your user name ");
        String userName = scanner.nextLine();
        System.out.println("Enter your email ");
        String email = scanner.nextLine();
        System.out.println("Enter your gender ");
        String gender = scanner.nextLine();
        System.out.println("Enter your birthDate");
        String birthDate = scanner.nextLine();
        System.out.println("Enter your password ");
        String password = scanner.nextLine();
        System.out.println("Enter your location ");
        String location = scanner.next();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthDate(birthDate);
        user.setPassword(password);
        user.setLocation(location);

        /*
               Define Request Body
         */
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("register");
        requestBody.setObject(user);

        /*
            Send Request Body
         */
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


    public void loginUser()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email ");
        String email = scanner.nextLine();
        System.out.println("Enter your password ");
        String password = scanner.nextLine();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        /*
               Define Request Body
         */
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("login");
        requestBody.setObject(user);

        /*
            Send Request Body
         */
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
            System.out.println("\t\t --- Token issued: "+ ((TokenIssued) responseStatus.getObject()).getTokenValue());

        }
    }


}
