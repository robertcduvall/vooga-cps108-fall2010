package vooga.examples.player;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.engine.overlay.Stat;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * This game is an example of how to use the sprite-switching features of the new core Sprite
 * if you were unable to write a good Level XML file. Normally, all of the initialization
 * code would be handled by the Level parser and done automatically. 
 * 
 * However, this is a good demonstration of the "setAsRenderedSprite()" method. See the
 * update method for its use. 
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 *
 */

public class GameExample extends Game {

	private PlayField myPlayField;
	private PlayerExample player;
	
	public void initResources() {
		//gets arrays of images for left and right-facing animations
		BufferedImage[] leftImages = {getImage("resources/images/doodle_left.png"), getImage("resources/images/doodle_left_crouch.png")};
		BufferedImage[] rightImages = {getImage("resources/images/doodle_right.png"), getImage("resources/images/doodle_right_crouch.png")};
		
		//constructs left-facing animatedSprite
		AnimatedSprite leftFacingDoodle = new AnimatedSprite(leftImages);
		leftFacingDoodle.setAnimate(true);
		
		//constructs right-facing animatedSprite
		AnimatedSprite rightFacingDoodle = new AnimatedSprite(rightImages);
		rightFacingDoodle.setAnimate(true);
		
		//set both animated sprites to loop
		leftFacingDoodle.setLoopAnim(true);
		rightFacingDoodle.setLoopAnim(true);
		
		//sets the delay for animation timer
		leftFacingDoodle.getAnimationTimer().setDelay(100);
		rightFacingDoodle.getAnimationTimer().setDelay(100);
		
		/* 
		 * constructs a new PlayerExample (subclass of Sprite) using the leftFacingDoodle 
		 * animation. here's what's happening: the string "left" is the name used to access
		 * the leftFacingDoodle animation, and the leftFacingDoodle animation is the default
		 * representation of player.
		 */
		player = new PlayerExample("left", leftFacingDoodle);
		
		/*
		 * now, we are going to add the right-facing animated sprite representation to our
		 * player. this method adds the rightFacingDoodle with the string "right" as the name
		 * that you can use if you want to switch the representation of this player to 
		 * the rightFacingDoodle animation.
		 */
		player.addSprite("right", rightFacingDoodle);
		
		//set player location
		player.setLocation(getWidth()/2, getHeight()/2);
		
		//this adds a new stat for score--see the update method for more information
		player.setStat("score", new Stat<Integer>(0));
		
		//construct playfield
		myPlayField = new PlayField();
		
		//construct a SpriteGroup for player so that we can add it to our PlayField
		SpriteGroup sprites = new SpriteGroup("sprites");
		sprites.add(player);

		//set up background
		BufferedImage background = getImage("resources/images/background.png");
		Background bg = new ImageBackground(background, 400,400);
		myPlayField.addGroup(sprites);
		myPlayField.setBackground(bg);

	}

	public void update(long elapsedTime) {
		
		myPlayField.update(elapsedTime);
		
		/*
		 * if the right arrow key is pressed, the sprite will switch to the 
		 * rightFacingDoodle
		 * 
		 * if the left arrow key is pressed, the sprite will switch to the
		 * leftFacingDoodle
		 */
		if(keyPressed(KeyEvent.VK_RIGHT)) {
			player.setAsRenderedSprite("right");
		}
		if(keyPressed(KeyEvent.VK_LEFT)) {
			player.setAsRenderedSprite("left");
		}
		
		/*
		 * This is an example of how to use the stats feature. this updates the 
		 * score by one point for every cycle of the game loop. Obviously, you 
		 * won't ever need to do this, but it shows how one can access the Stat
		 * while avoiding any problems with generics, which is the way the Stat
		 * class is designed.
		 */
		//gets the stat
		Stat<Integer> tempStat = (Stat<Integer>)player.getStat("score");
		//gets the number out of the stat object
		Integer i = tempStat.getStat();
		//bumps the stat by 1
		i++;
		//updates the stat to the new integer
		tempStat.setStat(i);
	}

	public void render(Graphics2D g) {

		myPlayField.render(g);
		
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GameExample(), new Dimension(400, 400), false);
		game.start();
	}
}