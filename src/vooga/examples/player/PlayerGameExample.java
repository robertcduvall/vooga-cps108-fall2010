package vooga.examples.player;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;

public class PlayerGameExample extends Game {

	//private Background background;
	//private PlayerExample doodleRight;
	
	public void initResources() {
		super.initResources();
		
		
		//background = new ImageBackground(Resources.getImage("images/background"),532,850);
		//doodleRight = new PlayerExample("doodleRight", new Sprite(Resources.getAnimation("leftFacingDoodleAnimation")));
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		//background.render(g);
		//doodleRight.render(g);
	}
	
	public static void main(String [] args) {
		GameLoader game = new GameLoader();
		game.setup(new PlayerGameExample(), new Dimension(532, 850), false);
		game.start();
	}
}
