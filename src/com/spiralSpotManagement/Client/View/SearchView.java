package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static com.spiralSpotManagement.Client.Main.welcomeToSpiral;

/**
 * @author Abizera Oreste
 * @author Kwizera Emmanuel
 */

public class SearchView {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final Scanner scanner = new Scanner(System.in);
    UserView userForms = new UserView();

    public void mainMethod() throws Exception{
        String cont = "";
        do {
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      WELCOME TO SPIRAL        ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.SEARCH SPOT              ------------------||");
            System.out.println("\t\t\t||------------------    2.SEARCH PEOPLE            ------------------||");
            System.out.println("\t\t\t||------------------    3.SEARCH MESSAGE           ------------------||");
            System.out.println("\t\t\t||------------------    4.POPULAR SEARCHES         ------------------||");
            System.out.println("\t\t\t||------------------    5.RECENT SEARCHES          ------------------||");
            System.out.println("\t\t\t||------------------    6.RETURN HOME              ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> searchSpot();
            case 2 -> searchPeople();
            case 3 -> searchMessages();
            case 4 -> searchPopular();
            case 5 -> {
                if(new UserAuthMiddleware().checkForUserExistence() != 0) {
                    viewRecentSearches();
                }
                System.out.println("You have to login first");
                userForms.loginUser();
            }

            case 6 -> welcomeToSpiral();
            default -> System.out.println("Invalid option");
        }

            System.out.print("Do you want to continue searching? (y/n): ");
            cont = scanner.next();
        }while (cont.equalsIgnoreCase("y") || cont.equalsIgnoreCase("yes"));
    }

    /**
     * Sends a request to display spot comments
     * @param spotId
     * @throws Exception
     */
    public static void displayComments(Integer spotId) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-comment");
        requestBody.setAction("getComments");

        requestBody.setObject((Object) spotId);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        boolean found = false;
        Integer index = 0;
        List<Object> commentsList = new ArrayList<>();
        for (Object response: responseBody.getResponse()){
            index++;
            found = true;
            Comment comment = (Comment) response;
            System.out.println(index + ". " + comment.getContent());
            commentsList.add(comment);
        }

        if(!found){
            System.out.println("No comments Found.");
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
            displayComments(selectedSpot.getSpotId());
        }
        else if(action == 4){
            welcomeToSpiral();
        }
        System.out.println("Invalid");
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
     * Search a spot
     */
    public static void searchSpot() throws Exception{
        try {
            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/search");
            requestBody.setAction("getSpots");

            Spot spotToSend = new Spot();
            System.out.print("Search a spot: ");
            String searchKey = scanner.next();
            //        create user log
            UserLog userLogToInsert = new UserLog();
            userLogToInsert.setUser_id(new UserAuthMiddleware().checkForUserExistence());
            userLogToInsert.setDateTimeLoggedIn("2021-02-10 05:10:08.000000");
            userLogToInsert.setAction("searching " + searchKey);
            userLogToInsert.setDateTimeLoggedOut(null);
            userLogToInsert.setTotalIn(5);
            userLogToInsert.setTotalOut(3);
            new ReportsView().createUserlog(userLogToInsert);

            spotToSend.setSpotName(searchKey);
            requestBody.setObject(spotToSend);

            ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
            boolean found = false;
            Integer index = 0;
            List<Object> spotsList = new ArrayList<>();
            for (Object response : responseBody.getResponse()) {
                index++;
                found = true;
                Spot spot = (Spot) response;
                String showDesc = spot.getSpotDescription().length() > 20 ? spot.getSpotDescription().substring(0,20) + "..." : spot.getSpotDescription();
                System.out.println(index + ". " + spot.getSpotName() + "\n\t\t" + ANSI_BLUE +  showDesc + ANSI_RESET);
                spotsList.add(spot);
            }

            if (!found) {
                System.out.println("No spots Found.");
            } else {
                displaySpot(spotsList);
            }
        }catch (Exception e){
            System.out.println("Error occured" + e.getMessage());
        }
    }


    /**
     * @author: Abizera Oreste
     * This method is used to search people
     */

    /**
     * @author: Blessing Hirwa
     * This method is used to get recent searches by a logged in user
     */

//    public static void getRecentSearches() throws Exception{
//        RequestBody requestBody = new RequestBody();
//        requestBody.setUrl("/search");
//        requestBody.setAction("viewRecentSearches");
//
//        User userIdToGetRecentSearches = new User();
//        requestBody.setObject(userIdToGetRecentSearches);
//        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
//        System.out.println("response is here : "+ responseBody);
//    }

    /**
     * Search with query param
     */
    public static void search(String searchQuery) throws Exception{
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("getSpots");
        Spot spotToSend = new Spot();
        spotToSend.setSpotName(searchQuery);
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
        UserLog userLogToInsertOnSearch = new UserLog();
        userLogToInsertOnSearch.setUser_id(new UserAuthMiddleware().checkForUserExistence());
        String logAction= "Searched " + searchKey;
        userLogToInsertOnSearch.setAction(logAction);

        new ReportsView().createUserlog(userLogToInsertOnSearch);
    }


