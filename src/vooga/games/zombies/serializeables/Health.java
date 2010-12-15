package vooga.games.zombies.serializeables;

import vooga.engine.networking.client.Serializeable;

/**
 * Health is a simple example of how to use the Serializable interface. If you want to send a piece of data then wrap a class around that data and have it
 * implements the Serializable interface to be able to send it over the network.  The Health class wraps the health of a player so the healths of all the
 * players in a game are synced up.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class Health extends Serializeable{
	private int health;
	private static final String identifier = "health:";

	/**
	 * Constructor that saves the randomly generated seed.
	 * 
	 * @param health the health
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Health(int health){
		this.health = health;
	}

	/**
	 * Takes a String and parses the seed from the string and returns a new Health object from that seed.
	 * 
	 * @param data the String representing a Health object
	 * @return a new Health object based on the data in the data param
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static Serializeable deserialize(String data) {
		return new Health(Integer.parseInt(data.substring(7)));
	}

	/**
	 * Takes the seed and returns the String that represents that Health.
	 * 
	 * @return String to send to the socket that represents the Health
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String serialize() {
		return identifier + Integer.toString(health);
	}
	
	public static String getIdentifier(){
		return identifier;
	}
	
	/**
	 * @return the health
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getHealth(){
		return health;
	}
}
