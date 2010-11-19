package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.*;

public class LevelButton extends Button{
	
	private String levelName;
	
	public LevelButton(Game game){
		super(game);
	}

	public LevelButton(Game game, BufferedImage image, double x, double y) {
		super(game, image, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed() {
//		switch levelName;
//		{
//			case "easy": 
//			case "medium":
//			case "hard":
//		}
	}
	
	public void setLevel(String levelName){
		this.levelName = levelName;
	}

}
