package com.spiralSpotManagement.ReportModule;

import java.util.Scanner;

public class ReportModule {
    public static void reportDashboard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t=                ADMIN DASHBOARD            = ");
        System.out.println("\t\t\t============================================= ");
        System.out.println("\t\t\t|| 1.  Home                                ||");
        System.out.println("\t\t\t|| 2.  Spot Management                     ||");
        System.out.println("\t\t\t|| 3.  Users Management                    ||");
        System.out.println("\t\t\t|| 4.  Settings                            ||");
        System.out.println("\t\t\t============================================ ");
        String choice = scanner.nextLine();
    }
}
