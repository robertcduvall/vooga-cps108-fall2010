package vooga.games.marioclone;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;

public class GameWonState extends GameEndState {

	// Add way for player to enter name
	
	public GameWonState(Background background, String messageString,
			GameFontManager fontManager) {
		super(background, messageString, fontManager);
	}

}
