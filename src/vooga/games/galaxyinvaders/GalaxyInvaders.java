package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.*;
import vooga.engine.player.control.*;
import vooga.engine.resource.*;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ColorBackground;


/**
 * The GalaxyInvaders class is the main Game class for Galaxy Invaders. It keeps track of the sprites, 
 * and has update called every turn by GoldenT, from which it calls update methods on all its sprite
 * groups. There are no command line arguments to run GalaxyInvaders.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class GalaxyInvaders extends Game {

	private static final int TOTAL_LEVELS = 3;
	private static final int MOVE_DISTANCE = 5;
	private static final double PLAYER_BOMB_SPEED = 0.8;
	private static final double ENEMY_BOMB_SPEED = 0.5;
	private static final double ITEM_SPEED = 0.1;
	private static final int MAX_ALLOWED_ENEMY_YPOS = 700;
	private static int ITEM_FREQUENCY = 7;
	private static int BOMB_FREQUENCY = 10;

	private Background bg;
	private PlayerSprite ship;
	private LevelManager levelManager;
	
	private SpriteGroup torpedos;
	private SpriteGroup enemyTorpedos;
	private SpriteGroup enemies;
	private SpriteGroup players;
	private SpriteGroup items;
	private SpriteGroup blockades;
	private SpriteGroup pauseMenu;
	private SpriteGroup gameOverMenu;
	
	private CollisionManager torpedoCollider;
	private CollisionManager torpedoPlayerCollider;
	private CollisionManager itemPlayerCollider;
	private CollisionManager torpedoBlockCollider;
	
	private GameState play;
	private GameState pause;
	private GameState gameOver;
	private GameStateManager gameStateManager;
	
	private OverlayTracker overlayTracker;
	protected Stat<Integer> myScore;
	protected Stat<Integer> myLives;

	/**
	 * Method inherited from Game. Initializes the game state and all the sprites in the game.
	 */
	public void initResources() {
		ResourceHandler.setGame(this);
		try {
			ResourceHandler.loadFile("vooga/games/galaxyinvaders/resources/resourcelist.txt");
		} catch (IOException e1) {
			System.out.print("File cannot be found.");
		}
		bg = new ColorBackground(Color.BLACK, 700, 800);
		ship = new PlayerSprite("p1", "default", new Sprite(ResourceHandler.getImage("ship"), getWidth()/2, getHeight()-100));
		
		initializeSpriteGroups();
		initializeOverlays();
		initializeBlocks();
		initializeLevels();
		initializeColliders();
		initializeGameStates();
		gameStateManager.switchTo(pause);

		players.add(ship);
	}

	/**
	 * Rendering method for GoldenT
	 * 
	 */
	public void render(Graphics2D g) {
		bg.render(g);
		gameStateManager.render(g);
	}

	/**
	 * This method is called every turn by the game engine. It determines whether
	 * bombs or powerups should be dropped, and updates all the sprite groups. It
	 * also checks to see if the game is over, and ends it if it is.
	 */
	public void update(long time) {

		gameStateManager.update(time);
		bg.update(time);
		
		checkLevel();
		checkCollisions();
		
		spawnBombs();
		spawnPowerUps();

		checkDefeat();
		checkButtons();

	}
	
	/**
	 * This method increases the player's score by a certain amount
	 * 
	 * @param score the amount by which to increase the score
	 */
	public void increasePlayerScore(int score) {
		myScore.setStat(myScore.getStat() + score);
	}

	/**
	 * Java main method
	 * 
	 * @param args do nothing
	 */
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GalaxyInvaders(), new Dimension(700,800), false);
		game.start();
	}

	
	
	private void initializeSpriteGroups() {
		items = new SpriteGroup("items");
		torpedos = new SpriteGroup("shots");
		enemies = new SpriteGroup("enemies");
		players = new SpriteGroup("players");
		blockades = new SpriteGroup("blockades");
		enemyTorpedos = new SpriteGroup("enemyTorpedos");
		pauseMenu = new SpriteGroup("pauseMenu");
		gameOverMenu = new SpriteGroup("gameOverMenu");
	}

	private void initializeOverlays() {
		overlayTracker = OverlayCreator.createOverlays(ResourceHandler.getMapping("overlays"));
		myLives = overlayTracker.getStats().get(0);
		myScore = overlayTracker.getStats().get(1);
	}

	private void initializeBlocks() {
		for(int i = 100; i<getWidth(); i+=200) {
			BlockadeSprite b = new BlockadeSprite("", "default", new Sprite(ResourceHandler.getImage("barrier"), i, 600));
			blockades.add(b);
		}
	}

	private void initializeLevels() {
		Properties propertiesFile = new Properties();
		try {
			propertiesFile.load(new FileInputStream("src/vooga/games/galaxyinvaders/resources/Directories.properties"));
		}
		catch(IOException e)
		{
			System.out.println(".properties file not found!");
		}
		
		String levelFilesDirectory = propertiesFile.getProperty("levelFilesDirectory");
		String levelNamesFile = propertiesFile.getProperty("levelNamesFile");
		levelManager = new GalaxyLevelManager();
		levelManager.addLevels(levelFilesDirectory,new File(levelNamesFile));
	}

	private void initializeColliders() {
		torpedoCollider = new TorpedoEnemyCollider(this);
		torpedoCollider.setCollisionGroup(torpedos, enemies);
		torpedoPlayerCollider = new TorpedoPlayerCollider(this);
		torpedoPlayerCollider.setCollisionGroup(enemyTorpedos, players);
		itemPlayerCollider = new ItemPlayerCollider(this);
		itemPlayerCollider.setCollisionGroup(items, players);
		torpedoBlockCollider = new TorpedoBlockCollider();
		torpedoBlockCollider.setCollisionGroup(enemyTorpedos, blockades);
	}

	private void initializeGameStates() {
		gameStateManager = new GameStateManager();
		play = new GameState();
		play.addGroup(items);
		play.addGroup(torpedos);
		play.addGroup(enemies);
		play.addGroup(blockades);
		play.addGroup(players);
		play.addGroup(enemyTorpedos);
		play.addGroup(overlayTracker.getOverlayGroups().get(2));
		gameStateManager.addGameState(play);
		pause = new GameState();
		pause.addGroup(pauseMenu);
		gameStateManager.addGameState(pause);
		gameOver = new GameState();
		gameOver.addGroup(gameOverMenu);
		gameStateManager.addGameState(gameOver);
		initializePause();
		initializeGameOver();
	}
	
	private void initializePause() {
			SpriteGroup strings = overlayTracker.getOverlayGroups().get(0);
			Sprite[] lines = strings.getSprites();
			int size = strings.getSize();
			for (int i = 0; i<size; i++)
			{
				OverlayString oString = (OverlayString) lines[i];
				oString.setColor(Color.WHITE);
				pauseMenu.add(oString);
			}			
	}

	private void initializeGameOver() {
			SpriteGroup strings = overlayTracker.getOverlayGroups().get(1);
			Sprite[] lines = strings.getSprites();
			int size = strings.getSize();
			for (int i = 0; i<size; i++)
			{
				OverlayString oString = (OverlayString) lines[i];
				oString.setColor(Color.WHITE);
				gameOverMenu.add(oString);
			}			
	}

	
	
	private void checkCollisions() {
		torpedoCollider.checkCollision();
		torpedoPlayerCollider.checkCollision();
		itemPlayerCollider.checkCollision();
		torpedoBlockCollider.checkCollision();
	}

	private void checkLevel() {
		if(levelManager.getCurrentLevel()==0){
			SpriteGroup temp = levelManager.loadNextLevel().getGroup("enemies");
			for(Sprite s : temp.getSprites()) {
				if(s!=null) {
					enemies.add(s);
				}
			}
		}
		
		for(Sprite e : enemies.getSprites()) {
			if(e.isActive()) {
				break;
			}
			else {
				if(levelManager.getCurrentLevel()<TOTAL_LEVELS) {
					enemies.clear();
					SpriteGroup temp = levelManager.loadNextLevel().getGroup("enemies");
					for(Sprite s : temp.getSprites()) {
						if(s!=null) {
							enemies.add(s);
						}
					}
				}
				else {
					gameStateManager.switchTo(gameOver);
				}
			}
		}
	}
	
	private void checkDefeat() {
		for(Sprite enemy: enemies.getSprites()) {
			if(enemy!=null) {
				if (isAtBorder(enemy)) {
					gameStateManager.switchTo(gameOver);
					break;
				}
			}
		}
		if(myLives.getStat()<=0) {
			gameStateManager.switchTo(gameOver);
		}

	}

	private void spawnPowerUps() {
		//this randomly determines whether a health powerup will be dropped
		//on this turn. the constant ITEM_FREQUENCY can be changed to change 
		//the rate of powerups dropped
		int itemSeed;
		try {
			itemSeed = Randomizer.nextInt(10000);
		} catch (RandomizerException e) {
			e.printStackTrace();
			itemSeed = 0;
		}

		if(itemSeed<ITEM_FREQUENCY) {
			spawnHealth();
		}
	}

	private void spawnBombs() {
		int bombSeed;
		try {
			bombSeed = Randomizer.nextInt(1000);
		} catch (RandomizerException e) {
			e.printStackTrace();
			bombSeed = 0;
		}
		if(bombSeed<BOMB_FREQUENCY) {
			spawnEnemyBomb();
		}
	}

	private void spawnHealth() {
		Sprite temp = new Sprite(ResourceHandler.getImage("health"), getWidth()/2, 0);
		temp.setVerticalSpeed(ITEM_SPEED);
		items.add(temp);
	}

	private void spawnEnemyBomb() {
		int enemySeed;
		Sprite[] enemySprites = enemies.getSprites();
		int count = 0;
		for(Sprite s : enemySprites) {
			if(s!=null) {
				if(s.isActive())
					count++;
			}
		}
		if(count>0) {
			try {
				enemySeed = Randomizer.nextInt(count);
			} catch (RandomizerException e) {
				e.printStackTrace();
				enemySeed = 0;
			}
			EnemySprite enemy = (EnemySprite) enemySprites[enemySeed];
			Sprite temp = new Sprite(ResourceHandler.getImage("torpedo"), enemy.getX()+25, enemy.getY()+30);
			temp.setSpeed(0, ENEMY_BOMB_SPEED);
			enemyTorpedos.add(temp);
		}
	}
	
	private void checkButtons() {
		if(keyDown(KeyEvent.VK_LEFT)) {
			if(ship.getX()>0-15)  moveLeft();
		}
		if(keyDown(KeyEvent.VK_RIGHT)) {
			if(ship.getX()<getWidth()-45)   moveRight();
		}
		if(keyPressed(KeyEvent.VK_SPACE)) {
			fire();
		}

		// this is a cheat code. it kills all the enemies on the screen and advances you to the next level
		if(keyPressed(KeyEvent.VK_T)) {
			enemies.clear();
		}

		if(keyPressed(KeyEvent.VK_P)) {
			gameStateManager.toggle(pause);
			gameStateManager.toggle(play);
		}
		
		if(keyPressed(KeyEvent.VK_R)) {
			this.finish();
			GalaxyInvaders.main(null);
		}
	}

	
	private void moveLeft() {
		ship.move(-MOVE_DISTANCE, 0);
	}

	private void moveRight() {
		ship.move(MOVE_DISTANCE, 0);
	}

	private void fire() {
		Sprite temp = new Sprite(ResourceHandler.getImage("torpedo"), ship.getX()+25, ship.getY()-35);
		temp.setSpeed(0, -PLAYER_BOMB_SPEED);
		torpedos.add(temp);
	}

	private boolean isAtBorder(Sprite enemy){
		return enemy.getY() >=  MAX_ALLOWED_ENEMY_YPOS;
	}

}
