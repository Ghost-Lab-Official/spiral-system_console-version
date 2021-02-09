package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Abizera Oreste
 * @author Kwizera Emmanuel
 */

public class SearchView {
    public static final Scanner scanner = new Scanner(System.in);
    public void mainMethod() throws Exception{
        String cont = "";
        do {
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      SPIRAL ~ SEARCH          ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.SEARCH SPOT              ------------------||");
            System.out.println("\t\t\t||------------------    2.SEARCH PEOPLE            ------------------||");
            System.out.println("\t\t\t||------------------    3.SEARCH MESSAGE           ------------------||");
            System.out.println("\t\t\t||------------------    4.POPULAR SEARCHES         ------------------||");
            System.out.println("\t\t\t||------------------    5.RECENT SEARCHES          ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> searchSpot();
            case 2 -> searchPeople();
            case 3 -> searchMessages();
            case 4 -> PopularSearch();
            case 5 -> RecentSearch();
            default -> System.out.println("Invalid option");
        }

        System.out.print("Do you want to continue searching? (y/n): ");
        cont = scanner.next();
        }while (cont.equalsIgnoreCase("y") || cont.equalsIgnoreCase("yes"));
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

    public void RecentSearch(){
        System.out.println("Hello recents");
    }
    public void PopularSearch(){
        System.out.println("Hello Popular");
    }
}
