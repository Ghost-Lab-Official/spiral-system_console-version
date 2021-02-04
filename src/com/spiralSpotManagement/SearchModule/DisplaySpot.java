package com.spiralSpotManagement.SearchModule;
import java.io.IOException;
import java.util.Scanner;

public class DisplaySpot {
    public static Scanner scanner = new Scanner(System.in);
    private  static  String toContinue;
    private static int special;
    private static int filtered;

    public static int levelOne() {
        System.out.println("|---------------SPOTS SEARCHING----------------|");
        System.out.println("|-----Kigali Convention Center ~Text Search----|\n");
        System.out.println("|1.Kigali Convention Center located in Kigali|");
        System.out.println("|2.Kigali Convention Center Visit Rwanda|");
        System.out.println("|3.Kigali Convention Center kitchen|");
        System.out.println("|4.Radisson Blu Hotel|\n");
        System.out.println("Enter your specific research");
        special = scanner.nextInt();
        return special;

    }

    public static int filter() {
        System.out.println("The filtered result is:\n");
        System.out.println("Enter the query of your choice");
        filtered = scanner.nextInt();
            if (special != filtered) {
                System.out.println("Invalid input");
            } else if (special == filtered) {
                levelTwo();
            }

        return filtered;
    }

    public static void levelTwo() {
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|---------------    SPOTS SEARCHING       --------------|");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("\t\t Name:Radisson Blu Hotel");
            System.out.println("\t\t Views:102");
            System.out.println("\t\t Location:Kigali,Rwanda");
            System.out.println("\t\t Description:It is what it is niki niki!");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|-------------------------------------------------------|");
            //dealWithDashboard();

    }

    public static void main(String[] args) throws IOException {
    do{

    levelOne();
    filter();
        System.out.println("Byarangiye birongera biratangira");
        //userChoice = levelOne();
        System.out.println(" Do you want to continue Y/N ? ");
        toContinue = scanner.next();
        //  levelOne();
    }
    while (toContinue.equals("Y") || toContinue.equals("y"));

        }

    }