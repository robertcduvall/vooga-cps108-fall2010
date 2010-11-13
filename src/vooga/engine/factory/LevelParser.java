package vooga.engine.factory;

import java.io.File;

import com.golden.gamedev.object.PlayField;

public class LevelParser implements LevelFactory{

	@Override
	public PlayField getPlayfield(File levelFactoryFile) {
		
		PlayField newplayfield = new PlayField();
		newplayfield = parseToPlayField(levelFactoryFile, newplayfield);
		
		return newplayfield;
	}

	/**
	 * 
	 * @param levelFactoryFile
	 * @param newplayfield
	 * @return
	 */
	private PlayField parseToPlayField(File levelFactoryFile,
			PlayField newplayfield) {
	
		
		
		
		
		
		
		
		
		
		
		return null;
		
	}

}
