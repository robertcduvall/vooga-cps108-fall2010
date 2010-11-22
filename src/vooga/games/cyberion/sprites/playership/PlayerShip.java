package vooga.games.cyberion.sprites.playership;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;

public class PlayerShip extends BetterSprite {
	KeyboardControl keyboardControl;

	public PlayerShip() {
		super();

	}

	public PlayerShip(String name, BetterSprite s) {

		super(name, s);

	}

	private int life = 1;
	private int weaponPower = 1;

	public int getWeaponPower() {
		return weaponPower;
	}

	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}

	private EventPool eventPool = new EventPool();

	public void setEventManager(EventPool em) {
		eventPool = em;
	}

	public KeyboardControl setKeyboardControl(KeyboardControl kb) {
		keyboardControl = kb;
		return keyboardControl;
	}

	public void moveRight() {
			setX(getX() + 5);
	}

	public void moveLeft() {
			setX(getX() - 5);
	}

	public void moveUp() {
			setY(getY() - 5);
	}

	public void moveDown() {
			setY(getY() + 5);
	}

	public boolean isAlive() {
		return getLife() > 0 ? true : false;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

}