package vooga.games.cyberion.sprites.playership;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;

public class PlayerShip extends BetterSprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	KeyboardControl keyboardControl;

	public PlayerShip() {
		super();
	}

	public PlayerShip(String name, BetterSprite s) {

		super(name, s);

	}

	private int life = 3;
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

	public void moveLeft() {
		moveX(-Resources.getDouble("playerSpeed"));
	}

	public void moveRight() {
		moveX(Resources.getDouble("playerSpeed"));
	}

	public void moveUp() {
		moveY(-Resources.getDouble("playerSpeed"));
	}

	public void moveDown() {
		moveY(Resources.getDouble("playerSpeed"));
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