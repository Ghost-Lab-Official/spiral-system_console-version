package com.spiralSpotManagement.SpotModule;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static com.spiralSpotManagement.SpotModule.Comment.toDateTime;

/**
 * CommentReactions.java This is a class for handling Spot Reviews Reactions (likes and dislikes)
 *
 * @author Cedric Izabayo
 *
 */

public class CommentReactions {

  private String comment_reaction_id, comment_id, user_id;
  private Boolean liked;
  private Date created_at, updated_at;

  /**
   * Constructor in which id and timestamps auto generated
   * @author Cedric Izabayo
   */
  public CommentReactions() {
    this.comment_reaction_id = UUID.randomUUID().toString();
    this.created_at = new Date();
    this.updated_at = new Date();
  }

  /**
   * Constructor in case one is initialising a comment_reaction
   * @author Cedric Izabayo
   */
  public CommentReactions(
    String comment_reaction_id,
    String comment_id,
    String user_id,
    Boolean liked,
    Date created_at,
    Date updated_at
  ) {
    this.comment_reaction_id = comment_reaction_id;
    this.comment_id = comment_id;
    this.user_id = user_id;
    this.liked = liked;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public String getComment_reaction_id() {
    return comment_reaction_id;
  }

  public void setComment_reaction_id(String comment_reaction_id) {
    this.comment_reaction_id = comment_reaction_id;
  }

  public String getComment_id() {
    return comment_id;
  }

  public void setComment_id(String comment_id) {
    this.comment_id = comment_id;
  }

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public Boolean getLiked() {
    return liked;
  }

  public void setLiked(Boolean liked) {
    this.liked = liked;
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

  private static final String InsertCommentReactionQuery =
    "INSERT INTO comment_reactions (comment_reaction_id, comment_id, user_id, liked, created_at, updated_at) VALUES(?,?,?,?,?,?)";
  private static final String UpdateCommentReactionQuery =
    "UPDATE comment_reactions  SET comment_id=?, user_id=?, liked=?, updated_at=? WHERE comment_reaction_id=?";

  /**
   * insertCommentReaction This is a method for creating a new comment_reaction
   * @author Cedric Izabayo
   */
  public static void insertCommentReaction(
    CommentReactions commentReaction,
    Connection connection
  )
    throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(
      InsertCommentReactionQuery
    );
    preparedStatement.setString(1, commentReaction.getComment_reaction_id());
    preparedStatement.setString(2, commentReaction.getComment_id());
    preparedStatement.setString(3, commentReaction.getUser_id());
    preparedStatement.setBoolean(4, commentReaction.getLiked());
    preparedStatement.setString(5, toDateTime(commentReaction.getCreated_at()));
    preparedStatement.setString(6, toDateTime(commentReaction.getUpdated_at()));
    preparedStatement.execute();
  }

  /**
   * updateCommentReaction This is a method for updating a comment_reaction
   * @author Cedric Izabayo
   */
  public static void updateCommentReaction(
    CommentReactions commentReaction,
    Connection connection
  )
    throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(
      UpdateCommentReactionQuery
    );
    preparedStatement.setString(1, commentReaction.getComment_id());
    preparedStatement.setString(2, commentReaction.getUser_id());
    preparedStatement.setBoolean(3, commentReaction.getLiked());
    preparedStatement.setString(4, toDateTime(commentReaction.getUpdated_at()));
    preparedStatement.setString(5, commentReaction.getComment_reaction_id());
    preparedStatement.execute();
  }
}
