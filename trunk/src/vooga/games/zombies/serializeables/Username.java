package vooga.games.zombies.serializeables;

import vooga.engine.networking.client.Serializeable;

/**
 * Username is a simple example of how to use the Serializable interface. If you want to send a piece of data then wrap a class around that data and have it
 * implements the Serializable interface to be able to send it over the network.  The Username class wraps the userName to be sent to a game to let the main
 * player know who the other participants in the game are.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class Username extends Serializeable{
	private String userName;
	private static final String identifier = "userName:";

	/**
	 * Constructor that saves the userName.
	 * 
	 * @param userName the userName
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Username(String userName){
		this.userName = userName;
	}

	/**
	 * Takes a String and parses the userName from the string and returns a new Username object from that seed.
	 * 
	 * @param data the String representing a Username object
	 * @return a new Username object based on the data in the data param
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static Serializeable deserialize(String data) {
		return new Username(data);
	}

	/**
	 * Takes the userName and returns the String that represents that Username.
	 * 
	 * @return String to send to the socket that represents the Username
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String serialize() {
		return identifier + userName;
	}
	
	public static String getIdentifier(){
		return identifier;
	}
	
	/**
	 * @return the userName
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String getUsername(){
		return userName;
	}
}
