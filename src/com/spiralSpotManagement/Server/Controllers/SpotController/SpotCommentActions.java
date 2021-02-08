package com.spiralSpotManagement.Server.Controllers.SpotController;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.Comment;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Spot Comment actions for managing all spot comment related work
 * @author ntwari egide
 * */


public class SpotCommentActions {
    String InsertCommentQuery =
            "INSERT INTO comments (comment_id, spot_id, user_id, content, created_at, updated_at) VALUES(?,?,?,?,?,?)";
    String InsertCommentReplyQuery =
            "INSERT INTO comments (comment_id, spot_id, user_id, reply_id, content, created_at, updated_at) VALUES(?,?,?,?,?,?,?)";
    String UpdateCommentQuery =
            "UPDATE comments  SET spot_id=?, user_id=?, content=?, updated_at=? WHERE comment_id=?";
    String UpdateCommentStatusQuery =
            "UPDATE comments  SET status=?, updated_at=? WHERE comment_id=?";

    public ResponseStatus insertComment(Comment comment)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(InsertCommentQuery);
            preparedStatement.setString(1, comment.getComment_id().toString());
            preparedStatement.setString(2, comment.getSpotId().toString());
            preparedStatement.setString(3, comment.getUserId().toString());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.setString(5, toDateTime(comment.getCreated_at()));
            preparedStatement.setString(6, toDateTime(comment.getUpdatedAt()));
            int inserted = preparedStatement.executeUpdate();

            if (inserted == 1) {
                return new ResponseStatus(200, "COMMENT ADDED", "you have commented on the spot");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "EXCEPTION ERROR", e.getMessage());
        }

            return null;
        }

    public static String toDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }



    public ResponseStatus updateComment(Comment comment)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateCommentQuery);
            preparedStatement.setString(1, comment.getSpotId().toString());
            preparedStatement.setString(2, comment.getUserId().toString());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setString(4, toDateTime(comment.getUpdatedAt()));
            preparedStatement.setString(5, comment.getComment_id().toString());
            preparedStatement.execute();

            int updated = preparedStatement.executeUpdate();

            if (updated == 1) {
                return new ResponseStatus(200, "COMMENT UPDATED", "you have updated commented on the spot");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "EXCEPTION ERROR", e.getMessage());
        }

        return null;
    }

    public ResponseStatus makeCommentReply(Comment comment)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(InsertCommentReplyQuery);
            preparedStatement.setString(1, comment.getComment_id().toString());
            preparedStatement.setString(2, comment.getSpotId().toString());
            preparedStatement.setString(3, comment.getUserId().toString());
            preparedStatement.setString(4, comment.getReplyId().toString());
            preparedStatement.setString(5, comment.getContent());
            preparedStatement.setString(6, toDateTime(comment.getCreated_at()));
            preparedStatement.setString(7, toDateTime(comment.getUpdatedAt()));
            preparedStatement.execute();

            int insertedReply = preparedStatement.executeUpdate();

            if (insertedReply == 1) {
                return new ResponseStatus(200, "REPLY ADDED", "you have replayed commented on the spot");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "EXCEPTION ERROR", e.getMessage());
        }

        return null;
    }
    public ResponseStatus updateCommentStatus(Comment comment)throws Exception{
        Connection connection = new CloudStorageConnectionHandler().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateCommentStatusQuery);
            preparedStatement.setString(1, comment.getStatus());
            preparedStatement.setString(2, toDateTime(new Date()));
            preparedStatement.setString(3, comment.getComment_id().toString());
            preparedStatement.execute();

            int updated = preparedStatement.executeUpdate();

            if (updated == 1) {
                return new ResponseStatus(200, "COMMENT UPDATED", "you have changed status of the spot comment");
            }
        } catch (Exception e) {
            return new ResponseStatus(500, "EXCEPTION ERROR", e.getMessage());
        }

        return null;
    }
}
