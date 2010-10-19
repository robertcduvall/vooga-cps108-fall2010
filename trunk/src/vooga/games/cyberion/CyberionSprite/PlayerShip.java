package vooga.games.cyberion.CyberionSprite;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.games.cyberion.GameEvent.PlayerFireEvent;
import vooga.games.cyberion.GameEvent.PlayerMoveEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
//main player class. extends sprite and includes relevant
// player information such as life and weapon power. creates fire event
// when the F key is pressed. also updates position based on wasd commands.

public class PlayerShip extends PlayerSprite {
	KeyboardControl keyboardControl;

	public PlayerShip(String name, String stateName, Sprite s) {
		
		super(name, stateName, s);
		
	}

	private int life;
	private int weaponPower;

	public int getWeaponPower() {
		return weaponPower;
	}

	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}

	private EventManager eventManager;

	public void setEventManager(EventManager em) {
		eventManager = em;
	}

	public KeyboardControl setKeyboardControl(KeyboardControl kb) {
		keyboardControl = kb;
		keyboardControl.addInput(KeyEvent.VK_LEFT, "moveLeft",
				"vooga.games.cyberion.CyberionSprite.PlayerShip", null);
		keyboardControl.addInput(KeyEvent.VK_RIGHT, "moveRight",
				"vooga.games.cyberion.CyberionSprite.PlayerShip", null);
		keyboardControl.addInput(KeyEvent.VK_DOWN, "moveDown",
				"vooga.games.cyberion.CyberionSprite.PlayerShip", null);
		keyboardControl.addInput(KeyEvent.VK_UP, "moveUp",
				"vooga.games.cyberion.CyberionSprite.PlayerShip", null);
		keyboardControl.addInput(KeyEvent.VK_SPACE, "fire",
				"vooga.games.cyberion.CyberionSprite.PlayerShip", null);
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

	public void update(long elapsedTime) {
		listen();
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

	public void listen() {
		eventManager.fireEvent("PlayerMoveEvent", new PlayerMoveEvent(this,
				"PlayerMoveEvent", getX(), getY()));
	}

	public void fire() {

		eventManager.fireEvent("PlayerFireEvent", new PlayerFireEvent(this,
				"PlayerFireEvent", getX(), getY(), weaponPower));
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
