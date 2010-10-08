package vooga.games.marioclone;

import vooga.engine.player.control.PlayerSprite;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.Sprite;

/*
 * @author Andrew Brown
 */

@SuppressWarnings("serial")
public class MarioSprite extends PlayerSprite {

	double gravity = .005;
	double friction = .05;
	double speed = .5;
	double jumpSpeed = 1;

	boolean onGround;

	public MarioSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
	}

	public void moveRight() {
		if (isOnScreen()) {
			setHorizontalSpeed(speed);
		} else {
			setX(0);
		}
	}

	public void moveLeft() {
		if (isOnScreen()) {
			setHorizontalSpeed(-speed);
		} else {
			setX(0);
		}
	}

	public void jump() {
		if (isOnScreen()) {
			if (onGround) {
				setVerticalSpeed(-jumpSpeed);
				onGround = false;
			}
		} else {
			setY(0);
		}
	}

	public void crouch() {
		if (isOnScreen()) {
			// //setVerticalSpeed(-0.5);
			// setY(getY() + 5);
		} else {
			setY(0);
		}
	}

	public void setOnGround(boolean b) {
		onGround = b;
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		onGround = false;
		setVerticalSpeed(getVerticalSpeed() + gravity * elapsedTime);
		setHorizontalSpeed(getHorizontalSpeed()
				- Math.signum(getHorizontalSpeed())
				* Math.min(Math.abs(friction * getHorizontalSpeed()), Math
						.abs(getHorizontalSpeed())));

		/*
		 * double yVelocity = getVerticalSpeed(); double newYVelocity =
		 * yVelocity + gravity*elapsedTime; setVerticalSpeed(newYVelocity);
		 */

	}

}
