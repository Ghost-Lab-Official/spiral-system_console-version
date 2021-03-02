package com.spiralSpotManagement.Client.View;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/**
 *location management class. Method deleting given location
 * @author Mutoni Denyse
 * return boolean to indicated the success or fail to update.
 *
 */

public class UserView {

    public void registerUser()throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("\tUSER REGISTER");
        System.out.println("\t-----------------------");
        System.out.println("\tEnter your first name ");
        String firstName = scanner.nextLine();
        System.out.println("\tEnter your last name ");
        String lastName = scanner.nextLine();
        System.out.println("\tEnter your user name ");
        String userName = scanner.nextLine();
        System.out.println("\tEnter your email ");
        String email = scanner.nextLine();
        System.out.println("\tEnter your gender ");
        String gender = scanner.nextLine();
        System.out.println("\tEnter your birthDate");
        String birthDate = scanner.nextLine();
        System.out.println("\tEnter your password ");
        String password = scanner.nextLine();
        System.out.println("\tEnter your location ");
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
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t||------------------     SPIRAL ~ USER LOGIN       ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\tEnter your email ");
        String email = scanner.nextLine();
        System.out.println("\t\tEnter your password ");
        String password = scanner.nextLine();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        /*
               Define Request Body
         */

//        Create a user log

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
            try {
                System.out.println("\t\t --- Token issued: "+ ((TokenIssued) responseStatus.getObject()).getTokenValue());
                UserLog userLogToInsert = new UserLog();
                userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                userLogToInsert.setDateTimeLoggedIn(dateParser());
                userLogToInsert.setAction("logIn");
                userLogToInsert.setDateTimeLoggedOut(null);
                userLogToInsert.setTotalIn(1);
                userLogToInsert.setTotalOut(0);

                new ReportsView().createUserlog(userLogToInsert);
            }
            catch (Exception e){
                System.out.println("No token found");
            }

        }
    }
    public static String dateParser(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());

        return nowAsISO;
    }

}
