package com.spiralSpotManagement.SearchModule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
public class SearchByPopularity {
	
	public static void popuralityByRates(java.sql.Connection connection) throws SQLException{
		
		String Selectquery="select *from Spot_table order by rates limit 5";
		 PreparedStatement pt=connection.prepareStatement(Selectquery);
		 ResultSet results=pt.executeQuery();
	
		int id=0;
		 while(results.next()) {
			 id++;
			 String spotName=results.getString("spot_name");
			 System.out.print(id +" " + spotName+ "\n");
			 
		 }
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
 
		System.out.print("==== Search by popularity =====\n");
		 CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
		// cloudStorageConnection.checkDbWorking(cloudStorageConnection.getConnection());
		 popuralityByRates(cloudStorageConnection.getConnection());
		 
	}

}
