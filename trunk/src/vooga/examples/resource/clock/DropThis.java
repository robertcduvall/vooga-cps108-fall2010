package vooga.examples.resource.clock;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayClock;
import vooga.engine.resource.clock.WorldClock;

/**
 * Demonstrates how to initialize an OverlayClock, place 
 * it somewhere on the screen, update, and render it. The code 
 * required to actually make the game run without errors
 * is hacked together and should be ignored. Generally, 
 * you should create your OverlayClock inside a GameState 
 * rather than in the game class.
 * 
 * @author Daniel Koverman
 *
 */
public class DropThis extends Game{
	
	public static final int WIDTH = 1050;
	public static final int HEIGHT = 600;
	private OverlayClock clock;
	
	@Override
	public void initResources(){
		WorldClock.setTimeZone("EST");
		clock = new OverlayClock();
		clock.setLocation(WIDTH/2, HEIGHT/2);
	}
	
	@Override
	public void update(long elapsedTime) {
		clock.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g) {
		clock.render(g);
	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(WIDTH, HEIGHT), false);
		game.start();
	}

}
