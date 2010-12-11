package arcade.wall.models;


/**
 * Trying to develop interface that all WallModels should be implementing.
 * @author John
 *
 */
public interface IWallModel {

	public void addComment(Comment comment);
	public CommentSet getCommentSet();
}
