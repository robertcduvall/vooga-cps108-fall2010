package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
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
	
	public LevelButton(){
		super();
	}

	public LevelButton(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	@Override
	public void actionPerformed() {
		GameStateManager gameStateManager = Resources.getGame().getGameStateManager();
		PlayState play = (PlayState) gameStateManager.getGameState(2);
		GameState mainMenu = gameStateManager.getGameState(3);
		if(levelName.equals("easyLevel")){
			play.changeLevel(Resources.getString("easyLevelXML"), "easyLevelBackground", "easyLevelPathPoints");			
			gameStateManager.switchTo(play);
		} else if(levelName.equals("mediumLevel")){
			play.changeLevel(Resources.getString("mediumLevelXML"), "mediumLevelBackground", "mediumLevelPathPoints");
			gameStateManager.switchTo(play);
		} else if(levelName.equals("hardLevel")){
			play.changeLevel(Resources.getString("hardLevelXML"), "hardLevelBackground", "hardLevelPathPoints");
			gameStateManager.switchTo(play);
		} else if(levelName.equals("restart")){
			gameStateManager.switchTo(mainMenu);
		} else{
			System.out.println("level not found: \"" + levelName+"\"");
		}
		
	}
	
	public void setLevel(String levelName){
		this.levelName = levelName;
	}

}
