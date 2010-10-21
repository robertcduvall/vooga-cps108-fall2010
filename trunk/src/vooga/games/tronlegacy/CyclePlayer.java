package vooga.games.tronlegacy;

import java.awt.Point;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.games.tronlegacy.CyclePlayer;

public class CyclePlayer extends PlayerSprite {
	
	private final static double SPEED = 0.1;
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
		try {
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_UP, "changeDirection", classpath, "UP");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_DOWN, "changeDirection", classpath, "DOWN");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_LEFT, "changeDirection", classpath, "LEFT");
			controller.setParams(Class.forName("java.lang.String"));
			controller.addInput(KeyEvent.VK_RIGHT, "changeDirection", classpath, "RIGHT");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Failed attaching KeyboardControl to humanPlayer.");
		}
		
		return controller;
	}
	
	public void changeDirectionRandom(){
		int choice = (int) Math.ceil(Math.random()*4);
		
		switch (choice){
		case 1:
			changeDirection("UP");
			break;
		case 2:
			changeDirection("DOWN");
			break;
		case 3:
			changeDirection("LEFT");
			break;
		case 4:
			changeDirection("RIGHT");
			break;
		default:
			break;
		}
		
	}

	public void changeDirection(String direction){
		
		System.out.println(direction);
		
		switch (Direction.valueOf(direction)){
		case UP:
			setSpeed(0, -SPEED);
			break;
		case DOWN:
			setSpeed(0, SPEED);
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
	
	public void aiUpdate(PlayField playField){
		
		for (SpriteGroup spriteGroup : playField.getGroups()){
			if (spriteGroup != null){
				for (Sprite sprite : spriteGroup.getSprites()){
					if (sprite != null){
						if (this.getDistance(sprite) < 10 && !this.equals(sprite) ){
							changeDirectionRandom();
						}
					}

				}
			}
		}
			
	}
	
}
