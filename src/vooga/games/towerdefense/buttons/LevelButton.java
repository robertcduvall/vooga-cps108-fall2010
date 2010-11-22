package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;
import vooga.games.towerdefense.states.PlayState;
import vooga.widget.Button;

/**
 * A button designed to launch a level from the main menu.
 * Currently will launch a level based on String. Once the 
 * LevelParser is functional.
 * 
 * @author Daniel Koverman
 *
 */
public class LevelButton extends Button{
	
	private static final long serialVersionUID = 1L;
	private String levelName;
	
	public LevelButton(Game game){
		super(game);
	}

	public LevelButton(Game game, BufferedImage image, double x, double y) {
		super(game, image, x, y);
	}

	@Override
	public void actionPerformed() {
		GameStateManager gameStateManager = Resources.getGame().getGameStateManager();
		PlayState play = (PlayState) gameStateManager.getGameState(1);
		if(levelName.equals("easyLevel")){
			play.changeLevel(Resources.getString("easyLevelXML"), "easyLevelBackground", "easyLevelPathPoints");			
		} else if(levelName.equals("mediumLevel")){
			play.changeLevel(Resources.getString("mediumLevelXML"), "mediumLevelBackground", "mediumLevelPathPoints");
		} else if(levelName.equals("hardLevel")){
			play.changeLevel(Resources.getString("hardLevelXML"), "hardLevelBackground", "hardLevelPathPoints");
		} else{
			System.out.println("level not found: \"" + levelName+"\"");
		}
		gameStateManager.switchTo(play);
	}
	
	public void setLevel(String levelName){
		this.levelName = levelName;
	}

}
