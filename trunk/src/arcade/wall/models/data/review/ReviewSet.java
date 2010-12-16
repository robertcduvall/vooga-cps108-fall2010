package arcade.wall.models.data.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.wall.models.data.DataSet;
import arcade.wall.models.data.comment.Comment;

/**
 * A ReviewSet contains all the VOOGA Reviews made by all users. It is linked to our online database.
 * @author John, David Herzka
 */
public class ReviewSet extends DataSet {

	public ReviewSet(String host, String dbName, String tableName, String user,
			String pass) {
		super(host, dbName, tableName, user, pass);
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
		row.put("Id", review.getId());
		row.put("User_Id", review.getUserId());
		row.put("GameInfo_Title", review.getGameInfoTitle());
		row.put("Content", review.getContent());
		row.put("Rating", review.getRating());
		currentID++;
		boolean returnB = myDbAdapter.insert(myTable, row);
		System.out.println(returnB);
		return returnB;
	}
	
	/**
	 * Returns all Reviews whose values match the given parameters.
	 * @param fieldName - the field name to be considered
	 * @param value - the value to match
	 * @return a List of all the Reviews that match
	 */
	public List<Review> getReviewsByField(String fieldName, String value) {
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
		for( Map<String, String> row: myDbAdapter.getRows(myTable, conditions)) {
			if (!row.get("Rating").equals(review.getRating()))
				return true;
		}
		return false;
	}
	
}
