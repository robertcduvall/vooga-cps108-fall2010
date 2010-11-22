package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;
import vooga.games.galaxyinvaders.sprites.EnemySprite;
import vooga.games.galaxyinvaders.states.GalaxyGameState;
import vooga.games.galaxyinvaders.states.PauseState;
import vooga.games.galaxyinvaders.states.PlayState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * The GalaxyInvaders class is the main Game class for Galaxy Invaders. It keeps track of the sprites, 
 * and has update called every turn by GoldenT, from which it calls update methods on all its sprite
 * groups. There are no command line arguments to run GalaxyInvaders.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class DropThis extends Game {

	private LevelManager levelManager;
	private PlayField playfield;
	
	private GameState play;
	private GameState pause;
	private GameState gameOver;

	/**
	 * Method inherited from Game. Initializes the game state and all the sprites in the game.
	 */
	public void initResources() {
		super.initResources();
		initLevelManager();
		playfield = levelManager.loadFirstLevel();
		processEnemyPathPoints(playfield.getGroup("enemies").getSprites());
		play = new PlayState(this, playfield);
		pause = new PauseState(this, play, Resources.getString("pauseStateText"), Color.RED);
		gameOver = new BasicTextGameState(Resources.getString("gameOverText"), Color.RED);
		this.setAsPlayGameState(play);
		stateManager.addGameState(play, pause, gameOver);
		stateManager.activateOnly(play);
	}

	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}


	private void processEnemyPathPoints(Sprite[] group) {
		int levelNum = levelManager.getCurrentLevel();
		//TODO: figure out why it cannot find this file path. i've tried lots of different paths. 
		//TODO: i don't get that exception when i use the following path:
		String filepath = "src/vooga/games/galaxyinvaders/resources/levels/level"+levelNum+".txt";
		ArrayList<Point> points = PathPointParser.getPathPoints(filepath);
		int timerNum = PathPointParser.getTimerNum();
		for(Sprite s : group) {
			if(s!=null) {
				((EnemySprite) s).setPathPointsAndTimerNum(points, timerNum);
			}
		}
	}
	
	public GameState getPlayGameState(){
		return play;
	}
	
	public void pauseGame(){
		stateManager.switchTo(pause);
	}
	public void resumeGame(){
		stateManager.switchTo(play);
	}
	public void gameOver(){
		stateManager.switchTo(gameOver);
	}
	
	public void startNewGame(){
		this.finish();
		DropThis.main(null);
	}

	public void switchLevel(){
		playfield = levelManager.loadNextLevel();
//		processEnemyPathPoints(playfield.getGroup("enemies").getSprites());
	}
	
	/**
	 * Java main method
	 * 
	 * @param args do nothing
	 */
	public static void main(String[] args) {
		launch(new DropThis());
	}

}