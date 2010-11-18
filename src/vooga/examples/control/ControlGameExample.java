package vooga.examples.control;

import java.awt.Dimension;
import java.awt.Graphics2D;

import vooga.engine.core.Game;

import com.golden.gamedev.GameLoader;

public class ControlGameExample extends Game {
	//private Background background;
	//private PlayerExample doodleRight;
	//private Control joystickControl;
	
	public void initResources() {
		super.initResources();
		//background = new ImageBackground(Resources.getImage("images/background"),532,850);
		//doodleRight = new PlayerExample("doodleRight", new Sprite(Resources.getAnimation("leftFacingDoodleAnimation")));
		//joystickControl = new ControlExample(doodleRight, this);
		//joystickControl.addInput(Joystick.LEFT, "moveLeft", "vooga.examples.player.PlayerExample", String.class, int.class);
		//joystickControl.setParams("Speed", 10);
	}
	
	public void update(long elapsedTime) {
		//joystickControl.update();
	}
	
	public static void main(String [] args) {
		GameLoader game = new GameLoader();
		game.setup(new ControlGameExample(), new Dimension(532, 850), false);
		game.start();
	}
}
