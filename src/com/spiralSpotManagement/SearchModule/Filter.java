package com.spiralSpotManagement.SearchModule;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Filter {

    public void spotFilter(Statement stmt) throws Exception {
        Scanner scanInput=new Scanner(System.in);
        String searchKey;

        System.out.println("\n\t\t\t Search a spot: ");
        searchKey = scanInput.nextLine();

        String sql = "SELECT * from Spot_table WHERE spot_name LIKE '%"+searchKey+"%' OR spot_description LIKE '%"+searchKey+"%' ORDER BY registration_date DESC";

        ResultSet rs = stmt.executeQuery(sql);
        Boolean found = false;
        Integer results = 0;
        while (rs.next()){
            found = true;
            results++;
            System.out.println(rs.getString("spot_name") + ": " + rs.getString("spot_description"));
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
