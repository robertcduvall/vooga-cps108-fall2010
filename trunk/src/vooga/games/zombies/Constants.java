package vooga.games.zombies;

/**
 * Keeps the shared paths/constants
 * @author Yang
 *
 */
public interface Constants {

	public static int GAME_WIDTH=700;
	public static int GAME_HEIGHT=500;
	
	//File Paths
	public static final String PLAYER_CLASS = "vooga.games.zombies.Shooter";
	public static final String MAIN_CLASS = "vooga.games.zombies.Blah";
	//public static final String XML_PATH="src/vooga/games/zombies/resources/overlays/overlays.xml";
	public static final String STATES_XML_PATH="src/vooga/games/zombies/resources/overlays/nonPlayStateOverlays.xml";
	public static final String DEFAULT_RESOURCE_DIRECTORY="src/vooga/games/zombies/resources/";
	public static final String RESOURCE_FILENAME="game.properties";
	
	//Control variables
	public static final String ZOMBIE_LEFT="zombieleft";
	public static final String ZOMBIE_RIGHT="zombieright";
	public static final String ZOMBIE_UP="zombieup";
	public static final String ZOMBIE_DOWN="zombiedown";
	public static final String PLAYER_LEFT="playerleft";
	public static final String PLAYER_RIGHT="playerright";
	public static final String PLAYER_UP="playerup";
	public static final String PLAYER_DOWN="playerdown";
	public static final String ATTACKLEFT="zombieattackleft";
	public static final String ATTACKRIGHT="zombieattackright";
	public static final String ATTACKUP="zombieattackup";
	public static final String ATTACKDOWN="zombieattackdown";
	public static final String DEATH="zombiedeath";
}
