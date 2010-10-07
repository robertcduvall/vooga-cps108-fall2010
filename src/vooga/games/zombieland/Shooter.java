package vooga.games.zombieland;

import com.golden.gamedev.object.*;
import vooga.engine.player.control.PlayerSprite;

public class Shooter extends PlayerSprite {

	private int speed;

	public Shooter(String name, String stateName, AnimatedSprite s,
			int playerHealth, int playerRank) {
		super(name, stateName, s, playerHealth, playerRank);
		speed = -1;
	}

	public void goLeft() {
		setToCurrentSprite("Left");
		moveX(speed);
	}

	public void goRight() {
		setToCurrentSprite("Right");
		moveX(Math.abs(speed));
	}

	public void goUp() {
		setToCurrentSprite("Up");
		moveY(speed);
	}

	public void goDown() {
		setToCurrentSprite("Down");
		moveY(Math.abs(speed));
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
}
