package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.Spot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;

/**
 * @authors: Blessing Hirwa
 * This class is contains required forms to help a user create,update, and delete a spot
 */

public class FormsView {
    public Spot createSpotViewForm() throws Exception {
        Scanner scanner = new Scanner(System.in);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader entered = new BufferedReader(isr);
        System.out.println("Enter category id");
        Integer categoryId = scanner.nextInt();
        System.out.println("Enter your location Id ");
        Integer locationId = scanner.nextInt();
        System.out.println("Enter spot name ");
        String spotName = entered.readLine();
        System.out.println("Enter spot description ");
        String spotDescription = entered.readLine();
//        System.out.println("Enter spot status ");
        Integer spotStatus = 1;

        Spot spot = new Spot();
        spot.setUserId(new UserAuthMiddleware().checkForUserExistence());
        spot.setCategoryId(categoryId);
        spot.setLocationId(locationId);
        spot.setSpotName(spotName);
        spot.setSpotDescription(spotDescription);
        spot.setStatus(spotStatus);

        return spot;
    }

    public Spot updateSpotViewForm() throws Exception {
        Scanner scanner = new Scanner(System.in);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader entered = new BufferedReader(isr);
        System.out.println("Enter spot id to update");
        Integer spotId = scanner.nextInt();
        System.out.println("Enter category id");
        int categoryId = scanner.nextInt();
        System.out.println("Enter your location Id ");
        Integer locationId = scanner.nextInt();
        System.out.println("Enter spot name ");
        String spotName = entered.readLine();
        System.out.println("Enter spot description ");
        String spotDescription = entered.readLine();
//        System.out.println("Enter spot status ");
        Integer spotStatus = 1;

        Spot spot = new Spot();
        spot.setSpotId(spotId);
        spot.setUserId(new UserAuthMiddleware().checkForUserExistence());
        spot.setCategoryId(categoryId);
        spot.setLocationId(locationId);
        spot.setSpotName(spotName);
        spot.setSpotDescription(spotDescription);
        spot.setStatus(spotStatus);

        return spot;
    }

    public Integer deleteSpotViewForm(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id of the spot to delete");
        Integer spotIdToDelete = scanner.nextInt();

        return spotIdToDelete;
    }
}