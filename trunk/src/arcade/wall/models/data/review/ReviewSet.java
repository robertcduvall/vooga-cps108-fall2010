package arcade.wall.models.data.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.wall.models.data.DataSet;

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
		currentID++;
		return myDbAdapter.insert(myTable, row);
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
					row.get("Content")
					));
		}
		return returnReviews;
	}
	
}
