package com.spiralSpotManagement.Client.View;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Controllers.UserModuleControllers.CounterResponse;
import com.spiralSpotManagement.Server.Model.*;

import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReportsView {
    public  String toContinue;
    Scanner scanInput = null;
    int choice;

    public ReportsView() { }

    public void reportDashboard() throws Exception {
        do {
            System.out.println("\t\t\t---------------------------------------------------");
            System.out.println("\t\t\t=                ADMIN DASHBOARD                  =");
            System.out.println("\t\t\t---------------------------------------------------");
            System.out.println("\t\t\t|| 1.  Home                                     ||");
            System.out.println("\t\t\t|| 2.  Spot related reports                     ||");
            System.out.println("\t\t\t|| 3.  Users related reports                    ||");
            System.out.println("\t\t\t|| 4.  Locations related reports                ||");
            System.out.println("\t\t\t|| 5.  Exit                                     ||");
            System.out.println("\t\t\t-------------------------------------------------=");

            System.out.println("Make a choice ");
            scanInput =new Scanner(System.in);
            choice = scanInput.nextInt();

            switch (choice) {
                case 1:
                    navigateToHome();
                    break;
                case 2:
                    navigateToSpotsManagement();
                    break;
                case 3:
                    navigateToUsersManagement();
                    break;
                case 4:
                    navigateToLocationsManagement();
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Do you want to continue Y/N ? ");
            Scanner scanner=new Scanner(System.in);
            toContinue = scanner.next();
        }
        while (toContinue.equals("Y") || toContinue.equals("y")) ;
    }

//    public void printDashboard()throws Exception{
//        try {
//            do{
//                reportDashboard();
//                System.out.println("Do you want to continue Y/N ? ");
////                System.out.println("Enter your word");
//
//                if(scanInput.hasNext()){
//                    toContinue=scanInput.next();
//            }
//            }while(toContinue.equals("Y") || toContinue.equals("y"));
//        }catch(Exception e){
//            System.out.println("Error on loop "+e.getMessage());
//            e.printStackTrace();
//        }
//
//
//    }

        public  void navigateToHome() {
            System.out.println("""
                \t\t\t\t Good management is the art of making problems so interesting
                \t\t\t\t and their solutions so constructive that everyone wants to get
                 \t\t\t\t to work and deal with them.""");
            System.out.println("\t\t\t\t welcome to spiral app’s management module");
        }

        //-----------------------------------------------------------------------------------------===
//--------------------------------------------===              SPOTS RELATED           --------------------------------------------
        public  void navigateToSpotsManagement() throws Exception {
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS                     = ");
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t|| 1.  View Statistics                     ||");
                System.out.println("\t\t\t|| 2.  View all  spots                     ||");
                System.out.println("\t\t\t|| 3.  View Spots by status                ||");
                System.out.println("\t\t\t|| 4.  View spot report by time            ||");
                System.out.println("\t\t\t|| 5.  back                                ||");
                System.out.println("\t\t\t|| 6.  Exit                                ||");
                System.out.println("\t\t\t-------------------------------------------- ");

                System.out.println("\t\t\t Make a choice: ");
                choice=scanInput.nextInt();

                switch (choice) {
                    case 1 :
                        viewAllStatistics();
                        break;
                    case 2 :
                        printAllSpots();
                        break;
                    case 3 :
                        viewSpotsByStatus();
                        break;
                    case 4 :
                        viewSpotReportsByTime();
                        break;
                    case 5 :
                        navigateToSpotsManagement();
                        break;
                    case 6:
                        System.out.println("Good bye!!! ");
                        System.exit(0);
                    break;
                    default :
                        System.out.println("\t\t\t\t Invalid input");
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }


        public  void viewAllStatistics() throws Exception {
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/STATISTICS          = ");
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t|| 1.  Number of registered spots          ||");
                System.out.println("\t\t\t|| 2.  Number of active spots              ||");
                System.out.println("\t\t\t|| 3.  Number of inactive spots            ||");
                System.out.println("\t\t\t|| 4.  Number of Highly visited spots      ||");
                System.out.println("\t\t\t|| 5.  Back                                ||");
                System.out.println("\t\t\t|| 6.  Exit                                ||");
                System.out.println("\t\t\t-------------------------------------------- ");


                System.out.println("Make a choice ");
                choice = scanInput.nextInt();
                switch (choice) {
                    case 1 :
                        String query="select count(spot_name) from Spot_table";
//                        printTheTotalNumberOfRegisteredSpots(query);
                        break;
                    case 2 :
//                        printTheTotalNumberOfActiveSpots();
                        break;
                    case 3 :
//                        printTheTotalNumberOfInActiveSpots();
                        break;
                    case 4 :
//                        String query="select count(spot_name) from Spot_table  where views>10 ";
//                        printTheTotalNumberOfHighlyVisitedSpots(query);
                        break;
                    case 5 :
                        navigateToSpotsManagement();
                        break;
                    case 6 :
                        System.out.println("Good bye!!! ");
                        System.exit(0);
                    break;
                    default :
                        System.out.println("\t\t\t\t Invalid input");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        public  void printAllSpots() throws Exception {
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
                System.out.println("\t\t\t--------------------------------------------- ");
//                spotReportController.viewAllActiveSpots();
            }
            catch(Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }

        }

        public  void viewSpotsByStatus() throws Exception {
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-by-status      = ");
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t|| 1.  View all active spots              ||");
                System.out.println("\t\t\t|| 2.  View all archived spots            ||");
                System.out.println("\t\t\t|| 3.  View top-5 highly visited spots    ||");
                System.out.println("\t\t\t|| 4.  Back                               ||");
                System.out.println("\t\t\t|| 5.  Exit                               ||");
                System.out.println("\t\t\t-------------------------------------------- ");


                System.out.println("Make a choice ");
                choice = scanInput.nextInt();

                switch (choice) {
                    case 1:
//                        spotReportController.viewAllActiveSpots();
                        break;
                    case 2:
//                        printAllInactiveSpots();
                        break;
                    case 3:
//                        PrintTop5HighlyVisitedSpots();
                        break;
                    case 4:
                        navigateToSpotsManagement();
                        break;
                    case 5:
                        System.out.println("Good bye!!! ");
                        System.exit(0);
                        break;
                }
            }
            catch(Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }

        }


        public  void viewSpotReportsByTime() throws Exception{
            try {
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-by-time        = ");
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t|| 1.  Today                              ||");
                System.out.println("\t\t\t|| 2.  This month                         ||");
                System.out.println("\t\t\t|| 3.  Another day                        ||");
                System.out.println("\t\t\t|| 4.  Another month                       ||");
                System.out.println("\t\t\t|| 5.  Back                               ||");
                System.out.println("\t\t\t|| 6.  Exit                               ||");
                System.out.println("\t\t\t-------------------------------------------- ");

                System.out.println("Make a choice ");
                choice = scanInput.nextInt();

                switch (choice) {
                    case 1:
                        viewTodaysSpots();
                        break;
                    case 2:
                        viewThisMonthsReport();
                        break;
                    case 3:
                        viewReportForAnotherDay();
                        break;
                    case 4:
                        viewReportForAnotherMonth();
                        break;
                    case 5:
                        navigateToSpotsManagement();
                        break;
                    case 6:
                        System.out.println("Good bye!!! ");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input");

                }
            }
            catch (Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }

        }


        public  void viewTodaysSpots()throws Exception{
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/by-Time/Today  = ");
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= 1. Spots created                          = ");
                System.out.println("\t\t\t= 2. Trending spot                          = ");
                System.out.println("\t\t\t= 3. Back                                   = ");
                System.out.println("\t\t\t= 4. Exit                                   = ");

                choice=scanInput.nextInt();

                switch (choice){
                    case 1:
//                        printTodaysSpots();
                        break;
                    case 2:
//                        printTodaysTrendingSpots();
                        break;
                    case 3:
                        navigateToSpotsManagement();
                    case 4:
                        System.out.println("Good bye!!! ");
                        System.exit(0);
                    default:
                        System.out.println("invalid input");

                }
            }
            catch (Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }
        }



        public  void viewThisMonthsReport()throws Exception{
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/by-Time/this-month  = ");
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= 1. Spots created                          = ");
                System.out.println("\t\t\t= 2. Trending spot                          = ");
                System.out.println("\t\t\t= 3. Back                                   = ");
                System.out.println("\t\t\t= 4. Exit                                   = ");

                choice=scanInput.nextInt();

                switch (choice){
                    case 1 :
//                        printThisMonthsSpots();
                        break;
                    case 2 :
//                        printThisMonthsTrendingSpots();
                        break;
                    case 3 :
                        navigateToSpotsManagement();
                        break;
                    case 4 :
                        System.out.println("Good bye!!! ");
                        System.exit(0);

                    default : System.out.println("invalid input");

                }
            }
            catch (Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }
        }

        public  void viewReportForAnotherDay()throws Exception{
            try{
                System.out.println("Enter the date that you want to get reports for. eg(February 5, 2021)");
                String anotherDate=scanInput.nextLine();
//            String anotherDate="February 5, 2021";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
                LocalDate myDate = LocalDate.parse(anotherDate, formatter);
                System.out.println(myDate);
//                spotReportController.getReportForAnotherDay(myDate);
            }catch (Exception e){
                System.out.println("Something went wrong! ");
                e.printStackTrace();
            }


        }
        public  void viewReportForAnotherMonth()throws Exception{
            try{
                System.out.println("Enter the month that you want to get reports for. eg(2)");
                int anotherMonth=scanInput.nextInt();
                System.out.println("Enter the Year that you want to get reports for. eg(2020)");
                int anotherYear=scanInput.nextInt();
//                spotReportController.getReportForAnotherMonth(anotherMonth,anotherYear);
            }catch (Exception e){
                System.out.println("Something went wrong! ");
                e.printStackTrace();
            }


        }
//--------------------------------------------===              END OF SPOTS             --------------------------------------------===


//-----------------------------------------------------------------------------------------===
//--------------------------------------------===              USERS RELATED           --------------------------------------------
public  void viewUsersByStatus()  {

try{
    System.out.println("\t\t\t--------------------------------------------- ");
    System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-by-status      = ");
    System.out.println("\t\t\t--------------------------------------------- ");
    System.out.println("\t\t\t|| 1.  View all active users              ||");
    System.out.println("\t\t\t|| 2.  View all Inactive users            ||");
    System.out.println("\t\t\t|| 3.  Back                               ||");
    System.out.println("\t\t\t|| 4.  Exit                               ||");
    System.out.println("\t\t\t-------------------------------------------- ");


    System.out.println("Make a choice ");
    choice = scanInput.nextInt();

    switch (choice) {
        case 1:
            viewAllActiveUsers();
            break;
        case 2:
            viewAllInactiveUsers();
            break;
        case 4:
            navigateToUsersManagement();
            break;
        case 5:
            System.exit(0);
            break;
        default:
            System.out.println("Invalid input");
    }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

        public  void navigateToUsersManagement() {

        try{
            System.out.println("\t\t\t--------------------------------------------- ");
            System.out.println("\t\t\t= ADMIN DASHBOARD/Users                     = ");
            System.out.println("\t\t\t--------------------------------------------- ");
            System.out.println("\t\t\t|| 1.  View user Statistics                ||");
            System.out.println("\t\t\t|| 2.  View all users                      ||");
            System.out.println("\t\t\t|| 3.  View user activities                ||");
            System.out.println("\t\t\t|| 4.  View user by status                 ||");
            System.out.println("\t\t\t|| 5.  Create user a user log              ||");
            System.out.println("\t\t\t|| 6.  Back                                ||");
            System.out.println("\t\t\t|| 7.  Exit                                ||");
            System.out.println("\t\t\t-------------------------------------------- ");

            System.out.println("\t\t\t Make a choice: ");
            scanInput =new Scanner(System.in);
            choice=scanInput.nextInt();

            UserLog userLogToInsert = new UserLog();
            userLogToInsert.setUser_id(3);
            userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
            userLogToInsert.setAction("created Spot");
            userLogToInsert.setDateTimeLoggedOut(null);
            userLogToInsert.setTotalIn(5);
            userLogToInsert.setTotalOut(3);

            switch (choice) {
                case 1 :
                    viewUserStatistics();
                    break;
                case 2 :
                    ViewAllUsers();
                    break;
                case 3 :
                    ViewUserActivities();
                    break;
                case 4 :
                    viewUsersByStatus();
                    break;
                case 5 :
                    createUserlog(userLogToInsert);
                    break;
                case 6 :
                    navigateToUsersManagement();
                case 7 :
                    System.exit(0);
                default :
                    System.out.println("\t\t\t\t Invalid input");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        }

    public  String dateParser(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());

        return nowAsISO;
    }

        private  void viewUserStatistics() throws Exception {
            System.out.println("\t\t\t--------------------------------------------- ");
            System.out.println("\t\t\t= ADMIN DASHBOARD/Users/STATISTICS          = ");
            System.out.println("\t\t\t--------------------------------------------- ");
            System.out.println("\t\t\t|| 1.  Number of registered Users          ||");
            System.out.println("\t\t\t|| 2.  Number of active users              ||");
            System.out.println("\t\t\t|| 3.  Number of Inactive users            ||");
            System.out.println("\t\t\t|| 4.  Back                                ||");
            System.out.println("\t\t\t|| 5.  Exit                                ||");
            System.out.println("\t\t\t-------------------------------------------- ");


            System.out.println("Make a choice ");
            choice = scanInput.nextInt();

            switch (choice) {
                case 1:
                    numberOfRegisteredUsers();
                    break;
                case 2:
                    numberOfActiveUsers();
                    break;
                case 3:
                    RequestBody request=new RequestBody();
                    request.setUrl("/report");
                    request.setAction("getTotalNumberOfInactiveUsers");
                    request.setObject(null);

                    ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
                    for(Object Response: responseBody.getResponse()){
                        CounterResponse counterResponse=(CounterResponse) Response;
                        System.out.println("The number of Inactive users is: " + counterResponse.getNumber());
                    }
                    break;

                case 4:
                    navigateToUsersManagement();
                    break;
                case 5:
                    System.out.println("Good bye!!! ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\t\t\t\t Invalid input");
            }


        }
     public void numberOfRegisteredUsers()throws Exception{
        try {
            System.out.println("\t\t\t---------------------------------------------");
            System.out.println("\t\t\t=    ADMIN DASHBOARD/USERS/N-All users      =");
            System.out.println("\t\t\t---------------------------------------------");

            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getTotalNumberOfUsers");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
            for(Object Response: responseBody.getResponse()){
                CounterResponse counterResponse=(CounterResponse) Response;
                System.out.println("The number of users is: " + counterResponse.getNumber());
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

     }

    public void numberOfActiveUsers()throws Exception{
        try {
            System.out.println("\t\t\t---------------------------------------------");
            System.out.println("\t\t\t=    ADMIN DASHBOARD/USERS/N-All Active users=");
            System.out.println("\t\t\t---------------------------------------------");

            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getTotalNumberOfActiveUsers");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
            for(Object Response: responseBody.getResponse()){
                CounterResponse counterResponse=(CounterResponse) Response;
                System.out.println("The number of Active users is: " + counterResponse.getNumber());
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }



    public void ViewAllUsers()throws Exception{
        try {

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
                    System.out.println("Starting");
                    System.out.println("\t\t\t---------------------------------------------");
                    System.out.println("\t\t\t=    ADMIN DASHBOARD/USERS/All users        =");
                    System.out.println("\t\t\t---------------------------------------------");
                    RequestBody request=new RequestBody();
                    request.setUrl("/report");
                    request.setAction("getAllUsers");
                    request.setObject(null);

                    ResponseBody responseBody = null;
                    try{
                        responseBody = new ClientServerConnector().ConnectToServer(request);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                    System.out.println(String.format("| %20s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-15s | %-25s | %-25s | %-25s |","#Id ","First name", "Last name","Username","Email","Gender","Birth date","User category","Location name","User status","Registration date"));
                    System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                    for(Object Response: responseBody.getResponse()){
                        UsersReport usersReport = (UsersReport) Response;

                        System.out.println(
                                String.format("| %25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                        usersReport.getUser_id(),
                                        usersReport.getFirst_name(),
                                        usersReport.getLast_name(),
                                        usersReport.getUser_name(),
                                        usersReport.getEmail(),
                                        usersReport.getGender(),
                                        usersReport.getBirth_date(),
                                        usersReport.getUser_category(),
                                        usersReport.getLocation(),
                                        usersReport.getUser_status(),
                                        usersReport.getRegistration_date())
                        );
                    }
                    System.out.println("helllo");
//                }
//            }).start();

           System.out.println("hey");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void viewAllActiveUsers()throws Exception{
        try{
            System.out.println("\t\t\t---------------------------------------------");
            System.out.println("\t\t\t=    ADMIN DASHBOARD/USERS/ActiveUsers       =");
            System.out.println("\t\t\t---------------------------------------------");

            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getAllActiveUsers");
            request.setObject(null);
            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);

            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            System.out.println(String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","Birth date","User category","Location name","User status","Registration date"));
            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            for(Object Response: responseBody.getResponse()){
                UsersReport usersReport = (UsersReport) Response;

                System.out.println(
                        String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
                                usersReport.getUser_id(),
                                usersReport.getFirst_name(),
                                usersReport.getLast_name(),
                                usersReport.getUser_name(),
                                usersReport.getEmail(),
                                usersReport.getGender(),
                                usersReport.getBirth_date(),
                                usersReport.getUser_category(),
                                usersReport.getLocation(),
                                usersReport.getUser_status(),
                                usersReport.getRegistration_date())
                );
            }
        }catch(Exception e1){
            System.out.println(e1.getMessage());
        }
    }


    public void viewAllInactiveUsers()throws Exception{
        try{
            System.out.println("\t\t\t---------------------------------------------");
            System.out.println("\t\t\t=    ADMIN DASHBOARD/USERS/ActiveUsers       =");
            System.out.println("\t\t\t---------------------------------------------");

            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getAllInactiveUsers");

            request.setObject(null);
            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);


            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            System.out.println(String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |","#Id ","First name", "Last name","Username","Email","Gender","Birth date","User category","Location name","User status","Registration date"));
            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            for(Object Response: responseBody.getResponse()){
                UsersReport usersReport = (UsersReport) Response;

                System.out.println(
                        String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
                                usersReport.getUser_id(),
                                usersReport.getFirst_name(),
                                usersReport.getLast_name(),
                                usersReport.getUser_name(),
                                usersReport.getEmail(),
                                usersReport.getGender(),
                                usersReport.getBirth_date(),
                                usersReport.getUser_category(),
                                usersReport.getLocation(),
                                usersReport.getUser_status(),
                                usersReport.getRegistration_date())
                );
            }
        }catch(Exception e1){
            System.out.println(e1.getMessage());
        }
    }
        public  void ViewUserActivities() throws Exception{

            System.out.println("\t\t\t---------------------------------------------");
            System.out.println("\t\t\t=    ADMIN DASHBOARD/All USERS/Activities   =");
            System.out.println("\t\t\t---------------------------------------------");

            RequestBody request = new RequestBody();
            request.setUrl("/report");
            request.setAction("getUserLogs");


            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
            System.out.format("+------------------------------+--------------------------------+-----------------------------+--------------------------+--------------------------+---------------------------------+%n");
            System.out.println(String.format("|%-15s | %-15s | %-25s | %-25s | %-25s | %-15s | %-15s|","#Id ","User_Id", "Date_Time_loggedIn","Action","Date_Time_loggedOut","TotalIn","TotalOut"));
            System.out.format("+------------------------------+--------------------------------+-----------------------------+--------------------------+--------------------------+---------------------------------+%n");

            for (Object response: responseBody.getResponse()){
                 UserLog userLog = (UserLog) response;

                System.out.println(
                        String.format("|%-15s | %-15s | %-25s | %-25s | %-25s | %-15s | %-15s|",
                                userLog.getId(),
                                userLog.getUser_id(),
                                userLog.getDateTimeLoggedIn(),
                                userLog.getAction(),
                                userLog.getDateTimeLoggedOut(),
                                userLog.getTotalIn(),
                                userLog.getTotalOut())
                );
            }

            System.out.println("eiooooooo");


        }



        public  void createUserlog(UserLog userLog) throws Exception {

//            UserLog userLogToInsert = new UserLog();
//            userLogToInsert.setUser_id(3);
//            userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
//            userLogToInsert.setAction("created spot");
//            userLogToInsert.setDateTimeLoggedOut(null);
//            userLogToInsert.setTotalIn(5);
//            userLogToInsert.setTotalOut(3);

            RequestBody request = new RequestBody();
            request.setUrl("/report");
            request.setAction("createUserLog");
            request.setObject(userLog);

            ClientServerConnector  clientServerConnector = new ClientServerConnector();
            ResponseBody responseBody = clientServerConnector.ConnectToServer(request);

            for (Object response: responseBody.getResponse()){
                ResponseStatus responseStatus = (ResponseStatus) response;
                System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
                System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
                System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
                System.out.println("\t\t ------------------------------------------------------------------------------");
            }

        }



    //=======================              END OF Users             ===================================


//=================================================================================================
//=======================          Locations RELATED           ====================================

    public void navigateToLocationsManagement() throws Exception {
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Locations                 = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View Locations Statistics           ||");
        System.out.println("\t\t\t|| 2.  View all Locations                  ||");
        System.out.println("\t\t\t|| 3.  View Locations by status            ||");
        System.out.println("\t\t\t|| 4.  View spots in a location            ||");
        System.out.println("\t\t\t|| 5.  Back                                ||");
        System.out.println("\t\t\t|| 6.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");

        System.out.println("\t\t\t Make a choice: ");
        int choice=scanInput.nextInt();

        switch (choice) {
            case 1 : viewLocationsStatistics();
                break;
            case 2 :
                RequestBody request= new RequestBody();
                request.setUrl("/report");
                request.setAction("getAllLocations");
                request.setObject(null);


                ClientServerConnector  clientServerConnector = new ClientServerConnector();
                ResponseBody responseBody = clientServerConnector.ConnectToServer(request);

                System.out.format("+----------------------------------------------+----------------------------------+-------------------------+---------------------------+------------------------------+%n");
                System.out.println(String.format("|%-50s | %-35s | %-35s | %-50s |%-30s |","#Id ", "Location Name","LOcation GPs","Description","Status"));
                System.out.format("+--------------------------------+----------------------------------+-------------------------+---------------------------+------------------------------+%n");


                for(Object response:responseBody.getResponse()){
                    LocationsReport location = (LocationsReport) response;

                    System.out.println(
                            String.format("|%-50s | %-25s | %-25s | %-50s |%-30s |",
                                    location.getLocationId(),
                                    location.getLocation_name(),
                                    location.getLocation_GPS(),
                                    location.getDescription(),
                                    location.getStatus())
                    );
                }
                break;

            case 3 : locationsByStatus();
                break;
            case 4 :

                break;
            case 5 : reportDashboard();
                break;
            case 6 : System.exit(0);
            default : System.out.println("\t\t\t\t Invalid input");
        }
    }

    public void viewLocationsStatistics(){

        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Locations/STATISTICS      = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  Number of registered Locations      ||");
        System.out.println("\t\t\t|| 2.  Number of active Locations          ||");
        System.out.println("\t\t\t|| 3.  Number of inactive Locations        ||");
        System.out.println("\t\t\t|| 4.  Back                                ||");
        System.out.println("\t\t\t|| 5.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput.nextInt();
        switch (choice) {
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 : System.exit(0);
            default : System.out.println("\t\t\t\t Invalid input");
        }

    }

    public  void locationsByStatus(){
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t= ADMIN DASHBOARD/Locations/View-by-status  = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  View active Locations               ||");
        System.out.println("\t\t\t|| 2.  View inactive Locations             ||");
        System.out.println("\t\t\t|| 3.  Back                                ||");
        System.out.println("\t\t\t|| 4.  Exit                                ||");
        System.out.println("\t\t\t============================================ ");


        System.out.println("Make a choice ");
        int choice = scanInput.nextInt();
        switch (choice) {
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t Invalid input");
        }
    }
}


