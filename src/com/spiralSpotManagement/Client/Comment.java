package com.spiralSpotManagement.SpotModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Comment.java This is a class for handling Spot Reviews (comments)
 *
 * @author Cedric Izabayo
 *
 */

public class Comment {

  private String comment_id, spot_id, user_id, reply_id, content, status;
  private Date created_at, updated_at;

  /**
   * Constructor in which id and timestamps auto generated
   * @author Cedric Izabayo
   */
  public Comment() {
    this.comment_id = UUID.randomUUID().toString();
    this.created_at = new Date();
    this.updated_at = new Date();
  }

  /**
   * Constructor in case one is initialising a comment
   * @author Cedric Izabayo
   */
  public Comment(
    String comment_id,
    String spot_id,
    String user_id,
    String reply_id,
    String content,
    String status,
    Date created_at,
    Date updated_at
  ) {
    this.comment_id = comment_id;
    this.spot_id = spot_id;
    this.user_id = user_id;
    this.reply_id = reply_id;
    this.content = content;
    this.status = status;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public String getComment_id() {
    return comment_id;
  }

  public void setComment_id(String comment_id) {
    this.comment_id = comment_id;
  }

  public String getSpot_id() {
    return spot_id;
  }

  public void setSpot_id(String spot_id) {
    this.spot_id = spot_id;
  }

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public String getReply_id() {
    return reply_id;
  }

  public void setReply_id(String reply_id) {
    this.reply_id = reply_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  private static final String InsertCommentQuery =
    "INSERT INTO comments (comment_id, spot_id, user_id, content, created_at, updated_at) VALUES(?,?,?,?,?,?)";
  private static final String InsertCommentReplyQuery =
    "INSERT INTO comments (comment_id, spot_id, user_id, reply_id, content, created_at, updated_at) VALUES(?,?,?,?,?,?,?)";
  private static final String UpdateCommentQuery =
    "UPDATE comments  SET spot_id=?, user_id=?, reply_id=?, content=?, updated_at=? WHERE comment_id=?";
  private static final String UpdateCommentStatusQuery =
    "UPDATE comments  SET status=?, updated_at=? WHERE comment_id=?";

  /**
   * insertComment This is a method for creating a new comment
   * @author Cedric Izabayo
   */
  public static void insertComment(Comment comment, Connection connection)
    throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(
      InsertCommentQuery
    );
    preparedStatement.setString(1, comment.getComment_id());
    preparedStatement.setString(2, comment.getSpot_id());
    preparedStatement.setString(3, comment.getUser_id());
    preparedStatement.setString(4, comment.getContent());
    preparedStatement.setString(5, toDateTime(comment.getCreated_at()));
    preparedStatement.setString(6, toDateTime(comment.getUpdated_at()));
    preparedStatement.execute();
  }

  /**
   * toDateTime This is a method for parsing time in a date time format supported by mysql
   * @author Cedric Izabayo
   */
  public static String toDateTime(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return format.format(date);
  }

  /**
   * insertCommentReply This is a method for creating a new comment reply
   * @author Cedric Izabayo
   */
  public static void insertCommentReply(Comment comment, Connection connection)
    throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(
      InsertCommentReplyQuery
    );
    preparedStatement.setString(1, comment.getComment_id());
    preparedStatement.setString(2, comment.getSpot_id());
    preparedStatement.setString(3, comment.getUser_id());
    preparedStatement.setString(4, comment.getReply_id());
    preparedStatement.setString(5, comment.getContent());
    preparedStatement.setString(6, toDateTime(comment.getCreated_at()));
    preparedStatement.setString(7, toDateTime(comment.getUpdated_at()));
    preparedStatement.execute();
  }

  /**
   * updateComment This is a method for updating a comment
   * @author Cedric Izabayo
   */
  public static void updateComment(Comment comment, Connection connection)
    throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(
      UpdateCommentQuery
    );
    preparedStatement.setString(1, comment.getSpot_id());
    preparedStatement.setString(2, comment.getUser_id());
    preparedStatement.setString(3, comment.getReply_id());
    preparedStatement.setString(4, comment.getContent());
    preparedStatement.setString(5, toDateTime(comment.getUpdated_at()));
    preparedStatement.setString(6, comment.getComment_id());
    preparedStatement.execute();
  }

  /**
   * updateCommentStatus This is a method for changing comment status
   * @author Cedric Izabayo
   */
  public static void updateCommentStatus(
    String comment_id,
    String status,
    Connection connection
  )
    throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(
      UpdateCommentStatusQuery
    );
    preparedStatement.setString(1, status);
    preparedStatement.setString(2, toDateTime(new Date()));
    preparedStatement.setString(3, comment_id);
    preparedStatement.execute();
  }
}
