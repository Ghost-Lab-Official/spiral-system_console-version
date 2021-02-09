package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Abizera Oreste
 * @author Kwizera Emmanuel
 *
 * SearchView class is defined for handling Search Views (Search on the client)
 */

public class SearchView {
    public static final Scanner scanner = new Scanner(System.in);
    public void mainMethod() throws Exception{
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t||                SEARCH OPTIONS           || ");
        System.out.println("\t\t\t============================================= ");
<<<<<<< HEAD
        System.out.println("\t\t\t|| 1.  SEARCH SPOT                         ||");
        System.out.println("\t\t\t|| 2.  SEARCH PEOPLE                       ||");
        System.out.println("\t\t\t|| 3.  SEARCH MESSAGE                      ||");
        System.out.println("\t\t\t|| 4.  RECENT SEARCHES                     ||");
=======
        System.out.println("\t\t\t|| 1.  RECENT SEARCHES                      ||");
        System.out.println("\t\t\t|| 2.  SEARCH SPOT                          ||");
        System.out.println("\t\t\t|| 3.  SEARCH PEOPLE                        ||");
        System.out.println("\t\t\t|| 4.  SEARCH MESSAGE                       ||");
>>>>>>> f7ef698bb72af71567e23c67b00d1182f3f1078f
        System.out.println("\t\t\t============================================= ");
        System.out.print("Enter Your choice: ");
        int option = scanner.nextInt();

        switch (option) {
<<<<<<< HEAD
            case 1 -> searchSpot();
            case 2 -> searchPeople();
            case 3 -> searchMessages();
            case 4 -> recentSearches();
=======
            case 1 -> viewRecentSearches();
            case 2 -> searchSpot();
            case 3 -> searchPeople();
            case 4 -> searchMessages();
>>>>>>> f7ef698bb72af71567e23c67b00d1182f3f1078f
            default -> System.out.println("Invalid option");
        }
    }


    /**
     * Method to view recent searches
     */
    public static void viewRecentSearches() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("viewRecentSearches");

        User user = new User();
        user.setUserId(1);

        requestBody.setObject(user);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        int i = 1;
        List<RecentSearch> recentSearchList = new ArrayList<>();
        for (Object response : responseBody.getResponse()){
            RecentSearch recentSearch = (RecentSearch) response;
            System.out.println(i + ". " + recentSearch.getSearchQuery());
            recentSearchList.add(recentSearch);
            i++;
        }
        if(recentSearchList.size() == 0){
            System.out.println("No results found");
        }else {
            String delete = "";
            System.out.println("Do you want to remove a recent research (y/n)");
            delete = scanner.next();
            if (delete.equalsIgnoreCase("y") || delete.equalsIgnoreCase("yes")) {
                System.out.println("Enter Recent Search: ");
                Integer choice = scanner.nextInt();
                if (choice > recentSearchList.size()) {
                    System.out.println("Invalid Choice");
                } else {
                    RemoveRecentSearch(recentSearchList.get(choice - 1));
                }
            }
        }
    }

    public static void RemoveRecentSearch(RecentSearch recentSearch) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("RemoveRecentSearch");
        requestBody.setObject(recentSearch);
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);
        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
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
        System.out.println("\t\t 3. View Comments");
        System.out.println("\t\t 4. Skip");

        System.out.print("Enter Choice: ");
        Integer action = scanner.nextInt();
        if(action == 1){
            likeSpot(selectedSpot);
        }else if (action == 2){
            commentOnSpot(selectedSpot);
        }else if(action == 3){
            new CommentView().viewComments(selectedSpot);
        }
    }

    /**
     * @author: Abizera Oreste
     * this method is for displaying a single person information
     */
    public static void displayUser(List<Object> usersList) throws Exception{
        User selectedUser = null;
        System.out.println("Enter your Choice: ");
        int choice = scanner.nextInt();
        if(choice > usersList.size()){
            System.out.println("Invalid Choice");
        }else {
            selectedUser = (User) usersList.get(choice - 1);
            System.out.println("=================== " + selectedUser.getUserName() + " =============");
            System.out.println("\t\t" + "Names" + ":\t  " + selectedUser.getFirstName() + " " + selectedUser.getLastName());
            System.out.println("\t\t" + "Email" + ":\t  " + selectedUser.getEmail());
            System.out.println("\t\t" + "Birthday At" + ":\t  " + selectedUser.getBirthDate());
            System.out.println("\t\t" + "Location" + ":\t  " + selectedUser.getLocation());
            System.out.println("\t\t" + "Category" + ":\t  " + selectedUser.getUserCategory());
            System.out.println("\t\t" + "Gender" + ":\t  " + selectedUser.getGender());
        }
    }

    /**
     * Comment on a spot
     */

    public static void commentOnSpot(Spot spot) throws Exception {

        System.out.println("Comment on spot " + spot.getSpotId());
        new CommentView().makeComment(spot);
    }

    /**
     * Like a spot
     */

    public static void likeSpot(Spot spot){

        System.out.println("Like spot " + spot.getSpotId());
    }


    /**
     * this method is used to Search a spot
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
            System.out.println("No spots Found.");
        }else {
            displaySpot(spotsList);
        }

    }


    /**
     * @author: Abizera Oreste
     * This method is used to search people
     */
    public static void searchPeople() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("getPeople");

        User userToSend = new User();
        System.out.print("Search a person: ");
        String searchKey = scanner.next();
        userToSend.setUserName(searchKey);
        requestBody.setObject(userToSend);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        boolean found = false;
        Integer index = 0;
        List<Object> usersList = new ArrayList<>();
        for (Object response: responseBody.getResponse()){
            index++;
            found = true;
            User user = (User) response;
            System.out.println(index + ". " + user.getUserName());
            usersList.add(user);
        }

        if(!found){
            System.out.println("No people Found.");
        }else {
            displayUser(usersList);
        }
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
