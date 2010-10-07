package vooga.games.doodlejump;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import vooga.engine.player.control.*;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class DoodleSprite extends PlayerSprite {
	private BallSprite ball;
	private boolean ballMade = false;
	
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
		if (getVerticalSpeed() < 0.5)
			setVerticalSpeed(getVerticalSpeed() + 0.01);
		
		if(ballMade) {
			ball.update(elapsedTime);
		}
	}
	
	public void shoot() {
		try{
			BufferedImage image = ImageIO.read(new File("src/vooga/games/doodlejump/images/doodle_up.png"));
			setNewImage(image);
			BufferedImage ballImage = ImageIO.read(new File("src/vooga/games/doodlejump/images/ball.png")); 
			ball = new BallSprite("ball", "flying", new Sprite(ballImage, getX() + getWidth() / 2 - ballImage.getWidth() / 2, getY() - ballImage.getHeight()));
			ballMade = true;
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
