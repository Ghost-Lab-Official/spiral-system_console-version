package com.spiralSpotManagement.SearchModule;
import java.util.Scanner;

public class DisplaySpot {

    public static int filter(){
        System.out.println("The filtered result is:\n");
        return 0;
    }

    public static int levelOne() {
        filter();
        Scanner scanner=new Scanner(System.in);
        System.out.println("|---------------SPOTS SEARCHING----------------|");
        System.out.println("|-----Kigali Convention Center ~Text Search----|\n");
        System.out.println("|1.Kigali Convention Center located in Kigali|");
        System.out.println("|2.Kigali Convention Center Visit Rwanda|");
        System.out.println("|3.Kigali Convention Center kitchen|");
        System.out.println("|4.Radisson Blu Hotel|\n");
        System.out.println("Enter your specific research");
        int special=scanner.nextInt();
        scanner.close();
        return special;
    }

        public static void levelTwo() {
            filter();
            Scanner scanner=new Scanner(System.in);
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|---------------    SPOTS SEARCHING       --------------|");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|Name:----------   Radisson Blu Hotel     --------------|");
            System.out.println("|Views:---------          102             --------------|");
            System.out.println("|Location:------      Kigali,Rwanda       --------------|");
            System.out.println("|Description:--- It is what it is niki niki! -----------|");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|-------------------------------------------------------|");
        }

        public static void main(String[] args){
            int nextLevel=levelOne();
            if(nextLevel==4){
             levelTwo();
            }

        }
}
