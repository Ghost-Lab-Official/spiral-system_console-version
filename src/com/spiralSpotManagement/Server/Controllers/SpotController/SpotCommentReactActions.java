package com.spiralSpotManagement.Server.Controllers.SpotController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.CommentReactions;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.spiralSpotManagement.SpotModule.Comment.toDateTime;

/**
 * Spot Comment Reaction This is a class for Spot Reviews (comments) reactions actions
 *
 * @author ntwari egide
 *
 */
public class SpotCommentReactActions {
    private static final String InsertCommentReactionQuery =
            "INSERT INTO comment_reactions (comment_reaction_id, comment_id, user_id, liked, created_at, updated_at) VALUES(?,?,?,?,?,?)";

    public ResponseStatus addCommentReaction(CommentReactions commentReaction)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(InsertCommentReactionQuery);
            preparedStatement.setString(1, commentReaction.getComment_reaction_id());
            preparedStatement.setString(2, commentReaction.getComment_id());
            preparedStatement.setString(3, commentReaction.getUser_id());
            preparedStatement.setBoolean(4, commentReaction.getLiked());
            preparedStatement.setString(5, toDateTime(commentReaction.getCreated_at()));
            preparedStatement.setString(6, toDateTime(commentReaction.getUpdated_at()));

            int inserted = preparedStatement.executeUpdate();

            if (inserted == 1) {
                return new ResponseStatus(200, "REACTION ADDED", "you have added comment reaction on the spot");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "EXCEPTION ERROR", e.getMessage());
        }

        return null;
    }
}
