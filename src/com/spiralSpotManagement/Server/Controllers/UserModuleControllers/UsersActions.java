package com.spiralSpotManagement.Server.Controllers.UserModuleControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.TokenIssued;
import com.spiralSpotManagement.Server.Model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class UsersActions {
    String createUserQuery = "INSERT INTO users_table(first_name,last_name,user_name,email,gender,birth_date,password,user_category,location,user_status) values (?,?,?,?,?,?,?,?,?,?)";
    String loginUserQuery = "SELECT * FROM users_table WHERE email = ?";
    String secreteKey = "SpiralSystemPrivateKeyForAuthentication";

    public static boolean checkIfUserExist(Connection connection,String email) throws Exception {
        String sql = "SELECT * FROM users_table WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,email);
        ResultSet rs = preparedStatement.executeQuery();
        boolean checkUser = false;
        if (rs.next()){
            checkUser = true;
        }
        return checkUser;
    }

    private String hashPassword(String password){
        System.out.println(BCrypt.gensalt(12));
        return BCrypt.hashpw(password,BCrypt.gensalt(12));

    }

    public ResponseStatus registerUserInDb(User userToRegister)throws Exception{
        ResponseStatus responseStatus = new ResponseStatus(200,"OK","CREATED THE USER");
        Connection connection = new CloudStorageConnectionHandler().getConnection();

        if(checkIfUserExist(connection,userToRegister.getEmail())){
            return new ResponseStatus(200,"USER FOUND","User email is found, try another ");
        }

        String securePassword = hashPassword(userToRegister.getPassword());


        try{
            PreparedStatement preparedStatement = connection.prepareStatement(createUserQuery);
            preparedStatement.setString(1,userToRegister.getFirstName());
            preparedStatement.setString(2,userToRegister.getLastName());
            preparedStatement.setString(3,userToRegister.getUserName());
            preparedStatement.setString(4,userToRegister.getEmail());
            preparedStatement.setString(5,userToRegister.getGender());
            preparedStatement.setString(6,userToRegister.getBirthDate());
            preparedStatement.setString(7,securePassword);
            preparedStatement.setInt(8,1);
            preparedStatement.setString(9,userToRegister.getLocation());
            preparedStatement.setString(10,"inactive");

            int inserted = preparedStatement.executeUpdate();
            if(inserted == 1){
                return new ResponseStatus(200,"CREATED","User registered , Go Login");
            }
            else{
                return new ResponseStatus(500,"SERVER ERROR","Insertion failed, try or contact System Administrator");
            }
        }
        catch (Exception e){
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
    }

    public ResponseStatus loginUser(User userToLogin)throws Exception{
        boolean checkUser = false;
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(loginUserQuery);
        preparedStatement.setString(1,userToLogin.getEmail());
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
            System.out.println(rs.getString("email"));
            System.out.println(checkIfPasswordsAreEqual(userToLogin.getPassword(),rs.getString("password")));
            if(checkIfPasswordsAreEqual(userToLogin.getPassword(),rs.getString("password"))){
                checkUser = true;
                TreeMap<String,String> newPayload = new TreeMap<String,String>();
                newPayload.put("email",rs.getString("email"));
                newPayload.put("user_name",rs.getString("user_name"));
                newPayload.put("user_category",rs.getString("user_Category"));
                Token loginCredentials = new Token(rs.getString("email"),newPayload);
                String userToken = loginCredentials.generateJwtToken(1, ChronoUnit.DAYS);

                return new ResponseStatus(200,"LOGGED IN",(Object) new TokenIssued(userToken),"You are logged in ");
            };
        }
        else{
            return new ResponseStatus(404,"LOGGED FAILED","Email or password is incorrect");
        }

//
//        if (rs.next()){
//            checkUser = true;
//        }
//        else{
//            return new ResponseStatus(404,"LOGGED FAILED","Email or password is incorrect\n"+token);
//        }
//
//        return new ResponseStatus(200,"LOGGED IN","You are logged in ");

        return null;
    }



    public boolean checkIfPasswordsAreEqual(String password, String hash){
        boolean rightPassword = false;
        try{
            rightPassword =  BCrypt.checkpw(password,hash);

        }catch (IllegalArgumentException e){
            System.out.println("Wrong credentials");
        }
        return rightPassword;
    }
}
