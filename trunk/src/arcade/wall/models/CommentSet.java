package arcade.wall.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

/**
 * A CommentSet contains all the VOOGA Comments made by all users. It is linked to our online database.
 * @author John, David Herzka
 *
 */
public class CommentSet implements Iterable<Comment> {
	
	public Comment currentComment = null;
	public DatabaseAdapter myDbAdapter;
	public String myTable;

	public CommentSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
	}

	/**
	 * Returns the size of the CommentSet (number of rows).
	 */
	public int size() {
		List<String> col = myDbAdapter.getColumn(myTable, "User_Name");
		return col.size();
	}

	/**
	 * Inserts a new row into the database, based on the given Comment.
	 * @param comment
	 * 		The comment to add
	 * @return
	 * 		Whether the addition was successful
	 */
	public boolean addComment(Comment comment) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Game_Name", comment.getGameName());
		row.put("User_Name", comment.getUserName());
		row.put("Comment_String", comment.getCommentString());
		row.put("Rating", comment.getRating());
		return myDbAdapter.insert(myTable, row);
	}
	
	/**
	 * Uses the DatabaseAdapter.update() method to update entries in the database that meet the conditions for
	 * "changing a rating" - that is, the UserName, GameName, and CommentString should match.
	 * @param comment
	 * 		The comment being considered
	 * @param newRating
	 * 		The new rating to assign to the comment
	 * @return
	 * 		Whether the update was successful
	 */
	public boolean updateCommentRating(Comment comment, String newRating) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Game_Name", comment.getGameName());
		row.put("User_Name", comment.getUserName());
		row.put("Comment_String", comment.getCommentString());
		row.put("Rating", newRating);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Game_Name", comment.getGameName());
		conditions.put("User_Name", comment.getUserName());
		conditions.put("Comment_String", comment.getCommentString());
		return myDbAdapter.update(myTable, conditions, row);
	}

	/**
	 * Returns a Comment constructed from a row of the database.
	 * @param rowNo
	 * 		The row number
	 */
	public Comment getComment(int rowNo) {
////		return new Comment(myDbAdapter.getColumn(myTable, "Game_Name").get(rowNo),
////						   myDbAdapter.getColumn(myTable, "User_Name").get(rowNo),
////						   myDbAdapter.getColumn(myTable, "Comment_String").get(rowNo),
////						   myDbAdapter.getColumn(myTable, "Rating").get(rowNo));
//		Map<String, String> conditions = new HashMap<String, String>(); 
//		//TODO this is not how we should be doing this
//		List<Map<String, String>> rows = myDbAdapter.getRows(myTable, "User_Id", ""+rowNo);
//		Map<String, String> row = rows.get(0);
//		return new Comment(row.get("Game_Name"), row.get("User_Name"), 
//				row.get("Comment_String"), row.get("Rating"));
		return new Comment("blah", "blah", "blah", "blah");
	}

	/**
	 * Determines whether the given comment is in conflict with one already existing in this CommentSet.
	 */
	public boolean commentIsConflicting(Comment comment) {
		for (Comment c: this) {
			if (c.equals(comment))
				return true;
		}
		return false;
	}
	
	@Override
	public Iterator<Comment> iterator() {
		return new Iterator<Comment>() {

			int mySize = size();
			int myLoc = 0;

			@Override
			public boolean hasNext() {
				return myLoc < mySize;
			}

			@Override
			public Comment next() {
				return getComment(myLoc++);
			}

			@Override
			/**
			 * This method will not be implemented
			 */
			public void remove() {
				throw (new UnsupportedOperationException());
			}

		};
	}
}
