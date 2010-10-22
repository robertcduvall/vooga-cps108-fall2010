package vooga.games.zombieland;

/**
 * Keeps the shared paths/constants
 * @author Yang
 *
 */
public interface Constants {

	public static int GAME_WIDTH=700;
	public static int GAME_HEIGHT=500;
	
	//File Paths
	public static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	public static final String XML_PATH="src/vooga/games/zombieland/resources/overlays.xml";
	public static final String DEFAULT_RESOURCE_DIRECTORY="src/vooga/games/zombieland/resources/";
	public static final String RESOURCE_FILENAME="game.properties";
	
	//Control variables
	public static final String LEFT=ZombielandResources.getString("LEFT");
	public static final String RIGHT=ZombielandResources.getString("RIGHT");
	public static final String UP=ZombielandResources.getString("UP");
	public static final String DOWN=ZombielandResources.getString("DOWN");
	public static final String ATTACKLEFT=ZombielandResources.getString("ATTACKLEFT");
	public static final String ATTACKRIGHT=ZombielandResources.getString("ATTACKRIGHT");
	public static final String ATTACKUP=ZombielandResources.getString("ATTACKUP");
	public static final String ATTACKDOWN=ZombielandResources.getString("ATTACKDOWN");
	public static final String DEATH=ZombielandResources.getString("DEATH");
}
