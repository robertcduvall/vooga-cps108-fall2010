package arcade.wall.models.data.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

/**
 * A ReviewSet contains all the VOOGA Reviews made by all users. It is linked to our online database.
 * @author John Kline
 * @author David Herzka
 */
public class ReviewSet {

	private static DatabaseAdapter myDbAdapter;
	private static String myTable;
	public static int currentID;

	public ReviewSet(String host, String dbName, String tableName, String user,
			String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
		currentID = size();
	}

	/**
	 * Returns the size of the ReviewSet (number of rows).
	 */
	public int size() {
		List<String> col = myDbAdapter.getColumn(myTable, "Id");
		return col.size();
	}

	/**
	 * Inserts a new row into the database, based on the given Review.
	 * @param review
	 * 		The review to add
	 * @return
	 * 		Whether the addition was successful
	 */
	public boolean addReview(Review review) {
		Map<String, String> row = new HashMap<String, String>();
		Map<String, String> conditions = new HashMap<String, String>();
		row.put("Id", review.getId());
		row.put("User_Id", review.getUserId());
		row.put("GameInfo_Title", review.getGameInfoTitle());
		row.put("Content", review.getContent());
		row.put("Rating", review.getRating());
		currentID++;
		return myDbAdapter.replace(myTable, row);
	}

	/**
	 * Returns all Reviews whose values match the given parameters.
	 * @param fieldName - the field name to be considered
	 * @param value - the value to match
	 * @return a List of all the Reviews that match
	 */
	public static List<Review> getReviewsByField(String fieldName, String value) {
		List<Review> returnReviews = new ArrayList<Review>();
		for (Map<String, String> row: myDbAdapter.getRows(myTable, fieldName, value)) {
			returnReviews.add(new Review( 
					row.get("Id"),
					row.get("User_Id"),
					row.get("GameInfo_Title"),
					row.get("Content"),
					row.get("Rating")
			));
		}
		return returnReviews;
	}

	/**
	 * Calculates the average rating for the given game. A single user's rating is only taken into account once, even
	 * if they have made multiple comments about a game.
	 */
	public double getAverageRating(String gameName) {
		double averageRating = 0;
		List<Review> gameReviews = getReviewsByField("GameInfo_Title", gameName);
		List<String> userIds = new ArrayList<String>();
		for(Review review: gameReviews){
			if (!userIds.contains(review.getUserId())) {
				averageRating += Integer.parseInt(review.getRating());
				userIds.add(review.getUserId());
			}
		}
		averageRating /= userIds.size();
		return averageRating;
	}

	/**
	 * Determines whether the given review is in conflict with one already existing in this ReviewSet.
	 */
	public boolean reviewIsConflicting(Review review) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("GameInfo_Title", review.getGameInfoTitle());
		conditions.put("User_Id", review.getUserId());
		 if (myDbAdapter.getRows(myTable, conditions) != null) {
			 return true;
		 }
		 return false;
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

	public static Review getRandomReview(String fieldName, String value) {
		List<Review> possibleReviews = getReviewsByField(fieldName, value);
		int i = (int)(Math.random()*possibleReviews.size());
		try {
			return possibleReviews.get(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}