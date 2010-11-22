package vooga.games.cyberion.sprites.playership;

import java.awt.event.KeyEvent;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.games.cyberion.events.PlayerFireEvent;

public class PlayerShip extends BetterSprite {
		vooga.engine.control.KeyboardControl keyboardControl;

		public PlayerShip(){
			super(Resources.getImage("playerShip"));
			
		}
		
		public PlayerShip(String name, BetterSprite s) {
			
			super(name, s);
			
		}

		private int life;
		private int weaponPower;

		public int getWeaponPower() {
			return weaponPower;
		}

		public void setWeaponPower(int weaponPower) {
			this.weaponPower = weaponPower;
		}

		private EventPool eventManager = new EventPool();

		public void setEventManager(EventPool em) {
			eventManager = em;
		}

		public vooga.engine.control.KeyboardControl setKeyboardControl(KeyboardControl kb) {
			keyboardControl = kb;
//			keyboardControl.addInput(KeyEvent.VK_LEFT, "moveLeft",
//					"vooga.games.cyberion.sprites.PlayerShip", null);
//			keyboardControl.addInput(KeyEvent.VK_RIGHT, "moveRight",
//					"vooga.games.cyberion.sprites.PlayerShip", null);
//			keyboardControl.addInput(KeyEvent.VK_DOWN, "moveDown",
//					"vooga.games.cyberion.sprites.PlayerShip", null);
//			keyboardControl.addInput(KeyEvent.VK_UP, "moveUp",
//					"vooga.games.cyberion.sprites.PlayerShip", null);
//			keyboardControl.addInput(KeyEvent.VK_SPACE, "fire",
//					"vooga.games.cyberion.sprites.PlayerShip", null);
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
			System.out.println();
			setX(getX() - 5);
		}

		public void moveUp() {
			setY(getY() - 5);
		}

		public void moveDown() {

			setY(getY() + 5);
		}

		public void listen() {
			eventManager.addEvent(new PlayerFireEvent(this, "PlayerMoveEvent",  getX(), getY(), weaponPower));
		}

		public void fire() {
			eventManager.addEvent( new PlayerFireEvent(this, "PlayerFireEvent", getX(), getY(), weaponPower));
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