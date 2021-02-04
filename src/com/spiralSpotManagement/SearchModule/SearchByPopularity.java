package com.spiralSpotManagement.SearchModule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
/**
 *@author kerie and blessing
 *@description  class that will help in search by popularity

 **/
public class SearchByPopularity {
	/**
	 *@description   method to  make array of all popular stops to be displayed
	 * @param connection
	 *
	 * @return Array of Popular spots
	 * @throws SQLException
	 */
	public static ArrayList<String> popularityArray(java.sql.Connection connection) throws SQLException{
		ArrayList<String> spots=new ArrayList<String>();


		String SelectRatesquery="select *from Spot_table order by rates desc limit 5";
	  String SelectViewsquery="select *from Spot_table order by views desc limit 5";

		/**
		 * @description  query to  to fetch popular spots by rates they have  into array of pupular spots to be displayed to user
		 * @auther izere uwonkunda kerie
		 */
		PreparedStatement ptRates=connection.prepareStatement(SelectRatesquery);
		 ResultSet Ratesresults=ptRates.executeQuery();
		 while( Ratesresults.next()) {
			 String spotName= Ratesresults.getString("spot_name");
			if(!spots.contains(spotName)) {
				 spots.add(spotName);
			}
		 }


		/**
		 * @description  query to  to fetch popular spots by views they have  into array of popular spots to be displayed to user
		 * if there is duplicate(if spots has high rates and high vies ata same time we keep it once) we don't add it to our array
		 * @auther izere uwonkunda kerie
		 */
		PreparedStatement ptViews=connection.prepareStatement(SelectViewsquery);
		ResultSet Viewsresults=ptViews.executeQuery();
		 while(  Viewsresults.next() ) {
			 String spotName= Viewsresults.getString("spot_name");

			 
			 if(!spots.contains(spotName)) {
				 spots.add(spotName);
			}	 
		 }


		String SelectMostSearchedQuery = "SELECT searched_query FROM searchHistory GROUP BY searched_query ORDER BY COUNT(searched_query) DESC LIMIT 5";
		PreparedStatement sq = connection.prepareStatement(SelectMostSearchedQuery);

		ResultSet searcResults = sq.executeQuery();

		while (searcResults.next()){

			String searchedSpot = searcResults.getString("searched_query");

			 if(!spots.contains(searchedSpot)) {
				 spots.add(searchedSpot);
			}	
			
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
		popularityArray(cloudStorageConnection.getConnection());
		popularSpots.addAll(popularityArray(cloudStorageConnection.getConnection()));
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
