package vooga.games.tronlegacy;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.games.tronlegacy.CyclePlayer;

public class CyclePlayer extends PlayerSprite {
	
	private final static double SPEED = .1;
	private final static String classpath = "vooga.games.tronlegacy.CyclePlayer";
	
	private enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	EventManager eventManager;
	KeyboardControl controller;

	public CyclePlayer(String name, String stateName, Sprite s, EventManager em) {
		
		super(name, stateName, s);
		
		eventManager = em;

	}
	
	public KeyboardControl addPlayerControl(KeyboardControl kb){
		
		controller = kb;
				
		System.out.println(this.getClass().getClassLoader());
				
		controller.addInput(KeyEvent.VK_UP, "changeDirection", classpath, "UP");
		controller.addInput(KeyEvent.VK_DOWN, "changeDirection", classpath, "DOWN");
		controller.addInput(KeyEvent.VK_LEFT, "changeDirection", classpath, "LEFT");
		controller.addInput(KeyEvent.VK_RIGHT, "changeDirection", classpath, "RIGHT");
		
		return controller;
	}
	
	public KeyboardControl addComputerControl(){
		
		//do ai stuff here
		
		return controller;
	}

	public void changeDirection(String direction){

		switch (Direction.valueOf(direction)){
		case UP:
			setSpeed(0, SPEED);
			break;
		case DOWN:
			setSpeed(0, -SPEED);
			break;
		case LEFT:
			setSpeed(-SPEED, 0);
			break;
		case RIGHT:
			setSpeed(SPEED,0);
			break;
		default:
			break;
		}
		
	}
}
