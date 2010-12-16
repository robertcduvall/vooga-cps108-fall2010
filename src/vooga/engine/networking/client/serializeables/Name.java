package vooga.engine.networking.client.serializeables;

import vooga.engine.networking.client.Serializeable;

/**
 * Name is a simple example of how to use the Serializable interface. If you want to send a piece of data then wrap a class around that data and have it
 * implements the Serializable interface to be able to send it over the network.  The Name class wraps the name to be sent to a game to let the main
 * player know who the other participants in the game are.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class Name extends Serializeable{
	private String name;
	private static final String identifier = "name:";

	/**
	 * Constructor that saves the name.
	 * 
	 * @param name the name
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Name(String name){
		this.name = name;
	}

	/**
	 * Takes a String and parses the name from the string and returns a new Name object from that seed.
	 * 
	 * @param data the String representing a Name object
	 * @return a new Name object based on the data in the data param
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static Serializeable deserialize(String data) {
		return new Name(data);
	}

	/**
	 * Takes the name and returns the String that represents that Name.
	 * 
	 * @return String to send to the socket that represents the Name
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String serialize() {
		return identifier + name;
	}
	
	public static String getIdentifier(){
		return identifier;
	}
	
	/**
	 * @return the name
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String getName(){
		return name;
	}
}