    public static void searchMessages() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("getMessages");

        System.out.print("Search a comment: ");
        String searchKey = scanner.next();
        requestBody.setObject(searchKey);

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);
        if(responseBody.getResponse() == null){
            System.out.println("No comments found");
            return;
        }
        boolean found = false;
        Integer index = 0;
        List<Object> messagesList = new ArrayList<>();
        for (Object response: responseBody.getResponse()){
            index++;
            found = true;
            Comment comment = (Comment) response;
            System.out.println(index + ". " + comment.getContent());
            messagesList.add(comment);
        }

        if(!found){
            System.out.println("No comments Found.");
        }
    }

    /**
     * Method to view recent searches
     * @author: Mugisha ISaac and Ishimwe Pauline
     * comment: This is the method which prints recent searches of a logged in user
     * and then gives the user options to delete a recent search or to search again
     * using that query.
     */
    public static void viewRecentSearches() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("viewRecentSearches");
        User user = new User();
        user.setUserId(new UserAuthMiddleware().checkForUserExistence());
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
            System.out.println("No recent searches for this user, please search something!");
        }else {
            System.out.println("Options");
            System.out.println("\t 1.Delete The Recent Search \n \t 2.Search Again Using This Query");
            System.out.println("Enter Your Choice");
            Scanner sc = new Scanner(System.in);
            Integer  option = sc.nextInt();
            switch (option){
                case 1:
                    String delete = "";
                    System.out.println("Do you want to remove a recent research (yes/no)");
                    delete = scanner.next();
                    if (delete.equalsIgnoreCase("y") || delete.equalsIgnoreCase("yes")) {
                        System.out.println("Enter Recent Search To Delete: ");
                        Integer choice = scanner.nextInt();
                        if (choice > recentSearchList.size()) {
                            System.out.println("Invalid Choice. try, again");
                        } else {
                            RemoveRecentSearch(recentSearchList.get(choice - 1));
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter number of the query to Search");
                    Scanner sc2 = new Scanner(System.in);
                    Integer id = sc2.nextInt();
                    String selectedSearch = recentSearchList.get(id-1).getSearchQuery();
                    search(selectedSearch);
                    break;
                default:
                    System.out.println("Invalid Choice, Please Choose Again");
            }
        }
    }
    /**
     @uthor: Mugisha isaac
     comment: This the method which gives the status, message and action if the user
     deletes a recent search query from history. if status is 200 (ok), then this tells
     that the query was successfully deleted and the message will be a successfully message of
     deletion. action to do is to search again
     */
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
     *location management class. Method Recovering a deleted given location
     * @author Blessing Hirwa, Izere Kerie
     * return boolean a list of popular spots in the system
     *
     */
    public static void searchPopular() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/search");
        requestBody.setAction("searchByPopularity");
        requestBody.setObject(null);
        List<Object> popularSearches = new ArrayList<>();

        ResponseBody responseBody = new ClientServerConnector().ConnectToServer(requestBody);

        int i=1;
        for(Object response: responseBody.getResponse()){
            PopularSearch search = (PopularSearch) response;
            System.out.println(i + ". " + search.getSearch());
            popularSearches.add(search);
            i++;
        }
        System.out.println("Enter choice: ");
        Integer choice = scanner.nextInt();
        if(choice > popularSearches.size()){
            System.out.println("Invalid Choice.");
        }else{
            PopularSearch selectedSearch = (PopularSearch) popularSearches.get(choice-1);
            System.out.println("Search: " + selectedSearch.getSearch());
            RequestBody request = new RequestBody();
            request.setUrl("/search");
            request.setAction("getSpots");

            Spot spotToSend = new Spot();
            spotToSend.setSpotName(selectedSearch.getSearch());
            request.setObject(spotToSend);

            ResponseBody response = new ClientServerConnector().ConnectToServer(request);
            boolean found = false;
            Integer index = 0;
            List<Object> spotsList = new ArrayList<>();
            for (Object responseFound: response.getResponse()){
                index++;
                found = true;
                Spot spot = (Spot) responseFound;
                System.out.println(index + ". " + spot.getSpotName());
                spotsList.add(spot);
//                UserLog userLogToInsertOnSearch = new UserLog();
//                userLogToInsertOnSearch.setUser_id(new UserAuthMiddleware().checkForUserExistence());
//                String logAction= "Searched popular " +  spot.getSpotName();
//                userLogToInsertOnSearch.setAction(logAction);
//
//                new ReportsView().createUserlog(userLogToInsertOnSearch);
            }

            if(!found){
                System.out.println("No spots Found.\n");
            }else {
                displaySpot(spotsList);
            }


        }

    }

}