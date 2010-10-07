package vooga.games.doodlejump;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import vooga.engine.player.control.*;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class DoodleSprite extends PlayerSprite {
	PlayerSprite ball;
	
	public DoodleSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
	}

	public void moveLeft() {
		try{
			BufferedImage image = ImageIO.read(new File("src/vooga/games/doodlejump/images/doodle_left.png"));
			setNewImage(image);
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
		if (isOnScreen()) {
			setX(getX() - 5);
		}

	}

	public void moveRight() {
		try{
			BufferedImage image = ImageIO.read(new File("src/vooga/games/doodlejump/images/doodle_right.png"));
			setNewImage(image);
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
		if (isOnScreen()) {
			setX(getX() + 5);
		}
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void shoot() {
		try{
			BufferedImage image = ImageIO.read(new File("src/vooga/games/doodlejump/images/doodle_up.png"));
			setNewImage(image);
			BufferedImage ballImage = ImageIO.read(new File("src/vooga/games/doodlejump/images/ball.png")); 
			ball = new PlayerSprite("ball", "flying", new Sprite(ballImage, getX() + getWidth() / 2 - ballImage.getWidth() / 2, getY() - ballImage.getHeight()));
			ball.setVerticalSpeed(0.7);
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public void render(Graphics2D g){
		super.render(g);
		if(ball != null)
			ball.render(g);
	}
}
