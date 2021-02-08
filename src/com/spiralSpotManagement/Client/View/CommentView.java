package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

/**
 * Comment.java This is a class for handling Spot Reviews (comments) view and display from server , to server
 *
 * @author ntwari egide
 *
 */


public class CommentView {
    public void makeComment()throws Exception{

        Scanner commentIng = new Scanner(System.in);
        Comment comment = new Comment();
        System.out.println("\tSPOT COMMENTING");
        System.out.println("\t-----------------------");
        System.out.println("Enter your comment");
        String content = commentIng.nextLine();
        comment.setSpotId(1);
        comment.setUserId(3);
        comment.setContent(content);
        comment.setStatus("active");
        comment.setCreated_at(new Date());
        comment.setUpdatedAt(new Date());

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-comment");
        requestBody.setAction("register");
        requestBody.setObject(comment);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    public void updateTheComment()throws Exception{
        Comment comment = new Comment();
        comment.setComment_id(1);
        comment.setContent("Updated my comment is registered now");
        comment.setSpotId(1);
        comment.setCreated_at(new Date());
        comment.setStatus("active");
        comment.setUserId(3);
        comment.setUpdatedAt(new Date());

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-comment");
        requestBody.setAction("update");
        requestBody.setObject(comment);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    public void makeReplyComment()throws Exception{
        Comment comment = new Comment();
        comment.setComment_id(19);
        comment.setContent("my reply on comment is registered now");
        comment.setSpotId(1);
        comment.setCreated_at(new Date());
        comment.setStatus("active");
        comment.setUserId(3);
        comment.setReplyId(1);
        comment.setUpdatedAt(new Date());

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-comment");
        requestBody.setAction("reply-comment");
        requestBody.setObject(comment);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }

    public void updateCommentStatus()throws Exception {
        Comment comment = new Comment();
        comment.setComment_id(19);
        comment.setStatus("inactive");
        comment.setUpdatedAt(new Date());

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-comment");
        requestBody.setAction("update-status");
        requestBody.setObject(comment);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }
    }
}
