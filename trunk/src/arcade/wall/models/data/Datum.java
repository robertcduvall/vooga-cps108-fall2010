package arcade.wall.models.data;

/**
 * A Datum object represents a data entity in our online database, such as a Comment or GameReport.
 * @author John
 *
 */
public abstract class Datum {

	private String myId;
	
	public Datum(String id) {
		myId = id;
	}
	
	public String getId() {
		return myId;
	}
	
	public void setId(String id) {
		myId = id;
	}
}
