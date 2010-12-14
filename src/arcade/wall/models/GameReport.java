package arcade.wall.models;

/**
 * A GameReport contains info regarding a game such as its average rating, number of comments
 * @author John
 *
 */
public class GameReport extends Datum {
	private String myGameInfoTitle;
	private String myAverageRating;
	private String myNumberOfComments;
	
	public GameReport(String id, String gameInfoTitle, String averageRating, String numberOfComments) {
		super(id);
		myGameInfoTitle = gameInfoTitle;
		myAverageRating =averageRating;
		myNumberOfComments = numberOfComments;
	}
	
	public String getGameInfoTitle() {
		return myGameInfoTitle;
	}
	
	public String getAverageRating() {
		return myAverageRating;
	}
	
	public String getNumberOfComments() {
		return myNumberOfComments;
	}

	public void setAverageRating(String averageRating) {
		myAverageRating = averageRating;
	}
	
	public void setNumberOfComments(String numberOfComments) {
		this.myNumberOfComments = numberOfComments;
	}
}
