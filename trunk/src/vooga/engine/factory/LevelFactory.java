package vooga.engine.factory;

import java.io.File;

import com.golden.gamedev.object.PlayField;


/**
 * This interface is used to retrieve the playfield for the current level.
 * Additional methods could be added to specifically get Sprite Velocities etc.
 * @author Bhawana, Cameron, Derek
 */
public interface LevelFactory {
	
	/**
	 * @param levelFactoryFile - file containing details of the game objects for the current level
	 * @return Playfield for the current level
	 */
	public PlayField getPlayfield(File levelFactoryFile);
	
}
