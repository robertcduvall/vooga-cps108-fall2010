package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import vooga.engine.control.Control;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.*;
import vooga.engine.resource.*;
import vooga.engine.resource.random.Randomizer;
import vooga.engine.resource.random.RandomizerException;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.PauseGameState;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;


/**
 * The GalaxyInvaders class is the main Game class for Galaxy Invaders. It keeps track of the sprites, 
 * and has update called every turn by GoldenT, from which it calls update methods on all its sprite
 * groups. There are no command line arguments to run GalaxyInvaders.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class DropThis extends Game {

	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 800;

	//private Background background;
	private PlayField playfield;
	
	private GalaxyGameState play;
	private PauseGameState pause;
	private GameState gameOver;

	private SpriteGroup pauseMenu;
	private SpriteGroup gameOverMenu;

	/**
	 * Method inherited from Game. Initializes the game state and all the sprites in the game.
	 */
	public void initResources() {
		super.initResources();
		//background = new ColorBackground(Color.BLACK, GAME_WIDTH, GAME_HEIGHT);
		playfield = new PlayField();	
		playfield.addColorBackground(Color.BLACK);
		stateManager.switchTo(pause);
	}

	/**
	 * Rendering method for GoldenT
	 * 
	 */
	public void render(Graphics2D g) {
		super.render(g);
	}

	/**
	 * This method is called every turn by the game engine. It determines whether
	 * bombs or powerups should be dropped, and updates all the sprite groups. It
	 * also checks to see if the game is over, and ends it if it is.
	 */
	public void update(long time) {
		super.update(time);
	}
	


	public void initGameStates(){
		super.initGameStates();
		initializeGameStates();
	}

	private void initializeGameStates() {
		pauseMenu = new SpriteGroup("pauseMenu");
		gameOverMenu = new SpriteGroup("gameOverMenu");
		play = new GalaxyGameState(this, playfield);
		pause = new PauseGameState(play, "Welcome to GalaxyInvaders!\n" +
										"To move, use the left and right arrow keys\n" +
										"To fire, use the space bar\n" +
										"To play, or pause and return to the menu, press P", Color.WHITE);
		gameOver = new BasicTextGameState("Game Over!\n" +
										"To restart the game, press R", Color.WHITE);
		stateManager.addGameState(play, pause, gameOver);
		//initializeMenus(pause, pauseMenu, "pauseMenu");
		//initializeMenus(gameOver, gameOverMenu, "gameOverMenu");
	}
	
	/*private void initializeMenus(GameState state, SpriteGroup menu, String overlayGroup){
		//TODO:Re-work GameStates to take advantage of changes
		state.addGroup(menu);
		SpriteGroup strings = overlayTracker.getOverlayGroup(overlayGroup);
		Sprite[] lines = strings.getSprites();
		int size = strings.getSize();
		for (int i = 0; i<size; i++)
		{
			OverlayString oString = (OverlayString) lines[i];
			oString.setColor(Color.WHITE);
			menu.add(oString);
		}			
	}*/

	public void toggle(){
		stateManager.toggle(pause);
		stateManager.toggle(play);
	}
	
	public void gameOver(){
		stateManager.switchTo(gameOver);
	}
	
	public void startNewGame(){
		this.finish();
		DropThis.main(null);
	}
	
	/**
	 * Java main method
	 * 
	 * @param args do nothing
	 */
	public static void main(String[] args) {
		/*GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(GAME_WIDTH, GAME_HEIGHT), false);
		game.start();*/
		launch(new DropThis());
	}

}