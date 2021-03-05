package com.spiralSpotManagement.Client.View;
import java.util.UUID;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Client.Middleware.UserAuthMiddleware;
import com.spiralSpotManagement.Server.Model.*;

import java.util.*;

/**
 * Comment.java This is a class for handling Spot Reviews (comments) view and display from server , to server
 *
 * @author ntwari egide
 *
 */


public class CommentView {
    public void makeComment(Spot spot)throws Exception{

        Scanner commentIng = new Scanner(System.in);
        Comment comment = new Comment();
        System.out.println("\tSPOT COMMENTING");
        System.out.println("\t-----------------------");
        System.out.println("Enter your comment");
        String content = commentIng.nextLine();


        comment.setSpotId(spot.getSpotId());
        comment.setUserId(3);
        comment.setComment_id(UUID.randomUUID().toString());
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

            UserLog userLogToInsertOnSearch = new UserLog();
            userLogToInsertOnSearch.setUser_id(new UserAuthMiddleware().checkForUserExistence());
            String logAction= "commented on spot" ;
            userLogToInsertOnSearch.setAction(logAction);

            new ReportsView().createUserlog(userLogToInsertOnSearch);
        }
    }

    public void updateTheComment()throws Exception{
        Comment comment = new Comment();
        comment.setComment_id(UUID.randomUUID().toString());
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

            UserLog userLogToInsertOnSearch = new UserLog();
            userLogToInsertOnSearch.setUser_id(new UserAuthMiddleware().checkForUserExistence());
            String logAction= "Updated spot comment " ;
            userLogToInsertOnSearch.setAction(logAction);

            new ReportsView().createUserlog(userLogToInsertOnSearch);
        }
    }

    public void makeReplyComment()throws Exception{
        Comment comment = new Comment();
        comment.setComment_id(UUID.randomUUID().toString());
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
        comment.setComment_id(UUID.randomUUID().toString());
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

    /**
     * @author: Abizera Oreste
     * @param spot
     * This method is used to view comments on a given spot
     */
    public void viewComments(Spot spot) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-comment");
        requestBody.setAction("getComments");
        requestBody.setObject(spot);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        boolean found = false;
        Integer index = 0;
        List<Object> commentsList = new ArrayList<>();
        for (Object response: responseBody.getResponse()){
            index++;
            found = true;
            Comment comment = (Comment) response;
            System.out.println(index + ". " + comment.getContent());
            commentsList.add(spot);
        }

        if(!found) {
            System.out.println("No Comments Found.");
        }
    }
}
