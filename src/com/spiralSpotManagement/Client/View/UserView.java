package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.*;

import java.util.Scanner;

public class UserView {
    public void mainMethod() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("||\t\tUsers Section\t||\n");
        System.out.println("||\t\t1.Create user\t\t||\n");
        System.out.println("||\t\t2.Edit user  \t\t||\n");
        System.out.println("||\t\t3.View users \t\t||\n");
        System.out.println("||\t\t4.Delete user \t\t||\n");
        System.out.println("||\t\t5.Exit           \t\t||\n");
        System.out.println("==================================");
        Integer choice = input.nextInt();
        switch(choice) {
            case 1:
                registerUser();
                break;
            case 2:

                break;
            case 3:
                selectUsers();
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Incorrect input!!");
        }

    }
    public static void selectUsers() throws Exception{
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("getUsers");
        requestBody.setObject(null);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        System.out.println("\t\t\t List of Users\t\n");
        System.out.println("\t ID \t first name \t last name \t user name \t gender \t email \t birth date \t location\n ");
        for (Object response: responseBody.getResponse()){
            User user = (User) response;

            System.out.println("\t "+ user.getUserId() + "\t\t" + user.getFirstName()+" \t\t "+user.getLastName() + "\t\t"
            + user.getUserName() + "\t\t" + user.getGender() + "\t\t" + user.getEmail() + "\t\t" + user.getBirthDate() + "\t\t"
            + user.getLocation());
        }
    }
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
