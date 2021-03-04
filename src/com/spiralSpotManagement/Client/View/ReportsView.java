package com.spiralSpotManagement.Client.View;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Controllers.UserModuleControllers.CounterResponse;
import com.spiralSpotManagement.Server.Model.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.spiralSpotManagement.Client.Main.welcomeToSpiral;

public class ReportsView {
    
    
    /**
     * @Author:Best Verie Iradukunda.
     * @Author:Mike Manzi.
     * @Comment: this class is intended to control the flow of information or operations on the admin panel.
     *Throught this class the admin will be able to navigate all over our system's admin panel. it contains some menus 
     *which make it easy for them (admins) to carry out every kind of tasks that are in this module. 
     *Each and every method contained in this class is to help us navigate throught the panel 
     *and it's where results from the server, are displayed.
     * @Date: 9 Feb 2021
     * @copyright all right reserved.
     **/
    
    
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
            System.out.println("\t\t\t|| 6.  RETURN HOME                              ||");
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
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                    welcomeToSpiral();
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Do you want to continue Y/N ? ");
            Scanner scanner=new Scanner(System.in);
            toContinue = scanner.next();
        }
        while (toContinue.equals("Y") || toContinue.equals("y")) ;
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
            case 1 -> viewLocationsStatistics();
            case 2 -> {
                RequestBody request = new RequestBody();
                request.setUrl("/report");
                request.setAction("getAllLocations");
                request.setObject(null);
                ClientServerConnector clientServerConnector = new ClientServerConnector();
                ResponseBody responseBody = clientServerConnector.ConnectToServer(request);
                System.out.format("+----------------------------------------------+----------------------------------+-------------------------+---------------------------+------------------------------+%n");
                System.out.println(String.format("|%-50s | %-35s | %-35s | %-50s |%-30s |", "#Id ", "Location Name", "LOcation GPs", "Description", "Status"));
                System.out.format("+--------------------------------+----------------------------------+-------------------------+---------------------------+------------------------------+%n");
                for (Object response : responseBody.getResponse()) {
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
            }
            case 3 -> locationsByStatus();
            case 4 -> {
                RequestBody myRequest7 = new RequestBody();
                myRequest7.setUrl("/report");
                myRequest7.setAction("getSpotsByLocations");
                myRequest7.setObject(null);
                try {
                    ClientServerConnector clientServerConnector1 = new ClientServerConnector();
                    ResponseBody responseBody1 = clientServerConnector1.ConnectToServer(myRequest7);

                    System.out.format("+----------------------------------------------+----------------------------------+-------------------------+---------------------------+------------------------------+%n");
                    System.out.println(String.format("|%-50s | %-35s | %-35s | %-50s |%-30s |", "#Id ", "Location Name", "LOcation GPs", "Description", "Status"));
                    System.out.format("+--------------------------------+----------------------------------+-------------------------+---------------------------+------------------------------+%n");


                    for (Object response : responseBody1.getResponse()) {
                        SpotsReport spotsReport = (SpotsReport) response;

                        System.out.println(
                                String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                        spotsReport.getSpot_id(),
                                        spotsReport.getSpot_name(),
                                        spotsReport.getCategory_name(),
                                        spotsReport.getUser_name(),
                                        spotsReport.getLocation_name(),
                                        spotsReport.getSpot_description(),
                                        spotsReport.getViews(),
                                        spotsReport.getStatus(),
                                        spotsReport.getRegistration_date())
                        );
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 5 -> reportDashboard();
            case 6 -> System.exit(0);
            default -> System.out.println("\t\t\t\t Invalid input");
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
                RequestBody request= new RequestBody();
                request.setUrl("/report");
                request.setAction("getTheNumberOfAllRegisteredLocations");
                request.setObject(null);
                try{
                    ClientServerConnector  clientServerConnector = new ClientServerConnector();
                    ResponseBody responseBody = clientServerConnector.ConnectToServer(request);
                    for(Object response:responseBody.getResponse()) {
                        CounterResponse counterResponse=(CounterResponse) response;
                        System.out.println("The number of locations is: " + counterResponse.getNumber());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
            case 2 :
                RequestBody myRequest= new RequestBody();
                myRequest.setUrl("/report");
                myRequest.setAction("getTheNumberOfActiveLocations");
                myRequest.setObject(null);

                try{
                    ClientServerConnector  clientServerConnector = new ClientServerConnector();
                    ResponseBody responseBody = clientServerConnector.ConnectToServer(myRequest);
                    for(Object response:responseBody.getResponse()) {
                        CounterResponse counterResponse=(CounterResponse) response;
                        System.out.println("The number of active locations is: " + counterResponse.getNumber());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3 :
                RequestBody myRequest2= new RequestBody();
                myRequest2.setUrl("/report");
                myRequest2.setAction("getTheNumberOfInActiveLocations");
                myRequest2.setObject(null);

                try{
                    ClientServerConnector  clientServerConnector = new ClientServerConnector();
                    ResponseBody responseBody = clientServerConnector.ConnectToServer(myRequest2);
                    for(Object response:responseBody.getResponse()) {
                        CounterResponse counterResponse=(CounterResponse) response;
                        System.out.println("The number of active locations is: " + counterResponse.getNumber());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                RequestBody myRequest2= new RequestBody();
                myRequest2.setUrl("/report");
                myRequest2.setAction("viewAllActiveLocations");
                myRequest2.setObject(null);

                try{
                    ClientServerConnector  clientServerConnector = new ClientServerConnector();
                    ResponseBody responseBody = clientServerConnector.ConnectToServer(myRequest2);

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

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2 :
                RequestBody myRequest3= new RequestBody();
                myRequest3.setUrl("/report");
                myRequest3.setAction("viewAllInActiveLocations");
                myRequest3.setObject(null);

                try{
                    ClientServerConnector  clientServerConnector = new ClientServerConnector();
                    ResponseBody responseBody = clientServerConnector.ConnectToServer(myRequest3);

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

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3 :
                try {
                    navigateToLocationsManagement();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t Invalid input");
        }
    }
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
                        printTheTotalNumberOfRegisteredSpots();
                        break;
                    case 2 :
                        printTheTotalNumberOfActiveSpots();
                        break;
                    case 3 :
                        printTheTotalNumberOfInactiveSpots();
                        break;
                    case 4 :
                        printTheTotalNumberOfTrendingSpots();
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



        public void printTheTotalNumberOfRegisteredSpots(){
                try {
                    RequestBody request=new RequestBody();
                    request.setUrl("/report");
                    request.setAction("getTotalNumberOfRegisteredSpots");
                    request.setObject(null);

                    ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
                    for(Object Response: responseBody.getResponse()){
                        CounterResponse counterResponse=(CounterResponse) Response;
                        System.out.println("The number of spots is: " + counterResponse.getNumber());
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
        }
        public void printTheTotalNumberOfActiveSpots(){
        try {
            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getTotalNumberOfActiveSpots");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
            for(Object Response: responseBody.getResponse()){
                CounterResponse counterResponse=(CounterResponse) Response;
                System.out.println("The number of spots is: " + counterResponse.getNumber());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printTheTotalNumberOfInactiveSpots(){
        try {
            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getTotalNumberOfInactiveSpots");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
            for(Object Response: responseBody.getResponse()){
                CounterResponse counterResponse=(CounterResponse) Response;
                System.out.println("The number of inactive spots is: " + counterResponse.getNumber());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printTheTotalNumberOfTrendingSpots(){
        try {
            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getTotalNumberOfTrendingSpots");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);
            for(Object Response: responseBody.getResponse()){
                CounterResponse counterResponse=(CounterResponse) Response;
                System.out.println("The number of spots is: " + counterResponse.getNumber());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public  void printAllSpots() {
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
                System.out.println("\t\t\t--------------------------------------------- ");

                RequestBody request=new RequestBody();
                request.setUrl("/report");
                request.setAction("getAllSpots");
                request.setObject(null);

                ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);

                System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                for(Object Response: responseBody.getResponse()){
                    SpotsReport SpotsReport = (SpotsReport) Response;

                    System.out.println(
                            String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                    SpotsReport.getSpot_id(),
                                    SpotsReport.getSpot_name(),
                                    SpotsReport.getCategory_name(),
                                    SpotsReport.getUser_name(),
                                    SpotsReport.getLocation_name(),
                                    SpotsReport.getSpot_description(),
                                    SpotsReport.getViews(),
                                    SpotsReport.getStatus(),
                                    SpotsReport.getRegistration_date())
                    );
                }
            }
            catch(Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }

        }

        public  void viewSpotsByStatus() {
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
                    case 1 -> viewAllActiveSpots();
                    case 2 -> viewAllInactiveSpots();
                    case 3 -> viewAllTrendingSpots();
                    case 4 -> navigateToSpotsManagement();
                    case 5 -> {
                        System.out.println("Good bye!!! ");
                        System.exit(0);
                    }
                }
            }
            catch(Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }

        }

        public void viewAllActiveSpots(){
            try{
                System.out.println("\t\t\t--------------------------------------------- ");
                System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
                System.out.println("\t\t\t--------------------------------------------- ");

                RequestBody request=new RequestBody();
                request.setUrl("/report");
                request.setAction("getAllActiveSpots");
                request.setObject(null);

                ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);

                System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                for(Object Response: responseBody.getResponse()){
                    SpotsReport SpotsReport = (SpotsReport) Response;

                    System.out.println(
                            String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                    SpotsReport.getSpot_id(),
                                    SpotsReport.getSpot_name(),
                                    SpotsReport.getCategory_name(),
                                    SpotsReport.getUser_name(),
                                    SpotsReport.getLocation_name(),
                                    SpotsReport.getSpot_description(),
                                    SpotsReport.getViews(),
                                    SpotsReport.getStatus(),
                                    SpotsReport.getRegistration_date())
                    );
                }
            }
            catch(Exception e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
            }
        }

    public void viewAllInactiveSpots()throws Exception{
        try{
            System.out.println("\t\t\t--------------------------------------------- ");
            System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
            System.out.println("\t\t\t--------------------------------------------- ");

            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getAllTrendingSpots");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);

            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            for(Object Response: responseBody.getResponse()){
                SpotsReport SpotsReport = (SpotsReport) Response;

                System.out.println(
                        String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                SpotsReport.getSpot_id(),
                                SpotsReport.getSpot_name(),
                                SpotsReport.getCategory_name(),
                                SpotsReport.getUser_name(),
                                SpotsReport.getLocation_name(),
                                SpotsReport.getSpot_description(),
                                SpotsReport.getViews(),
                                SpotsReport.getStatus(),
                                SpotsReport.getRegistration_date())
                );
            }
        }
        catch(Exception e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }

    public void viewAllTrendingSpots()throws Exception{
        try{
            System.out.println("\t\t\t--------------------------------------------- ");
            System.out.println("\t\t\t= ADMIN DASHBOARD/SPOTS/View-All            = ");
            System.out.println("\t\t\t--------------------------------------------- ");

            RequestBody request=new RequestBody();
            request.setUrl("/report");
            request.setAction("getAllInactiveSpots");
            request.setObject(null);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);

            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

            for(Object Response: responseBody.getResponse()){
                SpotsReport SpotsReport = (SpotsReport) Response;

                System.out.println(
                        String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                SpotsReport.getSpot_id(),
                                SpotsReport.getSpot_name(),
                                SpotsReport.getCategory_name(),
                                SpotsReport.getUser_name(),
                                SpotsReport.getLocation_name(),
                                SpotsReport.getSpot_description(),
                                SpotsReport.getViews(),
                                SpotsReport.getStatus(),
                                SpotsReport.getRegistration_date())
                );
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
                System.out.println("\t\t\t|| 4.  Back                               ||");
                System.out.println("\t\t\t|| 5.  Exit                               ||");
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
                        navigateToSpotsManagement();
                        break;
                    case 5:
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
                        RequestBody request=new RequestBody();
                        request.setUrl("/report");
                        request.setAction("viewTodaysSpots");
                        request.setObject(null);
                        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);


                        if((responseBody.getResponse()).isEmpty()){
                            System.out.println("NO data found! ");
                        }
                        else {
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            for(Object Response: responseBody.getResponse()){
                                SpotsReport SpotsReport = (SpotsReport) Response;

                                System.out.println(
                                        String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                                SpotsReport.getSpot_id(),
                                                SpotsReport.getSpot_name(),
                                                SpotsReport.getCategory_name(),
                                                SpotsReport.getUser_name(),
                                                SpotsReport.getLocation_name(),
                                                SpotsReport.getSpot_description(),
                                                SpotsReport.getViews(),
                                                SpotsReport.getStatus(),
                                                SpotsReport.getRegistration_date())
                                );
                            }
                        }
                        break;
                    case 2:
                        RequestBody req=new RequestBody();
                        req.setUrl("/report");
                        req.setAction("viewTodaysTrendingSpots");
                        req.setObject(null);
                        ResponseBody responseOfTodayTrending = new ClientServerConnector().ConnectToServer(req);


                        if((responseOfTodayTrending.getResponse()).isEmpty()){
                            System.out.println("NO data found! ");
                        }
                        else {
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            for(Object Response: responseOfTodayTrending.getResponse()){
                                SpotsReport SpotsReport = (SpotsReport) Response;

                                System.out.println(
                                        String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                                SpotsReport.getSpot_id(),
                                                SpotsReport.getSpot_name(),
                                                SpotsReport.getCategory_name(),
                                                SpotsReport.getUser_name(),
                                                SpotsReport.getLocation_name(),
                                                SpotsReport.getSpot_description(),
                                                SpotsReport.getViews(),
                                                SpotsReport.getStatus(),
                                                SpotsReport.getRegistration_date())
                                );
                            }
                        }
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
                        RequestBody req=new RequestBody();
                        req.setUrl("/report");
                        req.setAction("viewThisMonthsSpots");
                        req.setObject(null);
                        ResponseBody responseOfTodayTrending = new ClientServerConnector().ConnectToServer(req);


                        if((responseOfTodayTrending.getResponse()).isEmpty()){
                            System.out.println("NO data found! ");
                        }
                        else {
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            for(Object Response: responseOfTodayTrending.getResponse()){
                                SpotsReport SpotsReport = (SpotsReport) Response;

                                System.out.println(
                                        String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                                SpotsReport.getSpot_id(),
                                                SpotsReport.getSpot_name(),
                                                SpotsReport.getCategory_name(),
                                                SpotsReport.getUser_name(),
                                                SpotsReport.getLocation_name(),
                                                SpotsReport.getSpot_description(),
                                                SpotsReport.getViews(),
                                                SpotsReport.getStatus(),
                                                SpotsReport.getRegistration_date())
                                );
                            }
                        }
                        break;
                    case 2 :
                        RequestBody request=new RequestBody();
                        request.setUrl("/report");
                        request.setAction("viewThisMonthsTrendingSpots");
                        request.setObject(null);
                        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);


                        if((responseBody.getResponse()).isEmpty()){
                            System.out.println("NO data found! ");
                        }
                        else {
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                            for(Object Response: responseBody.getResponse()){
                                SpotsReport SpotsReport = (SpotsReport) Response;

                                System.out.println(
                                        String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                                SpotsReport.getSpot_id(),
                                                SpotsReport.getSpot_name(),
                                                SpotsReport.getCategory_name(),
                                                SpotsReport.getUser_name(),
                                                SpotsReport.getLocation_name(),
                                                SpotsReport.getSpot_description(),
                                                SpotsReport.getViews(),
                                                SpotsReport.getStatus(),
                                                SpotsReport.getRegistration_date())
                                );
                            }
                        }


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
                InputStream in;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the date that you want to get reports for. eg(February 5, 2021)");
                String anotherDate=reader.readLine();
//            String anotherDate="February 5, 2021";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
                LocalDate myDate = LocalDate.parse(anotherDate, formatter);
                System.out.println(myDate);
//                spotReportController.getReportForAnotherDay(myDate);

                RequestBody request=new RequestBody();
                request.setUrl("/report");
                request.setAction("viewReportForAnotherDay");
                request.setObject(null);
                ResponseBody responseBody = new ClientServerConnector().ConnectToServer(request);


                if((responseBody.getResponse()).isEmpty()){
                    System.out.println("NO data found! ");
                }
                else {
                    System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                    System.out.println(String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |","#Id ","Spot name", "Category_name","Creator","Location","Description","Views","Status","Location name","status","Registration date"));
                    System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");

                    for(Object Response: responseBody.getResponse()){
                        SpotsReport SpotsReport = (SpotsReport) Response;

                        System.out.println(
                                String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                                        SpotsReport.getSpot_id(),
                                        SpotsReport.getSpot_name(),
                                        SpotsReport.getCategory_name(),
                                        SpotsReport.getUser_name(),
                                        SpotsReport.getLocation_name(),
                                        SpotsReport.getSpot_description(),
                                        SpotsReport.getViews(),
                                        SpotsReport.getStatus(),
                                        SpotsReport.getRegistration_date())
                        );
                    }
                }



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
            System.out.println(String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-20s |","#Id ","First name", "Last name","Username","Email","Gender","Birth date","User category","Location name","User status","Registration date"));
            System.out.format("+-------+------------------+----------------+----------------+-------------+--------------+-------------------+--------------------+---------------------+------------------+-------------------------+%n");
            for(Object Response: responseBody.getResponse()){
                UsersReport usersReport = (UsersReport) Response;
                System.out.println(
                        String.format("| %4s | %-15s | %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-20s |",
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


        }



        public  void createUserlog(UserLog userLog) throws Exception {

            RequestBody request = new RequestBody();
            request.setUrl("/report");
            request.setAction("createUserLog");
            request.setObject(userLog);

            ClientServerConnector  clientServerConnector = new ClientServerConnector();
            ResponseBody responseBody = clientServerConnector.ConnectToServer(request);

        }




}


