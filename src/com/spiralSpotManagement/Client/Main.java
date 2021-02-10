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

//    public void MainMenu(){
//
//    }


        /*
            WORKING ON USER REGISTRATION
        */
    }
    public static void main(String[] args) throws Exception{
        new SpiralMultiThreadedServer().startServer();
//        registerUser();
//        new UserView().registerUser();
//        new UserView().loginUser();
//       spotCategoryModuleMain();
//        spotMainContent();
//        new CommentView().makeComment();
//        new CommentView().updateTheComment();
//        new CommentView().makeReplyComment();
//        new CommentView().updateCommentStatus();
//        new CommentReactionView().insertCommentReaction();
//        loginUser();
//        new LocationLevelsView().registerLocationLevels();
//          new LocationView().registerLocation();
//        new UserCategoryView().mainMethod();
        new SearchView().mainMethod();

        
    }




    public static void spotCategoryModuleMain()throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t=                SPOT CATEGORY DASHBOARD          = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  create spot category                                ||");
        System.out.println("\t\t\t|| 2.  update spot category                     ||");
        System.out.println("\t\t\t|| 3.  select all categories                    ||");
        System.out.println("\t\t\t|| 4.  change spot status                            ||");
        System.out.println("\t\t\t============================================ ");
        System.out.println("\t\t\tEnter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 :
                new SpotCategoryView().CreateCategory();
                break;
            case 2:
                new SpotCategoryView().UpdateCategory();
                break;
            case 3 :
                new SpotCategoryView().GetSpotCategory();
                break;
            case 4 :
                new SpotCategoryView().ChangeSpotStatus();
                break;
            default :
                System.out.println("Invalid choice");
        }
    }


    public static void spotMainContent()throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("||\t\tSpot  Section\t\t||");
        System.out.println("==============================\n");
        System.out.println("||\t\t1.Create Spot\t\t||\n");
        System.out.println("||\t\t2.Edit Spot\t\t\t||\n");
        System.out.println("||\t\t3.Delete Spot\t\t||\n");
        System.out.println("||\t\t4.Exit         \t\t||\n");
        System.out.println("==============================");
        String choose = input.nextLine();
=======
    public static void main(String[] args) throws Exception {
        RequestBody requestBody = new RequestBody();
        UserView userForms = new UserView();
        SpotView spotForms = new SpotView();
        SpotCategoryView spotCategories= new SpotCategoryView();
        SearchView searchForms = new SearchView();
        String toContinue;
        do {
            int choice;
            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      WELCOME TO SPIRAL        ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.LOGIN                    ------------------||");
            System.out.println("\t\t\t||------------------    2.REGISTER                 ------------------||");
            System.out.println("\t\t\t||------------------    3.SPOT INFO                ------------------||");
            System.out.println("\t\t\t||------------------    4.SPOT CATEGORY INFO       ------------------||");
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
                    spotForms.spotViewMenu();
                    break;
                case 4:
                    spotCategories.SpotCategoryMenu();
                    break;
                case 5:
                    searchForms.mainMethod();
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
>>>>>>> f7ef698bb72af71567e23c67b00d1182f3f1078f


    }

}