package com.spiralSpotManagement.SearchModule;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Authors: Abizera Oreste and Kwizera Emmanuel

public class Filter {

    //method to display results
    public void displayResults (ArrayList<Map> searchResults){
        Scanner choiceScanner = new Scanner(System.in);
        System.out.println("Enter your Choice: ");
        Integer choice = choiceScanner.nextInt();
        if(choice-1 > searchResults.size()){
            System.out.println("Invalid Choice");
        }else {
            Map<String, String> selectedResult = searchResults.get(choice - 1);
            System.out.println("===================" + selectedResult.get("name") + "=============");
            for (Map.Entry<String, String> element : selectedResult.entrySet()) {
                System.out.println("\t\t" + element.getKey() + ":\t  " + element.getValue());
            }
        }
    };

    //method to search a spot
    public void spotFilter(Statement stmt) throws Exception {
        Boolean loggedIn = true;
        ArrayList<Map> searchResults = new ArrayList();
        Scanner scanInput=new Scanner(System.in);
        String searchKey;

        System.out.print("\n\t\t\t Search a spot: ");
        searchKey = scanInput.nextLine();

        String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' AND status = 1 ORDER BY registration_date DESC LIMIT 10";

        if(!loggedIn){
            //changing sql query when user is not logged in
            //sql = "SELECT * FROM Spot_table LIMIT 10";
        }
        ResultSet rs = stmt.executeQuery(sql);
        Boolean found = false;
        Integer results = 0;
        while (rs.next()){
            found = true;
            results++;
            Map<String,String> singleResult = new HashMap<>();
            singleResult.put("name",rs.getString("spot_name"));
            singleResult.put("description", rs.getString("spot_description"));
            singleResult.put("views", String.valueOf(rs.getInt("views")));
            singleResult.put("ratings", String.valueOf(rs.getInt("rates")));
            singleResult.put("viewers", String.valueOf(rs.getInt("viewers")));
            searchResults.add(singleResult);
            System.out.println(results + ". " + singleResult.get("name"));
        }

        if(!found){
            System.out.println("No results Found.");
        }else {
            System.out.println("===========" + results + " results shown ===============");
            displayResults(searchResults);
        }
    }


    public void peopleFilter(){
        System.out.println("Now we start");
    }
    public void messageFilter(){
        System.out.println("Now we start");
    }

}


