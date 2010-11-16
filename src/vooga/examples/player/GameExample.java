package vooga.examples.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Sprite;

public class GameExample extends Game {

	private PlayField voogaPlayField;
	private PlayerExample player;
	private ImageBackground bg;
	private SpriteGroup sprites;
	
	public void initResources() {
//		BufferedImage[] leftImages = {getImage("resources/images/doodle_left.png"), getImage("resources/images/doodle_left_crouch.png")};
//		BufferedImage[] rightImages = {getImage("resources/images/doodle_right.png"), getImage("resources/images/doodle_right_crouch.png")};
//		AnimatedSprite leftFacingDoodle = new AnimatedSprite(leftImages);
//		leftFacingDoodle.setAnimate(true);
//		AnimatedSprite rightFacingDoodle = new AnimatedSprite(rightImages);
//		rightFacingDoodle.setAnimate(true);
//		Sprite s = new Sprite("leftDoodle", leftFacingDoodle);
//		s.addSprite("rightDoodle", rightFacingDoodle);
		player = new PlayerExample("left", new com.golden.gamedev.object.Sprite(getImage("resources/images/doodle_left.png")));
		player.addSprite("right", getImage("resources/images/doodle_right.png"));
		player.setLocation(getWidth()/2, getHeight()/2);
		sprites = new SpriteGroup("sprites");
		sprites.add(player);
		voogaPlayField = new PlayField();
		BufferedImage background = getImage("resources/images/background.png");
		bg = new ImageBackground(background, 400,400);
		voogaPlayField.addGroup(sprites);
		voogaPlayField.setBackground(bg);

	}

	public void update(long elapsedTime) {
//		player.update(elapsedTime);
		voogaPlayField.update(elapsedTime);
		if(keyPressed(KeyEvent.VK_W)) {
			player.setToCurrentSprite("right");
		}
		if(keyPressed(KeyEvent.VK_S)) {
			player.setToCurrentSprite("left");
		}
//		if(counter % 2 == 0)
//		player.setToCurrentSprite("right");
//		
//		if(counter % 2 == 1)
//		player.setToCurrentSprite("left");
//		counter++;
	}

	public void render(Graphics2D g) {
//		bg.render(g);
//		player.setToCurrentSprite("right");
//		player.setToCurrentSprite("left");
//		player.render(g);
		voogaPlayField.render(g);
		
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GameExample(), new Dimension(400, 400), false);
		game.start();
	}
}