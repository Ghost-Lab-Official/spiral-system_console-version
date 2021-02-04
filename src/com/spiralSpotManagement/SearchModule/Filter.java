package com.spiralSpotManagement.SearchModule;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Filter {

    //Author: Oreste Abizera
    //Description: filter searches by spot
    public void spotFilter(Statement stmt) throws Exception {
        Scanner scanInput=new Scanner(System.in);
        String searchKey;

        System.out.println("\n\t\t\t Search a spot: ");
        searchKey = scanInput.nextLine();

        String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' AND status=1 ORDER BY registration_date DESC";

        ResultSet rs = stmt.executeQuery(sql);
        Boolean found = false;
        Integer results = 0;
        while (rs.next()){
            found = true;
            results++;
            String name = rs.getString("spot_name");
            String description = rs.getString("spot_description");
            Integer views = rs.getInt("views");
            Integer rates = rs.getInt("rates");
            System.out.println(results + ". " + name + ": " + description + "  " + views + " views" + "   " + rates + " ratings");
            System.out.println("========================================================\n");
        }

        if(!found){
            System.out.println("No results Found.");
        }else {
            System.out.println(results + " results shown");
        }

    }
    public void peopleFilter(){
        System.out.println("Now we start");
    }
    public void messageFilter(){
        System.out.println("Now we start");
    }

}
