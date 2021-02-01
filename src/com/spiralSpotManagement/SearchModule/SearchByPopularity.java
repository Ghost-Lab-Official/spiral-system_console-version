package com.spiralSpotManagement.SearchModule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.mysql.jdbc.Connection;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

public class SearchByPopularity {

	public static ArrayList<String> popuralityByRatesArray(java.sql.Connection connection) throws SQLException{
		ArrayList<String> spots=new ArrayList<String>();
		String SelectRatesquery="select *from Spot_table order by rates desc limit 5";
		 PreparedStatement pt=connection.prepareStatement(SelectRatesquery);
		 ResultSet results=pt.executeQuery();
	

		 while(results.next()) {
			
			 String spotName=results.getString("spot_name");
			 spots.add(spotName);		 
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
		 popuralityByRatesArray(cloudStorageConnection.getConnection());
		 popularSpots.addAll(popuralityByRatesArray(cloudStorageConnection.getConnection()));
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
