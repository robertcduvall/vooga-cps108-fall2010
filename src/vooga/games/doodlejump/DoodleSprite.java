package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class DoodleSprite extends PlayerSprite {

	public DoodleSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
	}

	public void moveLeft() {
		if (isOnScreen()) {
			setX(getX() - 5);
		}

	}

	public void moveRight() {
		if (isOnScreen()) {
			setX(getX() + 5);
		}
	}
	
	public void moveUp() {
		if (isOnScreen()) {
			setY(getY() - 5);
		}

	}

	public void moveDown() {
		if (isOnScreen()) {
			setY(getY() + 5);
		}
	}
}
