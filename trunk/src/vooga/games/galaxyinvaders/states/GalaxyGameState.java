package vooga.games.galaxyinvaders.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.resource.random.Randomizer;
import vooga.engine.resource.random.RandomizerException;
import vooga.engine.state.GameState;
import vooga.games.galaxyinvaders.DropThis;
import vooga.games.galaxyinvaders.GalaxyLevelManager;
import vooga.games.galaxyinvaders.collisions.ItemPlayerCollider;
import vooga.games.galaxyinvaders.collisions.TorpedoBlockCollider;
import vooga.games.galaxyinvaders.collisions.TorpedoEnemyCollider;
import vooga.games.galaxyinvaders.collisions.TorpedoPlayerCollider;
import vooga.games.galaxyinvaders.sprites.BlockadeSprite;
import vooga.games.galaxyinvaders.sprites.EnemySprite;
import vooga.games.galaxyinvaders.sprites.Ship;

public class GalaxyGameState extends GameState{

	private static final int MOVE_DISTANCE = 5;
	private static final int TOTAL_LEVELS = 3;
	private static final int MAX_ALLOWED_ENEMY_YPOS = 700;
	private static final int FIRST_BLOCKADE_XPOS = 100;
	private static final int BLOCKADE_YPOS = 600;
	private static final int INCREMENT_BLOCKADE_XPOS = 200;
	private static final double ITEM_SPEED = 0.1;
	private static int ITEM_FREQUENCY = 7;
	private static int BOMB_FREQUENCY = 10;
	
	private PlayField playfield;
	private DropThis game;
	private Control shipControl;
	private Control gameControl;
	private LevelManager levelManager;
	private Ship ship;
	
	private SpriteGroup enemyTorpedos;
	private SpriteGroup enemies;
	private SpriteGroup players;
	private SpriteGroup items;
	private SpriteGroup blockades;
	
	private CollisionManager torpedoCollider;
	private CollisionManager torpedoPlayerCollider;
	private CollisionManager itemPlayerCollider;
	private CollisionManager torpedoBlockCollider;
	
	private OverlayTracker overlayTracker;
	protected Stat<Integer> scoreStat;
	public Stat<Integer> livesStat;
	
	public GalaxyGameState(DropThis g, PlayField pf){
		playfield = pf;
		game = g;
	}
	
	public PlayField getPlayField(){
		return playfield;
	}
	
	public void render(Graphics2D g){
		playfield.render(g);
	}
	
	public void update(long time){
		playfield.update(time);
		shipControl.update();
		gameControl.update();
		
		checkLevel();
		checkDefeat();
		spawnBombs();
		spawnPowerUps();
	}

	@Override
	public void initialize() {
		initSpriteGroups();
		initControls();
		initColliders();
		initBlocks();
		initOverlays();
		initPlayField();
		initLevels();
	}
	
	private void initSpriteGroups() {
		items = new SpriteGroup("items");
		enemies = new SpriteGroup("enemies");
		players = new SpriteGroup("players");
		blockades = new SpriteGroup("blockades");
		enemyTorpedos = new SpriteGroup("enemyTorpedos");
		ship = new Ship();
		players.add(ship);
	}
	
	private void initControls(){
		shipControl = new KeyboardControl(ship, game);
		shipControl.addInput(KeyEvent.VK_LEFT, "moveLeft", "Ship", new Class[] {int.class});
		shipControl.setParams(KeyEvent.VK_LEFT, MOVE_DISTANCE);
		shipControl.addInput(KeyEvent.VK_RIGHT, "moveRight", "Ship", new Class[] {int.class});
		shipControl.setParams(KeyEvent.VK_RIGHT, MOVE_DISTANCE);
		shipControl.addInput(KeyEvent.VK_SPACE, "fire", "Ship");
		
		gameControl = new KeyboardControl(game);
		// this is a cheat code. it kills all the enemies on the screen and advances you to the next level
		gameControl.addInput(KeyEvent.VK_T, "clearEnemies", "GalaxyGameState");
		gameControl.addInput(KeyEvent.VK_P, "toggle", "DropThis");
		gameControl.addInput(KeyEvent.VK_R, "startNewGame", "DropThis");
	}
	
