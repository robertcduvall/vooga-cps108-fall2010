package vooga.games.doodlejump;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

/**
 * The DoodleSprite class extends PlayerSprite and defines how the main
 * character doodle should function
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleSprite extends PlayerSprite {
	private ArrayList<BallSprite> balls;
	private DoodleGame game;
	private int bulletDelay = 20;

	public DoodleSprite(String name, String stateName, Sprite s, DoodleGame game) {
		super(name, stateName, s);
		balls = new ArrayList<BallSprite>();
		this.game = game;
	}

	public void moveLeft() {
		try {
			BufferedImage image = ImageIO.read(new File(
					"src/vooga/games/doodlejump/images/doodle_left.png"));
			setNewImage(image);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		System.out.println(isOnScreen());
		if (isOnScreen()) {
			setX(getX() - 5);
		} else {
			setX(500);
		}

	}

	public void moveRight() {
		try {
			BufferedImage image = ImageIO.read(new File(
					"src/vooga/games/doodlejump/images/doodle_right.png"));
			setNewImage(image);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		if (isOnScreen()) {
			setX(getX() + 5);
		} else {
			setX(0);
		}
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getVerticalSpeed() < 0.5)
			setVerticalSpeed(getVerticalSpeed() + 0.01);
		for (BallSprite ball : balls) {
			ball.update(elapsedTime);
		}
		bulletDelay--;
	}

	public void shoot() {
		if (bulletDelay <= 0) {
			try {
				BufferedImage image = ImageIO.read(new File(
						"src/vooga/games/doodlejump/images/doodle_up.png"));
				setNewImage(image);
				BufferedImage ballImage = ImageIO.read(new File(
						"src/vooga/games/doodlejump/images/ball.png"));
				BallSprite ball = new BallSprite("ball", "flying", new Sprite(
						ballImage, getX() + getWidth() / 2
								- ballImage.getWidth() / 2, getY()
								- ballImage.getHeight()));
				ball.setVerticalSpeed(-0.7);
				balls.add(ball);
				game.BallGroup.add(ball);
			} catch (Exception e) {
				System.out.println(e);
				System.exit(0);
			}
			bulletDelay = 20;
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		for (BallSprite ball : balls)
			ball.render(g);
	}
}
