package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.widget.*;

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
		System.out.println("Pressed");
	}
	
	public void setLevel(String levelName){
		this.levelName = levelName;
	}

}
