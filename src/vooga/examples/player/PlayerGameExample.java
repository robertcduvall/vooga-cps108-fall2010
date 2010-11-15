package vooga.examples.player;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;

import vooga.engine.core.Game;
import vooga.engine.core.VoogaPlayField;

public class PlayerGameExample extends Game {

	//private Background background;
	//private PlayerExample doodleRight;
	
	private VoogaPlayField voogaPlayField;
	
	public void initResources() {
		super.initResources();
		//voogaPlayField = getCurrentLevel();
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		
	}
	
	public static void main(String [] args) {
		GameLoader game = new GameLoader();
		game.setup(new PlayerGameExample(), new Dimension(532, 850), false);
		game.start();
	}
}
