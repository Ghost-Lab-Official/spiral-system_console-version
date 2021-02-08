package com.spiralSpotManagement.Client;
import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.View.*;
import com.spiralSpotManagement.Client.View.LocationLevelsView;
import com.spiralSpotManagement.Client.View.LocationView;
import com.spiralSpotManagement.Client.View.SpotView;
import com.spiralSpotManagement.Client.View.UserView;
import com.spiralSpotManagement.Client.View.SpotCategoryView;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.*;
import com.spiralSpotManagement.Server.ServerMain.SpiralMultiThreadedServer;
import java.util.List;
import java.util.Scanner;
/*
            @author : Anne Bethiane
            This is the entry of Spiral;
            WELCOME!
 */
public class Main {
    public static void main(String[] args) throws Exception {
        RequestBody requestBody = new RequestBody();
        UserView userForms = new UserView();
        SpotView spotForms = new SpotView();
        SpotCategoryView spotCategories= new SpotCategoryView();
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t||------------------      WELCOME TO SPIRAL        ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t||------------------    1.LOGIN                    ------------------||");
        System.out.println("\t\t\t||------------------    2.REGISTER                 ------------------||");
        System.out.println("\t\t\t||------------------    3.CREATE A SPOT            ------------------||");
        System.out.println("\t\t\t||------------------    4.CREATE A SPOT CATEGORY   ------------------||");
        System.out.println("\t\t\t||------------------    5.SEARCH                   ------------------||");
        System.out.println("\t\t\t||-------------------------------------------------------------------||");
        System.out.println("\t\t\t\t  Enter your choice                                              ");
        choice = scanner.nextInt(); System.out.println("");
        switch (choice){
            case 1 :
                userForms.loginUser();
                break;
            case 2:
                userForms.registerUser();
                break;
            case 3:
                spotForms.createSpot();
                break;
            case 4:
                spotCategories.CreateCategory();
                //case 5:
                //searchForms.searchSpot();
            default:
                System.out.println("Invalid input");
        }
    }
}