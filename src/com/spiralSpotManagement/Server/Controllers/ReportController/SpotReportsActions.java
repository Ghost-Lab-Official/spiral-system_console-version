package com.spiralSpotManagement.Server.Controllers.ReportController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.SpotsReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class SpotReportsActions {

        /**
         * @author Ntezirizaza Erneste
         * description This method generates the number of highly visited spots
         * @throws Exception
         */

        public static void getTheTotalNumberOfHighlyVisitedSpots(String query) throws Exception {
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println( "\t\t\t  Number of highly visited spots :         "+ rs.getInt(1)+"");
            }
            connection.close();

        }



        /**
         * @author Ntezirizaza Erneste
         * @description This method generates the number of registered spots
         * @throws Exception
         */
        public static void getTheTotalNumberOfActiveSpots() throws Exception {
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status=1");

            while(rs.next()){
                System.out.println( "\t\t\t  Number of Active spots :         "+ rs.getInt(1)+"");
            }
            connection.close();

        }

        /**
         * @author Ntezirizaza Erneste
         * @description This method generates the number of inactive spots
         * @throws Exception
         */

        public static void getTheTotalNumberOfInactiveSpots() throws Exception {
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(spot_name) from Spot_table where status=0");

            while(rs.next()){
                System.out.println( "\t\t\t  Number of Inactive spots :         "+ rs.getInt(1)+"");
            }
            connection.close();
        }




        //     Manzi Mike
        public static void viewAllSpots() throws Exception {
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Spot_table.spot_id,users_table.user_name,spot_category.category_name,"+
                    "Spot_table.spot_name,locations.locationId,Spot_table.spot_description,Spot_table.views,Spot_table.status," +
                    "Spot_table.registration_date "+"FROM `Spot_table` LEFT JOIN users_table ON users_table.user_id = Spot_table.user_id " +
                    "LEFT JOIN spot_category ON "+"spot_category.category_id = Spot_table.category_id LEFT JOIN locations on " +
                    getlocations() + ".locationId = Spot_table.location_id"
            );
            ArrayList<SpotsReport> AllSpots = new ArrayList<SpotsReport>();
            while (rs.next()) {
                SpotsReport  mySpots = new SpotsReport(
                        rs.getString("spot_id"),
                        rs.getString("user_name"),
                        rs.getString("category_name"),
                        rs.getString("location_id"),
                        rs.getString("spot_name"),
                        rs.getString("spot_description"),
                        rs.getDouble("views"),
                        rs.getString("status"),
                        rs.getString("registration_date")
                );
                AllSpots.add(mySpots);
            }
            Iterator it = AllSpots.iterator();
            System.out.println("\t\t\t  #Id" + "\t\t\t Creator" +  "\t\t\t\t Category " +  "\t\t\t Location" +  "\t\t\t Spot Name " +
                    "\t\t\t\t Spot Description" +"\t\t\t\t Views" +  "\t\t\t\t Status" +  "\t\t\t RegistrationDate ");
            System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(it.hasNext()){
                SpotsReport spot = (SpotsReport)it.next();
                System.out.println(" \t\t\t\t "+spot.getSpot_id() + " \t\t\t\t " + spot.getUser_name()+ " \t\t\t\t " + spot.getCategory_name()+
                        " \t\t\t\t " + spot.getLocation_name()+ " \t\t\t\t " + spot.getSpot_name()+ " \t\t\t\t " +" \t\t\t\t " +spot.getSpot_description()
                        + " \t\t\t\t "+ spot.getViews()+" \t\t\t\t " + spot.getStatus()+ " \t\t\t\t " + spot.getRegistration_date());
            }
            connection.close();
        }

        private static String getlocations() {
            return "locations";
        }





        public static void viewAllInactiveSpots() throws Exception{
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmnt = connection.createStatement();
            String squery= "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                    " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                    " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                    "left join users_table on Spot_table.user_id=users_table.user_id" +
                    " left join locations on Spot_table.location_id = locations.locationId" +
                    " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                    "WHERE Spot_table.status =0";
            ResultSet result = stmnt.executeQuery(squery);
            Vector<SpotsReport> ActiveSpotsList = new Vector<SpotsReport>();
            while (result.next()) {
                SpotsReport  inactiveSpots = new SpotsReport(
                        result.getString("spot_id"),
                        result.getString("user_name"),
                        result.getString("category_name"),
                        result.getString("location_name"),
                        result.getString("spot_name"),
                        result.getString("spot_description"),
                        result.getDouble("views"),
                        result.getString("status"),
                        result.getString("registration_date")
                );
                ActiveSpotsList.add(inactiveSpots);
            }
            Iterator iterate = ActiveSpotsList.iterator();
            System.out.println("\t\t\t #Id" + "\t\t\t\t createdBy" +  "\t\t\t\t  Entitled " +  "\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
            System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(iterate.hasNext()){
                SpotsReport inactspot = (SpotsReport)iterate.next();
                System.out.println("\t\t\t " +inactspot.getSpot_id() + "\t\t\t\t " +inactspot.getUser_name() +  "\t\t\t\t " +inactspot.getSpot_description() +  "\t\t\t\t\t " +inactspot.getLocation_name() +  "\t\t\t\t\t " +inactspot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +inactspot.getStatus() +  "\t\t\t\t\t\t\t " +inactspot.getViews() +  "\t\t\t\t\t\t " + inactspot.getRegistration_date());
            }
            connection.close();
        }
        public static void ViewHighlyVisitedSpots() throws Exception{
            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection = cloudStorageConnection.getConnection();
            Statement stment = connection.createStatement();
            String querry = "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                    " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                    " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                    "left join users_table on Spot_table.user_id=users_table.user_id" +
                    " left join locations on Spot_table.location_id = locations.locationId" +
                    " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                    "WHERE Spot_table.status =1 AND Spot_table.views > 10";
            ResultSet resultset = stment.executeQuery(querry);
            Vector<SpotsReport> ActiveSpotsList = new Vector<SpotsReport>(2);
            while (resultset.next()) {
                SpotsReport  highlyViewed = new SpotsReport(
                        resultset.getString("spot_id"),
                        resultset.getString("user_name"),
                        resultset.getString("category_name"),
                        resultset.getString("location_name"),
                        resultset.getString("spot_name"),
                        resultset.getString("spot_description"),
                        resultset.getDouble("views"),
                        resultset.getString("status"),
                        resultset.getString("registration_date")
                );
                ActiveSpotsList.add(highlyViewed);
            }
            Iterator itrt = ActiveSpotsList.iterator();
            System.out.println("\t\t\t #Id" + "\t\t\t\t createdBy" +  "\t\t\t\t  Entitled " +  "\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
            System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(itrt.hasNext()){
                SpotsReport highlyViewed = (SpotsReport)itrt.next();
                System.out.println("\t\t\t " +highlyViewed.getSpot_id() + "\t\t\t\t " +highlyViewed.getUser_name() +  "\t\t\t\t " +highlyViewed.getSpot_description() +  "\t\t\t\t\t " +highlyViewed.getLocation_name() +  "\t\t\t\t\t " +highlyViewed.getCategory_name() +  "\t\t\t\t\t\t\t\t " +highlyViewed.getStatus() +  "\t\t\t\t\t\t\t " +highlyViewed.getViews() +  "\t\t\t\t\t\t " + highlyViewed.getRegistration_date());
            }
            connection.close();
        }



        public static void viewAllActiveSpots() throws Exception {

            CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
            Connection connection= cloudStorageConnection.getConnection();
            Statement stmt = connection.createStatement();
            String query= "SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                    " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                    " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                    "left join users_table on Spot_table.user_id=users_table.user_id" +
                    " left join locations on Spot_table.location_id = locations.locationId" +
                    " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                    "WHERE Spot_table.status =1";
            ResultSet result = stmt.executeQuery(query);


            ArrayList<SpotsReport> ActiveSpotsList = new ArrayList<SpotsReport>();


            while (result.next()) {
                SpotsReport  myActiveSpot = new SpotsReport(
                        result.getString("spot_id"),
                        result.getString("user_name"),
                        result.getString("category_name"),
                        result.getString("location_name"),
                        result.getString("spot_name"),
                        result.getString("spot_description"),
                        result.getDouble("views"),
                        result.getString("status"),
                        result.getString("registration_date")
                );
                ActiveSpotsList.add(myActiveSpot);
            }

            Iterator iterator = ActiveSpotsList.iterator();
            System.out.println("\t\t\t  #Id" + "\t\t\t\t createdBy" +  "\t\t\t\t  Entitled " +  "\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
            System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(iterator.hasNext()){
                SpotsReport spot = (SpotsReport)iterator.next();
                System.out.println(" \t\t\t\t "+spot.getSpot_id() + " \t\t\t\t " + spot.getUser_name()+ " \t\t\t\t " + spot.getSpot_name()+ " \t\t\t\t " + spot.getLocation_name()+ " \t\t\t\t " + spot.getCategory_name()+ " \t\t\t\t " + spot.getStatus()
                        + " \t\t\t\t " + spot.getViews()+ " \t\t\t\t " + spot.getRegistration_date());
            }
            connection.close();
        }

        public static void viewThisMonthsSpots()throws Exception{
            try {
                CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
                Connection connection= cloudStorageConnection.getConnection();
                Statement statement = connection.createStatement();
//            LocalDate currentDate = LocalDate.now();
//            Month currentMonth = currentDate.getMonth();
                String query="SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                        " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                        " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                        "left join users_table on Spot_table.user_id=users_table.user_id" +
                        " left join locations on Spot_table.location_id = locations.locationId" +
                        " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                        "where MONTH(Spot_table.registration_date) =EXTRACT(MONTH From CURRENT_TIMESTAMP)";
                ResultSet result = statement.executeQuery(query);
                ArrayList<SpotsReport> thisMonthsSpots = new ArrayList<SpotsReport>();
                while (result.next()) {
                    SpotsReport  currentMonthsSpots = new SpotsReport(
                            result.getString("spot_id"),
                            result.getString("user_name"),
                            result.getString("category_name"),
                            result.getString("location_name"),
                            result.getString("spot_name"),
                            result.getString("spot_description"),
                            result.getDouble("views"),
                            result.getString("status"),
                            result.getString("registration_date")
                    );
                    thisMonthsSpots.add(currentMonthsSpots);



                }
                Iterator iterator = thisMonthsSpots.iterator();
                System.out.println("\t\t\t #Id" + "\t\t\t\t createdBy" +  "\t\t\t\t  Entitled " +  "\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
                System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while(iterator.hasNext()){
                    SpotsReport spot = (SpotsReport)iterator.next();
                    System.out.println("\t\t\t " +spot.getSpot_id() + "\t\t\t\t " +spot.getUser_name() +  "\t\t\t\t " +spot.getSpot_description() +  "\t\t\t\t\t " +spot.getLocation_name() +  "\t\t\t\t\t " +spot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +spot.getStatus() +  "\t\t\t\t\t\t\t " +spot.getViews() +  "\t\t\t\t\t\t " + spot.getRegistration_date());
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        public static void viewThisMonthsTrendingSpots() throws Exception{
            try {
                CloudStorageConnectionHandler cloudStorageConnection = new CloudStorageConnectionHandler();
                Connection connection= cloudStorageConnection.getConnection();
                Statement statement = connection.createStatement();
//            LocalDate currentDate = LocalDate.now();
//            Month currentMonth = currentDate.getMonth();
                String query="SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                        " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                        " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                        "left join users_table on Spot_table.user_id=users_table.user_id" +
                        " left join locations on Spot_table.location_id = locations.locationId" +
                        " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                        "where MONTH(Spot_table.registration_date) =EXTRACT(MONTH From CURRENT_TIMESTAMP) and YEAR(Spot_table.registration_date) = EXTRACT(YEAR from CURRENT_TIMESTAMP) and Spot_table.status=1 and views>20 limit 1";
                ResultSet result = statement.executeQuery(query);
                ArrayList<SpotsReport> thisMonthsTrendingSpots = new ArrayList<SpotsReport>();
                while (result.next()) {
                    SpotsReport  currentMonthsTrendingSpots = new SpotsReport(
                            result.getString("spot_id"),
                            result.getString("user_name"),
                            result.getString("category_name"),
                            result.getString("location_name"),
                            result.getString("spot_name"),
                            result.getString("spot_description"),
                            result.getDouble("views"),
                            result.getString("status"),
                            result.getString("registration_date")
                    );
                    thisMonthsTrendingSpots.add(currentMonthsTrendingSpots);

                }
                boolean isEmpty = thisMonthsTrendingSpots.isEmpty();
                if(isEmpty !=true){
                    Iterator iterator = thisMonthsTrendingSpots.iterator();
                    System.out.println("\t\t\t #Id" + "\t\t\t\t createdBy" +  "\t\t\t\t  Entitled " +  "\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
                    System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    while(iterator.hasNext()){
                        SpotsReport spot = (SpotsReport)iterator.next();
                        System.out.println("\t\t\t " +spot.getSpot_id() + "\t\t\t\t " +spot.getUser_name() +  "\t\t\t\t " +spot.getSpot_description() +  "\t\t\t\t\t " +spot.getLocation_name() +  "\t\t\t\t\t " +spot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +spot.getStatus() +  "\t\t\t\t\t\t\t " +spot.getViews() +  "\t\t\t\t\t\t " + spot.getRegistration_date());
                    }

                }
                else{
                    System.out.println("NO spots found!!!");
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public static void viewTodaysSpots()throws Exception{

            try {
                CloudStorageConnectionHandler cloudStorageConnection=new CloudStorageConnectionHandler();
                Connection connection=cloudStorageConnection.getConnection();

                Statement statement=connection.createStatement();
                LocalDate currentDate = LocalDate.now();
//            System.out.println(currentDate);
                String query="SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                        " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                        " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                        "left join users_table on Spot_table.user_id=users_table.user_id" +
                        " left join locations on Spot_table.location_id = locations.locationId" +
                        " left join spot_category on Spot_table.category_id = spot_category.category_id where Spot_table.registration_date = "+"'"+currentDate+"'";
//            System.out.println(query);


                ResultSet result=statement.executeQuery(query);
                ArrayList<SpotsReport> TodaysNewSpots = new ArrayList<SpotsReport>();

                while (result.next()){
                    SpotsReport thisdaysSpots =new SpotsReport(
                            result.getString("spot_id"),
                            result.getString("user_name"),
                            result.getString("category_name"),
                            result.getString("location_name"),
                            result.getString("spot_name"),
                            result.getString("spot_description"),
                            result.getDouble("views"),
                            result.getString("status"),
                            result.getString("registration_date")
                    );
                    TodaysNewSpots.add(thisdaysSpots);
                }
                boolean isEmpty = TodaysNewSpots.isEmpty();
                if(isEmpty !=true){
                    Iterator iterator = TodaysNewSpots.iterator();
                    System.out.println("\t\t\t #Id" + "\t\t\t createdBy" +  "\t\t\t  Entitled " +  "\t\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
                    System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    while(iterator.hasNext()){
                        SpotsReport spot = (SpotsReport)iterator.next();
                        System.out.println("\t\t\t " +spot.getSpot_id() + "\t\t\t\t " +spot.getUser_name() +  "\t\t\t\t " +spot.getSpot_description() +  "\t\t\t\t\t " +spot.getLocation_name() +  "\t\t\t\t\t " +spot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +spot.getStatus() +  "\t\t\t\t\t\t\t " +spot.getViews() +  "\t\t\t\t\t\t " + spot.getRegistration_date());
                    }
                }
                else{
                    System.out.println("NO spots found!!!");
                }

                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        public static void  viewTodaysTrendingSpots()throws Exception{

            try {
                CloudStorageConnectionHandler cloudStorageConnection=new CloudStorageConnectionHandler();
                Connection connection=cloudStorageConnection.getConnection();

                Statement statement=connection.createStatement();
                LocalDate currentDate = LocalDate.now();
//            System.out.println(currentDate);
                String query="SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                        " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                        " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                        "left join users_table on Spot_table.user_id=users_table.user_id" +
                        " left join locations on Spot_table.location_id = locations.locationId" +
                        " left join spot_category on Spot_table.category_id = spot_category.category_id where Spot_table.registration_date = "+"'"+currentDate+"' and Spot_table.status=1 and Spot_table.views >20 limit 1";
//            System.out.println(query);


                ResultSet result=statement.executeQuery(query);
                ArrayList<SpotsReport> TodaysNewSpots = new ArrayList<SpotsReport>();

                while (result.next()){
                    SpotsReport thisdaysSpots =new SpotsReport(
                            result.getString("spot_id"),
                            result.getString("user_name"),
                            result.getString("category_name"),
                            result.getString("location_name"),
                            result.getString("spot_name"),
                            result.getString("spot_description"),
                            result.getDouble("views"),
                            result.getString("status"),
                            result.getString("registration_date")
                    );
                    TodaysNewSpots.add(thisdaysSpots);
                }
                Iterator iterator = TodaysNewSpots.iterator();
                System.out.println("\t\t\t #Id" + "\t\t\t createdBy" +  "\t\t\t  Entitled " +  "\t\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
                System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while(iterator.hasNext()){
                    SpotsReport spot = (SpotsReport)iterator.next();
                    System.out.println("\t\t\t " +spot.getSpot_id() + "\t\t\t\t " +spot.getUser_name() +  "\t\t\t\t " +spot.getSpot_description() +  "\t\t\t\t\t " +spot.getLocation_name() +  "\t\t\t\t\t " +spot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +spot.getStatus() +  "\t\t\t\t\t\t\t " +spot.getViews() +  "\t\t\t\t\t\t " + spot.getRegistration_date());
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        public static void getReportForAnotherDay(LocalDate date)throws Exception{
            try{
                CloudStorageConnectionHandler cloudStorageConnection=new CloudStorageConnectionHandler();
                Connection connection=cloudStorageConnection.getConnection();

                Statement statement=connection.createStatement();
                String query="SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                        " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                        " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                        "left join users_table on Spot_table.user_id=users_table.user_id" +
                        " left join locations on Spot_table.location_id = locations.locationId" +
                        " left join spot_category on Spot_table.category_id = spot_category.category_id where Spot_table.registration_date = "+"'"+date+"'";


                ResultSet resultSet=statement.executeQuery(query);
                ArrayList<SpotsReport> reportForAnotherDay=new ArrayList<SpotsReport>();

                while (resultSet.next()){
                    SpotsReport spots= new SpotsReport(
                            resultSet.getString("spot_id"),
                            resultSet.getString("user_name"),
                            resultSet.getString("category_name"),
                            resultSet.getString("location_name"),
                            resultSet.getString("spot_name"),
                            resultSet.getString("spot_description"),
                            resultSet.getDouble("views"),
                            resultSet.getString("status"),
                            resultSet.getString("registration_date")                    );

                    reportForAnotherDay.add(spots);
                }
                Iterator iterator = reportForAnotherDay.iterator();
                System.out.println("\t\t\t #Id" + "\t\t\t createdBy" +  "\t\t\t  Entitled " +  "\t\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
                System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while(iterator.hasNext()){
                    SpotsReport spot = (SpotsReport)iterator.next();
                    System.out.println("\t\t\t " +spot.getSpot_id() + "\t\t\t\t " +spot.getUser_name() +  "\t\t\t\t " +spot.getSpot_description() +  "\t\t\t\t\t " +spot.getLocation_name() +  "\t\t\t\t\t " +spot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +spot.getStatus() +  "\t\t\t\t\t\t\t " +spot.getViews() +  "\t\t\t\t\t\t " + spot.getRegistration_date());
                }
                connection.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }


        public static void getReportForAnotherMonth(int month, int year)throws Exception{
            try{
                CloudStorageConnectionHandler cloudStorageConnection=new CloudStorageConnectionHandler();
                Connection connection=cloudStorageConnection.getConnection();

                Statement statement=connection.createStatement();
                String query="SELECT Spot_table.spot_id , Spot_table.spot_name , Spot_table.spot_description ," +
                        " Spot_table.status , Spot_table.views  , Spot_table.registration_date ," +
                        " users_table.user_name , locations.location_name , spot_category.category_name from Spot_table " +
                        "left join users_table on Spot_table.user_id=users_table.user_id" +
                        " left join locations on Spot_table.location_id = locations.locationId" +
                        " left join spot_category on Spot_table.category_id = spot_category.category_id " +
                        "where MONTH(Spot_table.registration_date)= " + month + " and YEAR(Spot_table.registration_date) =" +year;
//            System.out.println(query);


                ResultSet resultSet=statement.executeQuery(query);
                ArrayList<SpotsReport> reportForAnotherDay=new ArrayList<SpotsReport>();

                while (resultSet.next()){
                    SpotsReport spots= new SpotsReport(
                            resultSet.getString("spot_id"),
                            resultSet.getString("user_name"),
                            resultSet.getString("category_name"),
                            resultSet.getString("location_name"),
                            resultSet.getString("spot_name"),
                            resultSet.getString("spot_description"),
                            resultSet.getDouble("views"),
                            resultSet.getString("status"),
                            resultSet.getString("registration_date")
                    );

                    reportForAnotherDay.add(spots);
                }
                Iterator iterator = reportForAnotherDay.iterator();
                System.out.println("\t\t\t #Id" + "\t\t\t createdBy" +  "\t\t\t  Entitled " +  "\t\t\t\t\t\t location" +  "\t\t\t\t\t category " +  "\t\t\t\t\t\t status " +  "\t\t\t\t\t views"+  "\t\t\t\t\t  registrationDate ");
                System.out.println("\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while(iterator.hasNext()){
                    SpotsReport spot = (SpotsReport)iterator.next();
                    System.out.println("\t\t\t " +spot.getSpot_id() + "\t\t\t\t " +spot.getUser_name() +  "\t\t\t\t " +spot.getSpot_description() +  "\t\t\t\t\t " +spot.getLocation_name() +  "\t\t\t\t\t " +spot.getCategory_name() +  "\t\t\t\t\t\t\t\t " +spot.getStatus() +  "\t\t\t\t\t\t\t " +spot.getViews() +  "\t\t\t\t\t\t " + spot.getRegistration_date());
                }
                connection.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
}

