package com.spiralSpotManagement.UsersModule;
import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersModule {
    CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();





//view users list function  done by muhodari
    public void getUsersList(Connection connection)throws Exception{
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select  * from users_table");
        System.out.println(" -------------------------- USERS TABLE------------------------- \n\n");
        System.out.println(" user_id\t\tfirst_name\t\tlast_Name\t\tEmail\t\t\t\t\tGender\t\tLocation");
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------- ");

        while (rs.next()) {
            int id = rs.getInt("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email=rs.getString("email");
            String gender=rs.getString("gender");
            String location=rs.getString("location");
            System.out.println(id+"\t\t\t\t"+firstName+"\t\t"+lastName+"\t\t\t"+email+"\t\t"+gender+"\t\t"+location);
            System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------ ");
        }
    }






}
