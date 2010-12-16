package arcade.mod.controller;

import java.util.ArrayList;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.factory.LevelParser;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class GameConsole extends Console {
	static LevelParser levelParser;
	static GameStateManager stateManager;
	static GameState playState;
	static ArrayList<BetterSprite> allSprites;
	static public boolean modified;
	public static final int PLAY_STATE = 4;

	public GameConsole(Game gm) {
		super();
		modified = false;
		stateManager = gm.getGameStateManager();
		playState = stateManager.getGameState(PLAY_STATE);
		allSprites = playState.getSprites();
		levelParser = gm.getLevelParser();
	}

	public boolean isModified() {
		return modified;
	}

	public static void setModified(boolean b) {
		modified = b;
	}

	public  GameState refresh() {
		return playState;
	}

}