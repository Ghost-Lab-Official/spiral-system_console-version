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
                getUserProfile();
                break;
            case 3:
                selectUsers();
                break;
            case 4:
                updateUserSettings();
                break;
            case 5:
                deleteCategory();
                break;
            default:
                System.out.println("Incorrect input!!");
        }

    }
    public void selectUsers() throws Exception{
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

    /**
     * @author: Twizeyimana Elissa
     * @throws Exception
     */
    public void getUserProfile() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter user id!");
        Integer userId = scanner.nextInt();
        RequestBody requestBody = new RequestBody();
        User user = new User();
        user.setUserId(userId);
        requestBody.setUrl("/users");
        requestBody.setAction("view-user-profile");
        requestBody.setObject(user);
        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);


        System.out.println("\t ID \t first name \t last name \t user name \t gender \t email \t birth date \t location\n ");
        for (Object response: responseBody.getResponse()){
            User users = (User) response;
            System.out.println("ON "+users.getUserName()+"'s Desk");
            System.out.println("\t "+ users.getUserId() + "\t\t" + users.getFirstName()+" \t\t "+users.getLastName() + "\t\t"
                    + users.getUserName() + "\t\t" + users.getGender() + "\t\t" + users.getEmail() + "\t\t" + users.getBirthDate() + "\t\t"
                    + users.getLocation());
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
    public void deleteCategory() throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the user id to delete");
        int categoryId = scan.nextInt();
        User userToDelete = new User();
        userToDelete.setUserId(categoryId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("deleteUser");
        requestBody.setObject(userToDelete);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    /**
     * @author: Twizeyimana Elissa
     * @throws Exception
     */
    public void updateUserSettings() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the new data below");
        System.out.println("------------------------\n");

        System.out.println("Enter the user id you want to update");
        String userId = scanner.nextLine();
        System.out.print("Enter Your email:\t");
        String email = scanner.nextLine();
        System.out.println("Enter your first name");
        String first_name=scanner.nextLine();
        System.out.println("Enter your last name");
        String last_name=scanner.nextLine();
        System.out.println("Enter gender");
        String gender=scanner.nextLine();
        System.out.println("Enter your date of birth");
        String birth_date=scanner.nextLine();
        int userIdParsed = Integer.parseInt(userId);
        User user = new User();
        user.setUserId(userIdParsed);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthDate(birth_date);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("update-user-settings");
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
}
