package arcade.wall.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

/**
 * A CommentSet contains all the VOOGA Comments made by all users. It is linked to our online database.
 * @author John, David Herzka
 *
 */
public class CommentSet { //implements Iterable<Comment> {

	public Comment currentComment = null;
	public DatabaseAdapter myDbAdapter;
	public String myTable;
	public int currentID;

	public CommentSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
		currentID = size();
	}

	/**
	 * Returns the size of the CommentSet (number of rows).
	 */
	public int size() {
		List<String> col = myDbAdapter.getColumn(myTable, "Id");
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
		row.put("Id", comment.getId());
		row.put("GameInfo_Title", comment.getGameTitle());
		row.put("User_Id", comment.getUserId());
		row.put("String", comment.getString());
		row.put("Rating", comment.getRating());
		currentID++;
		return myDbAdapter.insert(myTable, row);
	}

	/**
	 * Updates the ratings given by a particular user to a particular game.
	 * @param selectedGameName
	 * 		The game to match
	 * @param myGamerName
	 * 		The username to match
	 * @param selectedValue
	 * 		The new rating to overwrite with
	 */
	public void updateCommentRatings(String selectedGameName,
			String myGamerName, String selectedValue) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("GameInfo_Title", selectedGameName);
		conditions.put("User_Id", myGamerName);
		for (Map<String, String> row : myDbAdapter.getRows(myTable, conditions)) {
			Comment commentToUpdate = new Comment(row.get("Id"),
					row.get("GameInfo_Title"),
					row.get("User_Id"),
					row.get("String"), 
					row.get("Rating"));
			updateCommentRating(commentToUpdate, selectedValue);
		}
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
		row.put("GameInfo_Title", comment.getGameTitle());
		row.put("User_Id", comment.getUserId());
		row.put("String", comment.getString());
		row.put("Rating", newRating);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("GameInfo_Title", comment.getGameTitle());
		conditions.put("User_Id", comment.getUserId());
		conditions.put("String", comment.getString());
		return myDbAdapter.update(myTable, conditions, row);
	}

//	/**
//	 * Returns a Comment constructed from a row of the database.
//	 * @param rowNo
//	 * 		The row number
//	 */
//	public Comment getComment(int rowNo) {
//		//TODO this is not how we should be doing this - it looks like the database is continually opening
//		//a connection then closing it
//		//TODO Use SELECT * FROM `Comments` LIMIT 5 to select the first five comments, or you can just use 
//		//SELECT *. You should be getting all the comments with one query. You should only use this method
//		//When you need to see a specific comment made by a user
//		List<Map<String, String>> rows = myDbAdapter.getRows(myTable, "Id", ""+rowNo);
//		Map<String, String> row = rows.get(0);
//		return new Comment(row.get("Id"), row.get("GameInfo_Title"), row.get("User_Id"), 
//				row.get("String"), row.get("Rating"));
//	}

	/**
	 * Determines whether the given comment is in conflict with one already existing in this CommentSet.
	 */
	public boolean commentIsConflicting(Comment comment) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("GameInfo_Title", comment.getGameTitle());
		conditions.put("User_Id", comment.getUserId());
		for( Map<String, String> row: myDbAdapter.getRows(myTable, conditions)) {
			if (!row.get("Rating").equals(comment.getRating()))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns all Comments whose values match the given parameters.
	 * @param fieldName - the field name to be considered
	 * @param value - the value to match
	 * @return a List of all the comments that match
	 */
	public List<Comment> getCommentsByField(String fieldName, String value) {
		List<Comment> returnComments = new ArrayList<Comment>();
		for (Map<String, String> row: myDbAdapter.getRows(myTable, fieldName, value)) {
			returnComments.add(new Comment( row.get("Id"),
					row.get("GameInfo_Title"),
					row.get("User_Id"),
					row.get("String"),
					row.get("Rating")
					));
		}
		return returnComments;
	}

	/**
	 * Calculates the average rating for the given game. A single user's rating is only taken into account once, even
	 * if they have made multiple comments about a game.
	 */
	public double getAverageRating(String gameName) {
		double averageRating = 0;
		List<Comment> gameComments = getCommentsByField("GameInfo_Title", gameName);
		List<String> userIds = new ArrayList<String>();
		for(Comment comment: gameComments){
			if (!userIds.contains(comment.getUserId())) {
				averageRating += Integer.parseInt(comment.getRating());
				userIds.add(comment.getUserId());
			}
		}
		averageRating /= userIds.size();
		return averageRating;
	}
}
