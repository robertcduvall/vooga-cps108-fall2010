package vooga.games.doodlejump;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.states.PlayState;

/**
 * The DoodleSprite class extends PlayerSprite and defines how the main
 * character doodle should function
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleSprite extends BetterSprite {
	private static final double MAXIMUM_SCREEN_X = Resources
			.getDouble("maxScreenX");
	private static final double MINIMUM_SCREEN_X = Resources
			.getDouble("minScreenX");
	private static final double MAXIMUM_SCREEN_Y = Resources
			.getDouble("maxScreenY");
	private static final double MINIMUM_SCREEN_Y = Resources
			.getDouble("minScreenY");

	private static final int DELTA_MOVEMENT = 5;
	private static final double DOODLE_VERTICAL_SPEED = 0.5;
	private static final double DELTA_VERTICAL_SPEED = 0.01;
	private static final double BALL_VERTICAL_SPEED = -1.5;
	private static final int ZERO_BULLET_DELAY = 0;
	private static final int BALL_INITIAL_X = 2;
	private static final int BALL_INITIAL_Y = 2;

	private boolean died;
	private PlayState playState;
	private int bulletDelay = Resources.getInt("bulletDelay");
	private double maxHeight;
	private boolean levelComplete = false;

	public DoodleSprite() {
		this(Resources.getImage("doodleRight"));
	}

	public DoodleSprite(BufferedImage image) {
		super(image);
		died = false;
		setMaxHeight(Resources.getDouble("doodleInitialY"));
	}

	public void moveLeft() {
		BufferedImage[] doodleImage = { Resources.getImage("doodleLeft") };
		setImages(doodleImage);
		if (isOnScreen()) {
			setX(getX() - DELTA_MOVEMENT);
		} else {
			setX(MAXIMUM_SCREEN_X);
		}

	}

	public void moveRight() {
		BufferedImage[] doodleImage = { Resources.getImage("doodleRight") };
		setImages(doodleImage);
		if (isOnScreen()) {
			setX(getX() + DELTA_MOVEMENT);
		} else {
			setX(MINIMUM_SCREEN_X);
		}
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getVerticalSpeed() < DOODLE_VERTICAL_SPEED)
			setVerticalSpeed(getVerticalSpeed() + DELTA_VERTICAL_SPEED);
		bulletDelay--;
	}

	public void shoot() {
		if (bulletDelay <= ZERO_BULLET_DELAY) {
			BufferedImage[] doodleImage = { Resources.getImage("doodleUp") };
			setImages(doodleImage);
			makeBall();
			bulletDelay = Resources.getInt("bulletDelay");
		}
	}

	private void makeBall() {
		BufferedImage ballImage = Resources.getImage("ball");
		BallSprite ball = new BallSprite("ball", new Sprite(ballImage, getX()
				+ getWidth() / BALL_INITIAL_X - ballImage.getWidth()
				/ BALL_INITIAL_Y, getY() - ballImage.getHeight()));
		ball.setVerticalSpeed(BALL_VERTICAL_SPEED);
		playState.addBall(ball);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	public void setDied(boolean b) {
		died = b;
	}

	public boolean getDied() {
		return died;
	}

	public boolean doodleFell() {
		return (getY() > MAXIMUM_SCREEN_Y);
	}

	public void setPlayState(PlayState p) {
		playState = p;
	}

	public void setMaxHeight(double max) {
		maxHeight = max;
	}

	public double getMaxHeight() {
		return maxHeight;
	}

	public boolean isLevelComplete() {
		return levelComplete;
	}

	public void setLevelComplete() {
		levelComplete = true;
	}

	public void reset() {
		levelComplete = false;
		died = false;
	}
}
