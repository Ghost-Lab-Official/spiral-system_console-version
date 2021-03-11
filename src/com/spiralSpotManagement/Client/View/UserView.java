package com.spiralSpotManagement.Client.View;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Controllers.UserModuleControllers.SendEmail;
import com.spiralSpotManagement.Server.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;

import static com.spiralSpotManagement.Client.Main.welcomeToSpiral;

/**
 *location management class. Method deleting given location
 * @author Mutoni Denyse
 * return boolean to indicated the success or fail to update.
 *
 */

public class UserView {



    public void mainMethod() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("||\t\t\tUSERS SECTION         ||\n");
        System.out.println("------------------------------------");
        System.out.println("||\t\t1.CREATE USER             ||\n");
        System.out.println("||\t\t2.GET LOGGED USER INFO    ||\n");
        System.out.println("||\t\t3.GET USER BY ID          ||\n");
        System.out.println("||\t\t4.GET ALL USERS           ||\n");
        System.out.println("||\t\t5.UPDATE USER             ||\n");
        System.out.println("||\t\t6.DELETE USER             ||\n");
        System.out.println("||\t\t7.RESET PASSWORD          ||\n");
        System.out.println("||\t\t8.USER CATEGORIES         ||\n");
        System.out.println("||\t\t9.VIEW MY PLAN            ||\n");
        System.out.println("||\t\t10.RETURN HOME            ||\n");
        System.out.println("====================================");
        Integer choice = input.nextInt();
        switch(choice) {
            case 1:
                registerUser();
                UserLog userLogToInsert = new UserLog();
                userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                userLogToInsert.setAction("Registered a user");

                new ReportsView().createUserlog(userLogToInsert);
                break;
            case 2:
                getUserProfile();
                UserLog userLogToInsertonReports = new UserLog();
                userLogToInsertonReports.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                userLogToInsertonReports.setAction("viewed user settings");

                new ReportsView().createUserlog(userLogToInsertonReports);
                break;
            case 3:
                selectUserById();
                break;
            case 4:
                selectUsers();
                break;
            case 5:
                UserLog userLogToInsert3 = new UserLog();
                userLogToInsert3.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                userLogToInsert3.setAction("updated user settings");

                new ReportsView().createUserlog(userLogToInsert3);
                updateUserSettings();
                break;
            case 6:
                deleteUser();
                break;
            case 7:
                UserLog userLogToInsert4 = new UserLog();
                userLogToInsert4.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                userLogToInsert4.setAction("Reseted password");

                new ReportsView().createUserlog(userLogToInsert4);
                restPassword();
                break;
            case 8:
                if(new UserAuthMiddleware().checkIfIsAdmin() != 0 && new UserAuthMiddleware().checkIfIsAdmin() == 2){
                    new UserCategoryView().mainMethod();
                }
                else{
                    System.out.println("\t\t YOU SHOULD LOGIN AS ADMIN TO PERFORM THIS ACTION");
                }
                break;

            case 9:
                if(new UserAuthMiddleware().checkIfIsAdmin() != 0){
                    new UserBillingView().getuserPlanInfo();
                }
                else{
                    System.out.println("\t\t YOU SHOULD LOGIN TO ACCESS THIS ACTION");
                }
                break;
            case 10:
                welcomeToSpiral();
            default:
                System.out.println("Incorrect input!!");
        }

    }

    public void restPassword()throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your email");
        String email=scanner.nextLine();
        User user = new User();
        user.setEmail(email);

        RequestBody requestBody = new RequestBody();
        requestBody.setObject(user);
        requestBody.setAction("reset-password");
        requestBody.setUrl("/users");

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody=  clientServerConnector.ConnectToServer(requestBody);

        String randomString = null;
        for (Object response: responseBody.getResponse()) {
            ResponseStatus responseStatus = (ResponseStatus) response;
            randomString = responseStatus.getObject().toString();

            System.out.println("\t\t -------------------------------------- STATUS: " + responseStatus.getStatus() + " ---------------------------");
            System.out.println("\t\t --------------         Meaning: " + responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: " + responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }

        System.out.println("\t\tEnter VERIFICATION CODE : ");
        String verificationCode = scanner.nextLine();

        if (verificationCode.equals(randomString)){

            System.out.println("\tEnter new Password : ");
            String newPassword = scanner.nextLine();
            requestBody.setAction("reset-password-second");
            requestBody.setUrl("/users");

            User newUserPassword = new User();
            newUserPassword.setPassword(newPassword);
            requestBody.setObject(newUserPassword);
            ResponseBody secondResponseBody=  clientServerConnector.ConnectToServer(requestBody);

            for (Object response: secondResponseBody.getResponse()) {
                ResponseStatus responseStatus = (ResponseStatus) response;

                System.out.println("\t\t -------------------------------------- STATUS: " + responseStatus.getStatus() + " ---------------------------");
                System.out.println("\t\t --------------         Meaning: " + responseStatus.getMessage());
                System.out.println("\t\t --------------         Action: " + responseStatus.getActionToDo());
                System.out.println("\t\t ------------------------------------------------------------------------------");
            }

        }
        else{
            System.out.println("You entered wrong verification Code !");
        }
    }

    public void selectUsers() throws Exception{
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("getUsers");
        requestBody.setObject(null);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        System.out.println("\t\t\t\t LIST OF USERS\t");
        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
        System.out.println(String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","User category","Birth date","User Location"));
        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
        for (Object response: responseBody.getResponse()){
            User user = (User) response;

//            System.out.println("\t "+ user.getUserId() + "\t\t" + user.getFirstName()+" \t\t "+user.getLastName() + "\t\t"
//            + user.getUserName() + "\t\t" + user.getGender() + "\t\t" + user.getEmail() + "\t\t" + user.getBirthDate() + "\t\t"
//            + user.getLocation());
            System.out.println(
            String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |",
                    user.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getGender(),
                    user.getBirthDate(),
                    user.getUserCategory(),
                    user.getLocation())
            );
        }
    }
    public void selectUserById() throws Exception{
        Scanner scanner=new Scanner(System.in);
        Integer userId = new UserAuthMiddleware().checkForUserExistence();
        RequestBody requestBody = new RequestBody();
        User user = new User();
        user.setUserId(userId);
        requestBody.setUrl("/users");
        requestBody.setAction("getUserById");
        requestBody.setObject(user);
        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);


//        System.out.println("\t ID \t first name \t last name \t user name \t gender \t email \t birth date \t location\n ");

        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
        System.out.println(String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","User category","Birth date","User Location"));
        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");

        for (Object response: responseBody.getResponse()){
            User users = (User) response;
            System.out.println("ON "+users.getUserName()+"'s Desk");
//            System.out.println("\t "+ users.getUserId() + "\t\t" + users.getFirstName()+" \t\t "+users.getLastName() + "\t\t"
//                    + users.getUserName() + "\t\t" + users.getGender() + "\t\t" + users.getEmail() + "\t\t" + users.getBirthDate() + "\t\t"
//                    + users.getLocation());

            System.out.println(
                    String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |",
                            users.getUserId(),
                            users.getFirstName(),
                            users.getLastName(),
                            users.getUserName(),
                            users.getEmail(),
                            users.getGender(),
                            users.getUserCategory(),
                            users.getBirthDate(),
                            users.getLocation())
            );
        }
    }
    public void getUserProfile() throws Exception{
        Scanner scanner=new Scanner(System.in);
        Integer userId = new UserAuthMiddleware().checkForUserExistence();
        RequestBody requestBody = new RequestBody();
        User user = new User();
        user.setUserId(userId);
        requestBody.setUrl("/users");
        requestBody.setAction("view-user-profile");
        requestBody.setObject(user);
        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);


        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
        System.out.println(String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","User category","Birth date","User Location"));
        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
        for (Object response: responseBody.getResponse()){
            User users = (User) response;
            System.out.println("ON "+users.getUserName()+"'s Desk");
            System.out.println(
                    String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |",
                            users.getUserId(),
                            users.getFirstName(),
                            users.getLastName(),
                            users.getUserName(),
                            users.getEmail(),
                            users.getGender(),
                            users.getUserCategory(),
                            users.getBirthDate(),
                            users.getLocation())
            );
        }
    }
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

            try {
//                System.out.println("\t\t --- Token issued: "+ ((TokenIssued) responseStatus.getObject()).getTokenValue());
                UserLog userLogToInsert = new UserLog();
                userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
                userLogToInsert.setAction("Joined Spiral (register) ");
                new ReportsView().createUserlog(userLogToInsert);
            }
            catch (Exception e){
                System.out.println("No token found");
            }
        }
    }
    public void loginUser()throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("\tUSER LOGIN");
        System.out.println("\t-----------------------");
        System.out.println("\tEnter your email ");
        String email = scanner.nextLine();
        System.out.println("\tEnter your password ");
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
//                System.out.println("\t\t --- Token issued: "+ ((TokenIssued) responseStatus.getObject()).getTokenValue());
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
    public void deleteUser() throws Exception{
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
     * @author: Elissa Twizeyimana
     * @description: Resetting user password using mailing system
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
