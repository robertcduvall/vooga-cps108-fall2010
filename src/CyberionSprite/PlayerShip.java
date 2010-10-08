package CyberionSprite;

import java.awt.image.BufferedImage;

import GameEvent.PlayerFireEvent;
import GameEvent.PlayerMoveEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;

import engine.event.EventManager;

public class PlayerShip extends Sprite {

	private int life;
	private int weaponPower;

	public int getWeaponPower() {
		return weaponPower;
	}

	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}

	private EventManager eventManager;
	private BaseInput bsInput;

	public PlayerShip(BufferedImage image, double x, double y, int life,
			int weaponPower, EventManager eventManager, BaseInput bsInput) {
		super(image, x, y);
		this.setLife(life);
		this.weaponPower = weaponPower;
		this.eventManager = eventManager;
		this.bsInput = bsInput;
	}

	public void update(long elapsedTime) {

		move();
		fire();

	}

	public void move() {
		if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_RIGHT)) {
			setX(getX() + 5);
		}
		if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_LEFT)) {
			setX(getX() - 5);
		}
		if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_UP)) {
			setY(getY() - 5);
		}
		if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_DOWN)) {
			setY(getY() + 5);
		}
		eventManager.fireEvent("PlayerMoveEvent", new PlayerMoveEvent(this,
				"PlayerMoveEvent", getX(), getY()));
	}

	public void fire() {
		if (bsInput.isKeyPressed(java.awt.event.KeyEvent.VK_F))
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
