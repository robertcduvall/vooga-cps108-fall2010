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
	private boolean died;
	private PlayState playState;
	private int bulletDelay = 20;
	private double maxHeight;

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
			setX(getX() - 5);
		} else {
			setX(532);
		}

	}

	public void moveRight() {
		BufferedImage[] doodleImage = { Resources.getImage("doodleRight") };
		setImages(doodleImage);
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
		bulletDelay--;
	}

	public void shoot() {
		if (bulletDelay <= 0) {
			BufferedImage[] doodleImage = { Resources.getImage("doodleUp") };
			setImages(doodleImage);
			BufferedImage ballImage = Resources.getImage("ball");
			BallSprite ball = new BallSprite("ball", new Sprite(ballImage,
					getX() + getWidth() / 2 - ballImage.getWidth() / 2, getY()
							- ballImage.getHeight()));
			ball.setVerticalSpeed(-0.7);
			if (getVerticalSpeed() < 0)
				ball.setVerticalSpeed(-1.5);
			playState.addBall(ball);
			bulletDelay = 20;
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	public void setDied(boolean b) {
		died = b;
	}
	
	public boolean getDied(){
		return died;
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
}
