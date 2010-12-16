package arcade.wall.models.data.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

/**
 * A CommentSet contains all the VOOGA Comments made by all users. It is linked to our online database.
 * @author John Kline
 * @author David Herzka
 */
public class CommentSet {

	private static DatabaseAdapter myDbAdapter;
	private static String myTable;
	public static int currentID;
	
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
		row.put("GameInfo_Title", comment.getGameInfoTitle());
		row.put("User_Id", comment.getUserId());
		row.put("String", comment.getString());
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
					row.get("String"));
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
		row.put("GameInfo_Title", comment.getGameInfoTitle());
		row.put("User_Id", comment.getUserId());
		row.put("String", comment.getString());
		row.put("Rating", newRating);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("GameInfo_Title", comment.getGameInfoTitle());
		conditions.put("User_Id", comment.getUserId());
		conditions.put("String", comment.getString());
		return myDbAdapter.update(myTable, conditions, row);
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
					row.get("String")
					));
		}
		return returnComments;
	}
	
	/**
	 * Updates a matching row's field in the database to a new value.
	 */
	public boolean updateField(String fieldToMatch, String valueToMatch, String fieldToUpdate, String newValue) {
		Map<String, String> row = new HashMap<String, String>();
		row.put(fieldToUpdate, ""+newValue);
		return myDbAdapter.update(myTable, fieldToMatch, valueToMatch, row);
	}
	
	/**
	 * Retrieves the value of a desired field of a matched row.
	 */
	public String getValue(String fieldToMatch, String valueToMatch, String desiredField) {
		Map<String, String> row = myDbAdapter.getRows(myTable, fieldToMatch, valueToMatch).get(0);
		return row.get(desiredField);
	}
}
