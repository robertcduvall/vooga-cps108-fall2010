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

	private int life;
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
		// keyboardControl.addInput(KeyEvent.VK_LEFT, "moveLeft",
		// "vooga.games.cyberion.sprites.PlayerShip", null);
		// keyboardControl.addInput(KeyEvent.VK_RIGHT, "moveRight",
		// "vooga.games.cyberion.sprites.PlayerShip", null);
		// keyboardControl.addInput(KeyEvent.VK_DOWN, "moveDown",
		// "vooga.games.cyberion.sprites.PlayerShip", null);
		// keyboardControl.addInput(KeyEvent.VK_UP, "moveUp",
		// "vooga.games.cyberion.sprites.PlayerShip", null);
		// keyboardControl.addInput(KeyEvent.VK_SPACE, "fire",
		// "vooga.games.cyberion.sprites.PlayerShip", null);
		return keyboardControl;
	}

	// private BaseInput bsInput;

	// public PlayerShip(BufferedImage image, double x, double y, int life,
	// int weaponPower, EventManager eventManager, BaseInput bsInput) {
	// super(image, x, y);
	// this.setLife(life);
	// this.weaponPower = weaponPower;
	// this.eventManager = eventManager;
	// this.bsInput = bsInput;
	// }

	public void moveRight() {
		if (getX() < Resources.getInt("maxX"))
			setX(getX() + 5);
	}

	public void moveLeft() {
		if (getX() > 0)
			setX(getX() - 5);
	}

	public void moveUp() {
		if (getY() > 0)
			setY(getY() - 5);
	}

	public void moveDown() {
		if (getY() < Resources.getInt("maxY"))
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