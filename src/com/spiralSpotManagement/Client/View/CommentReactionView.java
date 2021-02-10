package com.spiralSpotManagement.Client.View;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.Model.CommentReactions;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.util.Date;


public class CommentReactionView {
    public void insertCommentReaction()throws Exception{
        CommentReactions commentReactions = new CommentReactions();
        commentReactions.setComment_reaction_id("2");
        commentReactions.setUser_id("3");
        commentReactions.setUpdated_at(new Date());
        commentReactions.setLiked(false);
        commentReactions.setComment_id("19");

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/spot-reaction");
        requestBody.setAction("register");
        requestBody.setObject(commentReactions);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        ResponseBody responseBody = clientServerConnector.ConnectToServer(requestBody);

        for (Object response: responseBody.getResponse()){
            ResponseStatus responseStatus = (ResponseStatus) response;
            System.out.println("\t\t -------------------------------------- STATUS: "+responseStatus.getStatus()+" ---------------------------");
            System.out.println("\t\t --------------         Meaning: "+responseStatus.getMessage());
            System.out.println("\t\t --------------         Action: "+responseStatus.getActionToDo());
            System.out.println("\t\t ------------------------------------------------------------------------------");
        }

//            throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                InsertCommentReactionQuery
//        );
//        preparedStatement.setString(1, commentReaction.getComment_reaction_id());
//        preparedStatement.setString(2, commentReaction.getComment_id());
//        preparedStatement.setString(3, commentReaction.getUser_id());
//        preparedStatement.setBoolean(4, commentReaction.getLiked());
//        preparedStatement.setString(5, toDateTime(commentReaction.getCreated_at()));
//        preparedStatement.setString(6, toDateTime(commentReaction.getUpdated_at()));
//        preparedStatement.execute();
    }
}
