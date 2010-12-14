package arcade.wall.models;

import arcade.wall.models.data.comment.Comment;
import arcade.wall.models.data.comment.CommentSet;
import arcade.wall.models.data.gamereport.GameReportSet;


/**
 * Trying to develop interface that all WallModels should be implementing.
 * @author John
 *
 */
public interface IWallModel {

	public void addComment(Comment comment);
	public CommentSet getCommentSet();
	public GameReportSet getGameReportSet();
}
