package vooga.examples.networking.zombies;

import vooga.engine.networking.client.Serializeable;

/**
 * Location is a simple example of how to use the Serializable interface. If you want to send a piece of data then wrap a class around that data and have it
 * implements the Serializable interface to be able to send it over the network.  The Location class wraps the x and y coordinates that make up a location and 
 * has serialize and deserialize methods that convert the Location into a String to send over the network and convert the String into a Location to receive 
 * from the network.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class Location extends Serializeable{
	private int x;
	private int y;
	private static final String identifier = "location:";

	/**
	 * Constructor that saves the x and y.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Takes a String and parses the column and row from the string and returns a new Location object from that row and column.
	 * 
	 * @param data the String representing a Location object
	 * @return a new Location object based on the data in the data param
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static Serializeable deserialize(String data) {
		String location = data.substring(identifier.length());
		int y = Integer.parseInt(location.substring(0, location.indexOf("_")));
		int x = Integer.parseInt(location.substring(location.indexOf("_") + 1));
		return new Location(x, y);
	}

	/**
	 * Takes the row number and the column number and returns the String that represents that move.
	 * 
	 * @return String to send to the socket that represents the Location
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String serialize() {
		return identifier + Integer.toString(y) + "_" + Integer.toString(x);
	}
	
	public static String getIdentifier(){
		return identifier;
	}
	
	/**
	 * @return the x coordinate of the location
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y coordinate of the location
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getY() {
		return y;
	}
}
