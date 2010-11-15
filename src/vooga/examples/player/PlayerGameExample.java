package vooga.examples.player;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;

public class PlayerGameExample extends Game {

	private Background background;
	
	public void initResources() {
		super.initResources();
		
		
		background = new ImageBackground(Resources.getImage("background"),532,850);
		//Sprite dooleRight = new Sprite(Resources.getAnimation("leftFacingDoodleAnimation"),0,0);
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
