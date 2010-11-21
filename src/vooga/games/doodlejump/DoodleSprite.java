package vooga.games.doodlejump;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.Resources;

/**
 * The DoodleSprite class extends PlayerSprite and defines how the main
 * character doodle should function
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleSprite extends BetterSprite {
	private boolean died;
	private ArrayList<BallSprite> balls;
	private int bulletDelay = 20;
	private OverlayString gameOverString;
	
	public DoodleSprite(){
		this(Resources.getImage("doodleRight"));
	}

	public DoodleSprite(BufferedImage image) {
		super(image);
		died = false;
		balls = new ArrayList<BallSprite>();
	}

	public void moveLeft() {
		setImage(Resources.getImage("doodleLeft"));
		if (isOnScreen()) {
			setX(getX() - 5);
		} else {
			setX(500);
		}

	}

	public void moveRight() {
		setImage(Resources.getImage("doodleRight"));
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
			setImage(Resources.getImage("doodle_up"));
			BufferedImage ballImage = Resources.getImage("ball");
			BallSprite ball = new BallSprite("ball", new Sprite(
					ballImage, getX() + getWidth() / 2
							- ballImage.getWidth() / 2, getY()
							- ballImage.getHeight()));
			ball.setVerticalSpeed(-0.7);
			if (getVerticalSpeed() < 0)
				ball.setVerticalSpeed(-1.5);
			balls.add(ball);
			//game.ballGroup.add(ball);
			bulletDelay = 20;
		}
	}

	public void setDied(boolean b) {
		died = b;
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		for (BallSprite ball : balls)
			ball.render(g);
		if (gameOverString != null || (died && gameOverString != null)) {
			gameOverString.render(g);
			//game.gameOver();
		}
	}
}
