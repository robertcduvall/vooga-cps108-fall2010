package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.*;
import vooga.engine.player.control.*;
import vooga.engine.resource.*;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

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
public class DropThis extends Game {


	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 800;
	private static final int TOTAL_LEVELS = 3;
	private static final int MOVE_DISTANCE = 5;
	private static final double PLAYER_BOMB_SPEED = 0.8;
	private static final double ENEMY_BOMB_SPEED = 0.5;
	private static final double ITEM_SPEED = 0.1;
	private static final int MAX_ALLOWED_ENEMY_YPOS = 700;
	private static final int FIRST_BLOCKADE_XPOS = 100;
	private static final int BLOCKADE_YPOS = 600;
	private static final int INCREMENT_BLOCKADE_XPOS = 200;
	private static int ITEM_FREQUENCY = 7;
	private static int BOMB_FREQUENCY = 10;

	private Background background;
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
	protected Stat<Integer> scoreStat;
	protected Stat<Integer> livesStat;

	/**
	 * Method inherited from Game. Initializes the game state and all the sprites in the game.
	 */
	public void initResources() {
		super.initResources();
		background = new ColorBackground(Color.BLACK, GAME_WIDTH, GAME_HEIGHT);
		ship = new PlayerSprite("p1", "default", new Sprite(Resources.getImage("ship"), getWidth()/2, getHeight()-100));
		
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
		background.render(g);
		gameStateManager.render(g);
	}

	/**
	 * This method is called every turn by the game engine. It determines whether
	 * bombs or powerups should be dropped, and updates all the sprite groups. It
	 * also checks to see if the game is over, and ends it if it is.
	 */
	public void update(long time) {
		gameStateManager.update(time);
		background.update(time);
		
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
		scoreStat.setStat(scoreStat.getStat() + score);
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
		overlayTracker = OverlayCreator.createOverlays(Resources.getString("overlays"));
		livesStat = overlayTracker.getStats().get(0);
		scoreStat = overlayTracker.getStats().get(1);
	}

	private void initializeBlocks() {
		for(int i = FIRST_BLOCKADE_XPOS; i<getWidth(); i+=INCREMENT_BLOCKADE_XPOS) {
			BlockadeSprite b = new BlockadeSprite("", "default", new Sprite(Resources.getImage("barrier"), i, BLOCKADE_YPOS));
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
		gameOver = new GameState();
		initializeMenus(pause, pauseMenu, 0);
		initializeMenus(gameOver, gameOverMenu, 1);
	}
	
	private void initializeMenus(GameState state, SpriteGroup menu, int overlayGroup){
		state.addGroup(menu);
		gameStateManager.addGameState(state);
		SpriteGroup strings = overlayTracker.getOverlayGroups().get(overlayGroup);
		Sprite[] lines = strings.getSprites();
		int size = strings.getSize();
		for (int i = 0; i<size; i++)
		{
			OverlayString oString = (OverlayString) lines[i];
			oString.setColor(Color.WHITE);
			menu.add(oString);
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
				loadLevel();
		}
		
		for(Sprite e : enemies.getSprites()) {
			if(e.isActive()) {
				break;
			}
			else {
				if(levelManager.getCurrentLevel()<TOTAL_LEVELS) {
					enemies.clear();
					loadLevel();
				}
				else {
					gameStateManager.switchTo(gameOver);
				}
			}
		}
	}
	
	private void loadLevel(){
		SpriteGroup temp = levelManager.loadNextLevel().getGroup("enemies");
		for(Sprite s : temp.getSprites()) {
			if(s!=null) {
				enemies.add(s);
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
		if(livesStat.getStat()<=0) {
			gameStateManager.switchTo(gameOver);
		}

	}

	private void spawnPowerUps() {
		//this randomly determines whether a health powerup will be dropped
		//on this turn. the constant ITEM_FREQUENCY can be changed to change 
		//the rate of powerups dropped
		if(getRandomSeed(10000)<ITEM_FREQUENCY) {
			spawnHealth();
		}
	}

	private void spawnBombs() {
		if(getRandomSeed(1000)<BOMB_FREQUENCY) {
			spawnEnemyBomb();
		}
	}

	private int getRandomSeed(int randomizer){
		int seed;
		try {
			seed = Randomizer.nextInt(randomizer);
		} catch (RandomizerException e) {
			e.printStackTrace();
			seed = 0;
		}
		return seed;
	}
	
	private void spawnHealth() {
		Sprite temp = new Sprite(Resources.getImage("health"), getWidth()/2, 0);
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
			Sprite temp = new Sprite(Resources.getImage("torpedo"), enemy.getX()+25, enemy.getY()+30);
			temp.setSpeed(0, ENEMY_BOMB_SPEED);
			enemyTorpedos.add(temp);
		}
	}
	
	private void checkButtons() {
		if(keyDown(KeyEvent.VK_LEFT)) {
			if(ship.getX()>0-15)  
				moveLeft(-MOVE_DISTANCE);
		}
		if(keyDown(KeyEvent.VK_RIGHT)) {
			if(ship.getX()<getWidth()-45)   
				moveRight(MOVE_DISTANCE);
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
			DropThis.main(null);
		}
	}

	
	private void moveLeft(int distance) {
		ship.move(distance, 0);
	}

	private void moveRight(int distance) {
		ship.move(distance, 0);
	}

	private void fire() {
		Sprite temp = new Sprite(Resources.getImage("torpedo"), ship.getX()+25, ship.getY()-35);
		temp.setSpeed(0, -PLAYER_BOMB_SPEED);
		torpedos.add(temp);
	}

	private boolean isAtBorder(Sprite enemy){
		return enemy.getY() >= MAX_ALLOWED_ENEMY_YPOS;
	}
	

	/**
	 * Java main method
	 * 
	 * @param args do nothing
	 */
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(GAME_WIDTH, GAME_HEIGHT), false);
		game.start();
	}

}