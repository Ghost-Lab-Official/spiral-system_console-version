package com.spiralSpotManagement.UsersModule;

<<<<<<< HEAD:src/com/spiralSpotManagement/UsersModule/UsersModule.java
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

<<<<<<< HEAD
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
=======
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

// ===================================================================================================
// ===================notification function by muhodari
// happy of notication function now it is working
 public void sendNotification(String from, String password, String to, String sub, String msg) {
        Properties props = new Properties();
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.socketFactory.port", "465");
     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
//

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);


            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}


    }




>>>>>>> 312a634390dc4a1a0d6ec1e9ab16057e256b8a1a

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;

import jdk.internal.net.http.websocket.Transport;

<<<<<<< HEAD
public class UsersModule {
<<<<<<< HEAD
=======
   CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();

   private String user_id;
   private String first_name;
   private String last_name;
   private String user_email;
   private String email;
   private String gender;
   private String birth_date;
   private String password;
   private String user_category;
   private String location;

   // view users list function done by muhodari
   public void getUsersList(Connection connection) throws Exception {
       Statement stmt = connection.createStatement();
       ResultSet rs = stmt.executeQuery("select  * from users_table");
       System.out.println(" -------------------------- USERS TABLE------------------------- \n\n");
       System.out.println(" user_id\t\tfirst_name\t\tlast_Name\t\tEmail\t\t\t\t\tGender\t\tLocation");
       System.out.println(
               " --------------------------------------------------------------------------------------------------------------------------------- ");

       while (rs.next()) {
           int id = rs.getInt("user_id");
           String firstName = rs.getString("first_name");
           String lastName = rs.getString("last_name");
           String email = rs.getString("email");
           String gender = rs.getString("gender");
           String location = rs.getString("location");
           System.out.println(id + "\t\t\t\t" + firstName + "\t\t" + lastName + "\t\t\t" + email + "\t\t" + gender
                   + "\t\t" + location);
           System.out.println(
                   " ------------------------------------------------------------------------------------------------------------------------------------ ");
       }
   }

   // ===================================================================================================
   // ===================notification function by muhodari

   public void sendNotification(String from, String password, String to, String sub, String msg) {
       Properties props = new Properties();
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.port", "465");
       // get Session
       Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(from, password);
           }
       });
       // System.out.println(session);

       // compose message
       try {
           MimeMessage message = new MimeMessage(session);
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
           message.setSubject(sub);
           message.setText(msg);

           // send message
           Transport.send(message);
           System.out.println("message sent successfully");
       } catch (MessagingException e) {
           throw new RuntimeException(e);
       }

   }

   public void registerUser() {

   }
>>>>>>> 4b4cadec39b63f6aa37050524cb01f44a36c8a8c

=======
>>>>>>> 312a634390dc4a1a0d6ec1e9ab16057e256b8a1a
=======
public class UserController {
>>>>>>> 4b3c768af2d41de7662bf40cfbe6aec311d53638:src/com/spiralSpotManagement/UsersModule/UserController.java
}
