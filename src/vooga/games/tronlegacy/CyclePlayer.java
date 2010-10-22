package vooga.games.tronlegacy;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
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

	public KeyboardControl addPlayerControl(KeyboardControl kb) {
		controller = kb;
		try {
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_UP, "changeDirection", classpath,
					"UP");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_DOWN, "changeDirection", classpath,
					"DOWN");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_LEFT, "changeDirection", classpath,
					"LEFT");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_RIGHT, "changeDirection",
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
			directionIndex = (directionIndex - 1) % 3;
		} else {
			directionIndex = (directionIndex + 1) % 3;
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

	// this AI method is really bad but other stuff is more important (API usage), ill fix it
	// later (it moves randomly)
	public void aiUpdate(PlayField playField) {
		if (Math.random() > 0.98) {
			changeDirectionRandom();
		}
	}

}
