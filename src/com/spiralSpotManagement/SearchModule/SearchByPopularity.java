package com.spiralSpotManagement.SearchModule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

public class SearchByPopularity {

	public static ArrayList<String> popularityByRatesArray(java.sql.Connection connection) throws SQLException{
		ArrayList<String> spots=new ArrayList<String>();
		String SelectRatesquery="select * from Spot_table order by rates desc limit 5";
		PreparedStatement pt=connection.prepareStatement(SelectRatesquery);
		ResultSet results=pt.executeQuery();
		String SelectMostSearchedQuery = "SELECT searched_query FROM searchHistory GROUP BY searched_query ORDER BY COUNT(searched_query) DESC LIMIT 5";
		PreparedStatement sq = connection.prepareStatement(SelectMostSearchedQuery);

		ResultSet searcResults = sq.executeQuery();


		while(results.next()) {

			String spotName = results.getString("spot_name");
			spots.add(spotName);
		}

		while (searcResults.next()){

			String searchedSpot = searcResults.getString("searched_query");
			spots.add(searchedSpot);
		}

		return spots;
	}

	public static void DisplayPopularSpots(ArrayList<String> popularSpots) {
		for(int i=0;i<popularSpots.size();i++) {
			System.out.println(i+1+"."+popularSpots.get(i));
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scanner=new Scanner(System.in);
		// TODO Auto-generated method stub
		ArrayList<String> popularSpots= new ArrayList<String>();
		System.out.print("==== Search by popularity =====\n");

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

}
