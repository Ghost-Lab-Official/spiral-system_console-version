package com.spiralSpotManagement.UsersModule;

import com.spiralSpotManagement.DbConnection.CloudStorageConnection;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class Login {
    private String secretKey = "qbeyS2bwIwlxJa5KBTSFkvyYgHc7E5gtzbrMToWUSzw=";
    public boolean loginUser(Connection connection) throws Exception{
        boolean checkUser = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email ");
        String email = scanner.nextLine();
        System.out.println("Enter your password ");
        String password = scanner.nextLine();
        String sql = "SELECT * FROM users_table WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            checkUser = true;
            generateJwtToken();
        }
        else{
            throw new Exception("invalid credentials");
        }
        System.out.println(checkUser);
        return checkUser;
    }
    private String generateJwtToken(){
        byte[] secret = Base64.getDecoder().decode(this.secretKey);
        Instant now = Instant.now();

        String token = Jwts.builder()
                .setSubject("Mutoni Denyse")
                .claim("name","denyse")
                .claim("name","denyse")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        System.out.println(token);
        return token;
    }
    public static void main(String[] args) throws Exception{
        Login login = new Login();

        login.loginUser(new CloudStorageConnection().getConnection());
    }
}
