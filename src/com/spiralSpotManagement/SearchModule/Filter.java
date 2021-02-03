package com.spiralSpotManagement.SearchModule;

import java.util.Scanner;

public class Filter {
    public void spotFilter(){
        Scanner scanInput=new Scanner(System.in);
        String searchKey;

        System.out.println("\n\t\t\t Search a spot: ");
        searchKey = scanInput.nextLine();

        System.out.println(searchKey);

    }
    public void peopleFilter(){
        System.out.println("Now we start");
    }
    public void messageFilter(){
        System.out.println("Now we start");
    }

}
