package com.spiralSpotManagement.SearchModule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

//Authors: by Blessing and Izere Kerie
//This class is used to fetch recent searched query from the search history table and then return top 10 most popular
//searches. Popular searches will be determined by searchquery with most ratings,views,and most searched query.
public class SearchByPopularity {

	public static ArrayList<String> popularityByRatesArray(java.sql.Connection connection) throws SQLException{
		ArrayList<String> spots=new ArrayList<String>();
		String SelectRatesquery="select *from Spot_table order by rates desc limit 5";
		String SelectViewsquery="select *from Spot_table order by views desc limit 5";
		String SelectMostSearchedQuery = "SELECT searched_query FROM searchHistory GROUP BY searched_query ORDER BY COUNT(searched_query) DESC LIMIT 5";
		PreparedStatement ptRates=connection.prepareStatement(SelectRatesquery);
		PreparedStatement ptViews=connection.prepareStatement(SelectViewsquery);
		PreparedStatement sq = connection.prepareStatement(SelectMostSearchedQuery);
		ResultSet Ratesresults=ptRates.executeQuery();
		ResultSet Viewsresults=ptViews.executeQuery();
		ResultSet searchResults = sq.executeQuery();

		while( Ratesresults.next()) {
			String spotName= Ratesresults.getString("spot_name");
			if(!spots.contains(spotName)) {
				spots.add(spotName);
			}

		}
		while(  Viewsresults.next() ) {
			String spotName= Viewsresults.getString("spot_name");


			if(!spots.contains(spotName)) {
				spots.add(spotName);
			}
		}

		while (searchResults.next()){

			String searchedSpot = searchResults.getString("searched_query");
			spots.add(searchedSpot);
		}

		return spots;

	}

	//Method used to display popular spots in the console
	public static void DisplayPopularSpots(ArrayList<String> popularSpots) {
		for(int i=0;i<popularSpots.size();i++) {
			System.out.println(i+1+"."+popularSpots.get(i));
		}
	}

	public static void popularityEntry() throws Exception {
		Scanner scanner=new Scanner(System.in);
		// TODO Auto-generated method stub
		ArrayList<String> popularSpots= new ArrayList<String>();
		System.out.print("==== Search by popularity =====\n");

		//connect to the db
		CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
		// cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
		popularityByRatesArray(cloudStorageConnection.getConnection());
		popularSpots.addAll(popularityByRatesArray(cloudStorageConnection.getConnection()));
		DisplayPopularSpots(popularSpots);
		int NO;
		System.out.print("choose on one");
		NO=scanner.nextInt();

		PreparedStatement ps=cloudStorageConnection.getConnection().prepareStatement("select spot_description from Spot_table where spot_name =?" );
		ps.setString(1,popularSpots.get(NO-1));
		ResultSet results=ps.executeQuery();
		while(results.next()) {
			String spotDescription=results.getString("spot_description");
			System.out.println("result :" +spotDescription );
		}


	}


	//Main file of the program
	public static void main(String[] args) throws Exception {
		popularityEntry();

	}

}
