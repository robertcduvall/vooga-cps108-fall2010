package vooga.games.zombieland;

import java.util.ResourceBundle;

public interface IZombielandConstants {
	
	static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	static final String MAIN_RESOURCES_PATH = "vooga.games.zombieland.game";
	static final ResourceBundle bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
	
	static final String DELIM = bundle.getString("delim");
}
