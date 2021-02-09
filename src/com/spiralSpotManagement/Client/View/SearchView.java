package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Abizera Oreste
 */

public class SearchView {
    public static final Scanner scanner = new Scanner(System.in);
    public void mainMethod() throws Exception{
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||                SEARCH OPTIONS           || ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  SEARCH SPOT                         ||");
        System.out.println("\t\t\t|| 2.  SEARCH PEOPLE                       ||");
        System.out.println("\t\t\t|| 3.  SEARCH MESSAGE                      ||");
        System.out.println("\t\t\t|| 4.  RECENT SEARCHES                     ||");
        System.out.println("\t\t\t============================================= ");
        System.out.print("Enter Your choice: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> searchSpot();
            case 2 -> searchPeople();
            case 3 -> searchMessages();
            case 4 -> recentSearches();
            default -> System.out.println("Invalid option");
        }
    }

    /**
     * display a spot
     */
    public static void displaySpot(List<Object> spotsList) throws Exception{
        Spot selectedSpot = null;
        System.out.println("Enter your Choice: ");
        int choice = scanner.nextInt();
        if(choice > spotsList.size()){
            System.out.println("Invalid Choice");
        }else {
            selectedSpot = (Spot) spotsList.get(choice - 1);
            System.out.println("=================== " + selectedSpot.getSpotName() + " =============");
            System.out.println("\t\t" + "Name" + ":\t  " + selectedSpot.getSpotName());
            System.out.println("\t\t" + "Description" + ":\t  " + selectedSpot.getSpotDescription());
            System.out.println("\t\t" + "Registered At" + ":\t  " + selectedSpot.getRegistrationDate());
            System.out.println("\t\t" + "Location" + ":\t  " + selectedSpot.getLocationId());
            System.out.println("\t\t" + "Category" + ":\t  " + selectedSpot.getCategoryId());
            System.out.println("\t\t" + "User" + ":\t  " + selectedSpot.getUserId());
        }

        System.out.println("\n\t\t Actions");
        System.out.println("\t\t 1. Like");
        System.out.println("\t\t 2. Comment");
        System.out.println("\t\t 3. Skip");

        System.out.print("Enter Choice: ");
        Integer action = scanner.nextInt();
        if(action == 1){
            likeSpot(selectedSpot);
        }else if (action == 2){
            commentOnSpot(selectedSpot);
        }
    }

    /**
     * Comment on a spot
     */

    public static void commentOnSpot(Spot spot){
        System.out.println("Comment on spot " + spot.getSpotId());
    }

    /**
     * Like a spot
     */

    public static void likeSpot(Spot spot){

        System.out.println("Like spot " + spot.getSpotId());
    }
    /**
     * Search a spot
     */
    public static void searchSpot() throws Exception{
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("getSpots");

        Spot spotToSend = new Spot();
        System.out.print("Search a spot: ");
        String searchKey = scanner.next();
        spotToSend.setSpotName(searchKey);
        requestBody.setObject(spotToSend);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        boolean found = false;
        Integer index = 0;
        List<Object> spotsList = new ArrayList<>();
        for (Object response: responseBody.getResponse()){
            index++;
            found = true;
            Spot spot = (Spot) response;
            System.out.println(index + ". " + spot.getSpotName());
            spotsList.add(spot);
        }

        if(!found){
            System.out.println("No results Found.");
        }else {
            displaySpot(spotsList);
        }

    }


    public static void searchPeople(){

    }


    public static void searchMessages(){

    }

    public static void recentSearches()throws Exception{
        RequestBody requestBody = new RequestBody();
        RecentSearch recentSearch = new RecentSearch();
//        recentSearch.setUser_id(1);
        Integer index = 1;
        Integer userId=1;

        User userToSearch = new User();
        userToSearch.setUserId(userId);
        requestBody.setUrl("/search");
        requestBody.setAction("viewRecentSearches");
        requestBody.setObject(userToSearch);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        List<Object> searches = new ArrayList<>();

        for (Object response: responseBody.getResponse()){
           RecentSearch rSearch = (RecentSearch) response;
            System.out.println(index + ". " + response);
            System.out.println("\t\t\t Recent searches\t\n");
//            System.out.println("\t search Query  \t date\n");
            System.out.println("\t "+rSearch.getSearchQuery()+"on"+rSearch.getDate());
//            searches.add(rSearch);
//            index++;
        }

        System.out.println("");
    }
}
