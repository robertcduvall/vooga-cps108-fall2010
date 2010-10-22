package vooga.games.tronlegacy;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.games.tronlegacy.CyclePlayer;

/**
 * 
 * @author BrentSodman
 * 
 */

public class CyclePlayer extends PlayerSprite {

	private final static double SPEED = 0.1;
	private final static String classpath = "vooga.games.tronlegacy.CyclePlayer";

	private enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	EventManager eventManager;
	KeyboardControl controller;
	private boolean paused;
	private int directionIndex;

	public CyclePlayer(String name, String stateName, Sprite s, EventManager em) {
		super(name, stateName, s);

		eventManager = em;
	}

	public KeyboardControl addPlayerControl(KeyboardControl kb, int upKey, int downKey, int leftKey, int rightKey) {
		controller = kb;
		try {
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(upKey, "changeDirection", classpath,
					"UP");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(downKey, "changeDirection", classpath,
					"DOWN");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(leftKey, "changeDirection", classpath,
					"LEFT");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(rightKey, "changeDirection",
					classpath, "RIGHT");
			controller.addInput(KeyEvent.VK_P, "invokePause", classpath, null);
		} catch (ClassNotFoundException e) {
			System.out
					.println("Failed attaching KeyboardControl to humanPlayer.");
		}
		return controller;
	}

	public void invokePause() {
		paused = !paused;
	}

	public boolean isPaused() {
		return paused;
	}

	public void changeDirectionRandom() {
		//rotates clockwise or counterclockwise randomly
		if (Math.random() < 0.5) {
			directionIndex = (directionIndex - 1) % 4;
		} else {
			directionIndex = (directionIndex + 1) % 4;
		}

		switch (directionIndex) {
		case 0:
			changeDirection("UP");
			break;
		case 1:
			changeDirection("RIGHT");
			break;
		case 2:
			changeDirection("DOWN");
			break;
		case 3:
			changeDirection("LEFT");
			break;
		default:
			break;
		}

	}

	public void changeDirection(String direction) {
		switch (Direction.valueOf(direction)) {
		case UP:
			setSpeed(0, -SPEED);
			directionIndex = 0;
			break;
		case DOWN:
			setSpeed(0, SPEED);
			directionIndex = 2;
			break;
		case LEFT:
			setSpeed(-SPEED, 0);
			directionIndex = 3;
			break;
		case RIGHT:
			setSpeed(SPEED, 0);
			directionIndex = 1;
			break;
		default:
			break;
		}
	}

	// experimental and in progress (not really working yet)
	public void aiUpdate(PlayField playField) {

		for (SpriteGroup spriteGroup : playField.getGroups()) {
			if (spriteGroup != null) {
				for (Sprite sprite : spriteGroup.getSprites()) {
					if (sprite != null) {

						switch (directionIndex) {
						case 0:
							if (sprite.getY() < getY()
									&& getDistance(sprite) < 50) {
								changeDirectionRandom();
							}
							break;
						case 1:
							if (sprite.getX() > getX()
									&& getDistance(sprite) < 50) {
								changeDirectionRandom();
							}
							break;
						case 2:
							if (sprite.getY() > getY()
									&& getDistance(sprite) < 50) {
								changeDirectionRandom();
							}
							break;
						case 3:
							if (sprite.getX() < getX()
									&& getDistance(sprite) < 50) {
								changeDirectionRandom();
							}
							break;
						default:
							break;
						}

					}
				}
			}
		}

	}

}