	private void initColliders() {
		torpedoCollider = new TorpedoEnemyCollider(game);
		torpedoPlayerCollider = new TorpedoPlayerCollider(game);
		itemPlayerCollider = new ItemPlayerCollider(game);
		torpedoBlockCollider = new TorpedoBlockCollider();
	}
	
	private void initBlocks() {
		for(int i = FIRST_BLOCKADE_XPOS; i<game.getWidth(); i+=INCREMENT_BLOCKADE_XPOS) {
			BlockadeSprite b = new BlockadeSprite("default", new BetterSprite(Resources.getImage("barrier"), i, BLOCKADE_YPOS));
			blockades.add(b);
		}
	}
	
	private void initOverlays() {
		overlayTracker = OverlayCreator.createOverlays(Resources.getString("overlays"));
		livesStat = overlayTracker.getStat("lives", new Integer(0));
		scoreStat = overlayTracker.getStat("score", new Integer(0));
	}
	
	private void initPlayField(){
		playfield.addGroup(items);
		playfield.addGroup(enemies);
		playfield.addGroup(blockades);
		playfield.addGroup(players);
		playfield.addGroup(enemyTorpedos);
		playfield.addGroup(overlayTracker.getOverlayGroup("stats"));
		playfield.addCollisionGroup(ship.getTorpedoGroup(), enemies, torpedoCollider);
        playfield.addCollisionGroup(enemyTorpedos, players, torpedoPlayerCollider);
        playfield.addCollisionGroup(items, players, itemPlayerCollider);
        playfield.addCollisionGroup(enemyTorpedos, blockades, torpedoBlockCollider);
	}
	
	private void initLevels() {
		levelManager = new GalaxyLevelManager(game);
		levelManager.makeLevels(Resources.getString("levelFilesDirectory"), Resources.getString("levelNamesFile"));
	}
	
	private void spawnPowerUps() {
		//this randomly determines whether a health powerup will be dropped
		//on this turn. the constant ITEM_FREQUENCY can be changed to change 
		//the rate of powerups dropped
		if(getRandomSeed(10000)<ITEM_FREQUENCY) {
			spawnHealth();
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
		BetterSprite temp = new BetterSprite(Resources.getImage("health"), game.getWidth()/2, 0);
		temp.setVerticalSpeed(ITEM_SPEED);
		items.add(temp);
	}
	
	private void spawnBombs() {
		if(getRandomSeed(1000)<BOMB_FREQUENCY) {
			spawnEnemyBombs();
		}
	}
	
	
	private void spawnEnemyBombs() {
		int enemySeed;
		EnemySprite[] enemySprites = (EnemySprite[]) enemies.getSprites();
		int count = 0;
		for(EnemySprite s : enemySprites) {
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
			EnemySprite enemy = enemySprites[enemySeed];
			enemyTorpedos.add(enemy.spawnBomb());
		}
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
					game.gameOver();
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
				if (isAtBorder((BetterSprite) enemy)) {
					game.gameOver();
					break;
				}
			}
		}
		if(livesStat.getStat()<=0) {
			game.gameOver();
		}
	}
	
	private boolean isAtBorder(BetterSprite enemy){
		return enemy.getY() >= MAX_ALLOWED_ENEMY_YPOS;
	}
	
	private void clearEnemies(){
		enemies.clear();
	}
	
	/**
	 * This method changes the player's score by a certain amount. To decrease
	 * score, enter a negative score as a parameter
	 * 
	 * @param score the amount by which to change the score
	 */
	public void changePlayerScore(int score) {
		scoreStat.setStat(scoreStat.getStat() + score);
	}
	
	/**
	 * This method changes the player's lives by a certain amount. To decrease
	 * lives, enter a negative score as a parameter
	 * 
	 * @param lives the amount by which to change the lives
	 */
	public void changePlayerLives(int lives) {
		livesStat.setStat(livesStat.getStat() + lives);
	}
}
