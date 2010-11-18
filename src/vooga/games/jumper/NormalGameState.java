package vooga.games.jumper;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelParser;
import vooga.engine.state.GameState;

public class NormalGameState extends GameState{
	
	private Game myGame;
	
	public NormalGameState(Game game){
		myGame = game;
	}
	
	@Override
	public void initialize() {
		initLevel();
	}
	
	private void initLevel(){
		LevelFactory factory = new LevelParser();
		
		PlayField levelPlayfield = factory.getPlayfield(filepath, currentgame)
	}
}
