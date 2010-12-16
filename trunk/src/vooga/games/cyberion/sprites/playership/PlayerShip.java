package vooga.games.cyberion.sprites.playership;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

public class PlayerShip extends BetterSprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int life;
	private int weaponPower;
	private KeyboardControl keyboardControl;

	public PlayerShip() {
		super();
		System.out.println("called");
		this.life = Resources.getInt("playerLife");
		this.weaponPower = Resources.getInt("playerWeaponPower");
	}

	public PlayerShip(String name, BetterSprite s) {
		super(name, s);
		myLabel = "playerShip";
	}

	public int getWeaponPower() {
		return weaponPower;
	}

	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
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