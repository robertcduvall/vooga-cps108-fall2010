package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Randomizer;
import vooga.engine.resource.RandomizerException;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 * This extension of GameState represents the playing or action state of the MarioClone game.
 * The methods within this class are responsible for running, updating, and rendering all things pertinent
 * to the playing state.
 *
 */

public class GamePlayState extends GameState {

	private ColorBackground myMarioBackground;
	private MarioPlayField myPlayfield;
	private SpriteGroup myMarioGroup;
	private TileMap myMap;
	private MarioSprite myMario;
	private SpriteGroup myEnemyGroup;
	private int myWidth;
	private int myHeight;
	private Stat<Integer> myEnemiesRemaining;
	private OverlayStat myEnemyOverlay;
	private Timer myTimer;
	private List<MarioLevel> myLevels;
	private int myCurrentLevel;
	
	private static final int FREQ_ENEMIES = 5000;
	private static final int NUM_ENEMIES = 1;
	
	public enum State { Win, Lose, Continue };
	
	/**
	 * This constructs a GamePlayState by initializing the player as well as the game dimensions.
	 * 
	 * @param mario is a MarioSprite, which represents the user controlled sprite.
	 * @param width is the desired width of the game window
	 * @param height is the desired height of the game window
	 */
	
	public GamePlayState(MarioSprite mario, int width, int height) {
		this.myMario = mario;
		this.myWidth = width;
		this.myHeight = height;
		init();
	}

	/**
	 * This method is called in the MarioClone update method in order to check if any state transitions need to happen.
	 * 
	 * @return
	 * This method returns one of three state types: win, loss, or continue, depending on the given game conditions.
	 */
	
	public State nextState() {
		if (!myMario.isActive()) {
			return State.Lose;
		} else if (getEnemiesRemaining() == 0) {
			if(myCurrentLevel >= myLevels.size())
				return State.Win;
			else
			{
				myCurrentLevel++;
				return State.Continue;
			}
		} else
			return State.Continue;
	}
	
	/**
	 * This method is responsible for spawning enemies at random locations on the map.
	 * 
	 */
	
	public void spawnEnemies(){
			for(int j = 0; j < NUM_ENEMIES; j++) {
				Enemy enemy = new Enemy("enemy1","regular",Resources.getImage("EnemyR"),Resources.getImage("EnemyL"));
				try {
					enemy.setLocation(Randomizer.nextDouble(0,myWidth), Randomizer.nextDouble(0,myHeight));
				} catch (RandomizerException e) {
					System.out.println("Error - randomizer fail");
				}
				myEnemyGroup.add(enemy);
			}
	}
	

	/**
	 * This method is responsible for updating the main game playfield, which contains all of the game sprites.
	 * 
	 * @param t - the time constant that the engine uses for updating.
	 */
	
	public void update(long t) {
		super.update(t);
    	myPlayfield.update(t);
    	myMario.stop();
		myEnemyGroup.removeInactiveSprites();
		spawnEnemies();
		myEnemiesRemaining.setStat(myEnemyGroup.getSize()); 	
	}
	
	
	/**
	 * This method initializes all necessary variables, such as playfields, backgrounds, enemies, and tiles for
	 * the start of the game.
	 */
	
	public void init() {
		myMarioBackground = new ColorBackground(Color.white);
		myMarioBackground.setClip(0, 0, myWidth, myHeight);
		myPlayfield = new MarioPlayField();		
		myMarioGroup = new SpriteGroup("Mario Group");
		myMario.setLocation(150, 290);
		myMarioGroup.add(myMario);
		myPlayfield.addGroup(myMarioGroup);
		myEnemyGroup = new SpriteGroup("Enemy Group");
		spawnEnemies();
		myPlayfield.addGroup(myEnemyGroup);
		
		try {
			myMap = new TileMap(new File("src/vooga/games/marioclone/testmap.txt"));
		} catch (IOException e) {
			System.out.println("Error with myMap");
		}
		myPlayfield.addTileMap(myMap);
		
		myEnemiesRemaining = new Stat<Integer>(new Integer(myEnemyGroup.getSize()));

		myEnemyOverlay = new OverlayStat("Enemies Remaining: ", myEnemiesRemaining);
		myEnemyOverlay.setLocation(myWidth - 1000, 5);

		myPlayfield.add(myEnemyOverlay);
		myPlayfield.addCollisionGroup(myMarioGroup, myPlayfield.getTileMap().getTileGroup(), new MarioToTileCollision());
		myPlayfield.addCollisionGroup(myEnemyGroup, myPlayfield.getTileMap().getTileGroup(), new EnemyToTileCollision());
		myPlayfield.addCollisionGroup(myMarioGroup, myEnemyGroup, new MarioToEnemyCollision());
		
		myTimer = new Timer(FREQ_ENEMIES);
		
		
	}
	/**
	 * 
	 * @return - an int representing the number of active enemies remaining.
	 */
	
	public int getEnemiesRemaining() {
		return myEnemyGroup.getSize();
	}

	/**
	 * Method extended from GameState class used to initialize Levels.
	 * 
	 */
	
	public void initialize() {
		myLevels = new ArrayList<MarioLevel>();
	}
	
	/**
	 * Main render method that renders the backgrounds and playfield.
	 * 
	 */
	
	public void render(Graphics2D g) {
		super.render(g);
        myPlayfield.setBackground(myMarioBackground);
		myPlayfield.render(g);
	}

}
