package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.*;

public class LevelButton extends Button{
	
	private String levelName;
	
	public LevelButton(Game game, String levelName, String imageName, double x, double y){
		super(game);
		this.levelName = levelName;
		this.setImage(Resources.getImage(imageName));
	}

	public LevelButton(Game game, BufferedImage image, double x, double y) {
		super(game, image, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed() {
		// TODO Launch the next level somehow using levelName
		
	}

}
