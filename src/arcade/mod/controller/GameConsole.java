package arcade.mod.controller;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.cyberion.states.PlayState;

public class GameConsole extends Console {
	static LevelManager levelManager;
	static GameStateManager stateManager;
	static GameState playState;
	static ArrayList<Sprite> allSprites;
	static public boolean modified;
	public static final int PLAY_STATE = 4;

	public GameConsole(Game game) {
		super();
		modified = false;
		stateManager = game.getGameStateManager();
		playState = stateManager.getGameState(PLAY_STATE);
		allSprites = playState.getSprites();
		levelManager = playState.getLevelManager();
		

	}

	// public boolean isModified() {
	// return modified;
	// }
	//
	// public static void setModified(boolean b) {
	// modified = b;
	// }
	//
	// public GameState refresh() {
	// setModified(false);
	// return playState;
	// }

}