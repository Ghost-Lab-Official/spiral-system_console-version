package com.spiralSpotManagement.SearchModule;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchModule {

    //method to display results
    public void displayResults (ArrayList<Map> searchResults){
        Scanner choiceScanner = new Scanner(System.in);
        System.out.print("\t\tEnter your Choice: ");
        Integer choice = choiceScanner.nextInt();
        if(choice > searchResults.size()){
            System.out.println("\t\tInvalid Choice");
        }else {
            Map<String, String> selectedResult = searchResults.get(choice - 1);
            System.out.println("=================== " + selectedResult.get("name") + " =============");
            for (Map.Entry<String, String> element : selectedResult.entrySet()) {
                System.out.println("\t\t" + element.getKey() + ":\t  " + element.getValue());
            }
        }
    };

    public String scanSearchKey(String type){
        Scanner scanInput=new Scanner(System.in);
        String searchKey;
        System.out.print("\n\t\t\t Search " + type +": ");
        searchKey = scanInput.nextLine();

        return searchKey;
    }

    //method to search a spot
    public void spotFilter(Statement stmt) throws Exception {
        Boolean loggedIn = false;
        ArrayList<Map> searchResults = new ArrayList();
        String searchKey = scanSearchKey("a spot");

        String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' AND status = 1 ORDER BY viewers DESC LIMIT 10";

        if(!loggedIn){
            //changing sql query when user is not logged in
            sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' AND status = 1 ORDER BY registration_date DESC LIMIT 10";

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
            singleResult.put("registered At", rs.getString("registration_date"));
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
        String searchKey = scanSearchKey("a person");
        System.out.println("Now we start");
    }
    public void messageFilter(){
        System.out.println("Now we start");
    }




}
