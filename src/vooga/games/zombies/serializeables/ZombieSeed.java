package vooga.games.zombies.serializeables;

import vooga.engine.networking.client.Serializeable;

/**
 * ZombieSeed is a simple example of how to use the Serializable interface. If you want to send a piece of data then wrap a class around that data and have it
 * implements the Serializable interface to be able to send it over the network.  The ZombieSeed class wraps the seed that will be passed to generate the
 * zombies so that the zombie appearance can be random for each game but follow the same pattern within a game.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class ZombieSeed extends Serializeable{
	private long seed;
	private static final String identifier = "seed:";

	/**
	 * Constructor that saves the randomly generated seed.
	 * 
	 * @param seed the seed
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ZombieSeed(long seed){
		this.seed = seed;
	}

	/**
	 * Takes a String and parses the seed from the string and returns a new ZombieSeed object from that seed.
	 * 
	 * @param data the String representing a ZombieSeed object
	 * @return a new ZombieSeed object based on the data in the data param
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static Serializeable deserialize(String data) {
		return new ZombieSeed(Long.parseLong(data));
	}

	/**
	 * Takes the seed and returns the String that represents that ZombieSeed.
	 * 
	 * @return String to send to the socket that represents the ZombieSeed
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String serialize() {
		return identifier + Long.toString(seed);
	}
	
	public static String getIdentifier(){
		return identifier;
	}
	
	/**
	 * @return the seed
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public long getSeed(){
		return seed;
	}
}
