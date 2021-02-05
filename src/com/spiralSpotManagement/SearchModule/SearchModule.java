package com.spiralSpotManagement.SearchModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|---------------    SPOTS SEARCHING       --------------|");
            System.out.println("|---------------        " + selectedResult.get("name") + " ------------------");
            System.out.println("|-------------------------------------------------------|");
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
            System.out.println("---------------" + results + " results shown ------------------");
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


    public static ArrayList<String> popularityByRatesArray(java.sql.Connection connection) throws SQLException {
        ArrayList<String> spots=new ArrayList<String>();
        String SelectRatesquery="select *from Spot_table order by rates desc limit 5";
        String SelectViewsquery="select *from Spot_table order by views desc limit 5";
        String SelectMostSearchedQuery = "SELECT searched_query FROM searchHistory GROUP BY searched_query ORDER BY COUNT(searched_query) DESC LIMIT 5";
        PreparedStatement ptRates=connection.prepareStatement(SelectRatesquery);
        PreparedStatement ptViews=connection.prepareStatement(SelectViewsquery);
        PreparedStatement sq = connection.prepareStatement(SelectMostSearchedQuery);
        ResultSet Ratesresults=ptRates.executeQuery();
        ResultSet Viewsresults=ptViews.executeQuery();
        ResultSet searchResults = sq.executeQuery();

        while( Ratesresults.next()) {
            String spotName= Ratesresults.getString("spot_name");
            if(!spots.contains(spotName)) {
                spots.add(spotName);
            }

        }
        while(  Viewsresults.next() ) {
            String spotName= Viewsresults.getString("spot_name");


            if(!spots.contains(spotName)) {
                spots.add(spotName);
            }
        }

        while (searchResults.next()){

            String searchedSpot = searchResults.getString("searched_query");
            spots.add(searchedSpot);
        }

        return spots;

    }

    //Method used to display popular spots in the console
    public static void DisplayPopularSpots(ArrayList<String> popularSpots) {
        for(int i=0;i<popularSpots.size();i++) {
            System.out.println(i+1+"."+popularSpots.get(i));
        }
    }

    public static void popularityEntry() throws Exception {
        Scanner scanner=new Scanner(System.in);
        // TODO Auto-generated method stub
        ArrayList<String> popularSpots= new ArrayList<String>();
        System.out.print("==== Search by popularity =====\n");

        //connect to the db
        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
        // cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
        popularityByRatesArray(cloudStorageConnection.getConnection());
        popularSpots.addAll(popularityByRatesArray(cloudStorageConnection.getConnection()));
        DisplayPopularSpots(popularSpots);
        int NO;
        System.out.print("choose on one");
        NO=scanner.nextInt();

        PreparedStatement ps=cloudStorageConnection.getConnection().prepareStatement("select spot_description from Spot_table where spot_name =?" );
        ps.setString(1,popularSpots.get(NO-1));
        ResultSet results=ps.executeQuery();
        while(results.next()) {
            String spotDescription=results.getString("spot_description");
            System.out.println("result :" +spotDescription );
        }


    }


    //Main file of the program
    public static void main(String[] args) throws Exception {
        popularityEntry();

    }


}
