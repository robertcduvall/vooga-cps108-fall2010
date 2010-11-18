package vooga.games.galaxyinvaders;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.Sprite;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.resource.random.Randomizer;
import vooga.engine.resource.random.RandomizerException;
import vooga.engine.state.GameState;

public class GalaxyGameState extends GameState{

	private static final int MOVE_DISTANCE = 5;
	private static final int TOTAL_LEVELS = 3;
	private static final int MAX_ALLOWED_ENEMY_YPOS = 700;
	private static final int FIRST_BLOCKADE_XPOS = 100;
	private static final int BLOCKADE_YPOS = 600;
	private static final int INCREMENT_BLOCKADE_XPOS = 200;
	private static final double ITEM_SPEED = 0.1;
	private static final double ENEMY_BOMB_SPEED = 0.5;
	private static int ITEM_FREQUENCY = 7;
	private static int BOMB_FREQUENCY = 10;
	
	private PlayField playfield;
	private Game game;
	private Control shipControl;
	private Control gameControl;
	private LevelManager levelManager;
	private Ship ship;
	
	private SpriteGroup torpedos;
	private SpriteGroup enemyTorpedos;
	private SpriteGroup enemies;
	private SpriteGroup players;
	private SpriteGroup items;
	private SpriteGroup blockades;
	
	private CollisionManager torpedoCollider;
	private CollisionManager torpedoPlayerCollider;
	private CollisionManager itemPlayerCollider;
	private CollisionManager torpedoBlockCollider;
	
	public GalaxyGameState(Game g, PlayField pf){
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
		spawnBombs();
		spawnPowerUps();
	}

	@Override
	public void initialize() {
		initSpriteGroups();
		initControls();
		initColliders();
		initBlocks();
		initPlayField();
		initLevels();
	}
	
	private void initSpriteGroups() {
		items = new SpriteGroup("items");
		torpedos = new SpriteGroup("shots");
		enemies = new SpriteGroup("enemies");
		players = new SpriteGroup("players");
		blockades = new SpriteGroup("blockades");
		enemyTorpedos = new SpriteGroup("enemyTorpedos");
		ship = new Ship(torpedos);
		players.add(ship);
	}
	
	private void initControls(){
		shipControl = new KeyboardControl(ship, game);
		shipControl.setParams(KeyEvent.VK_LEFT, new Class[]{int.class});
		shipControl.addInput(KeyEvent.VK_LEFT, "moveLeft", "Ship", -MOVE_DISTANCE);
		shipControl.addInput(KeyEvent.VK_RIGHT, "moveRight", "Ship", MOVE_DISTANCE);
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
			BlockadeSprite b = new BlockadeSprite("", "default", new Sprite(Resources.getImage("barrier"), i, BLOCKADE_YPOS));
			blockades.add(b);
		}
	}
	
	private void initPlayField(){
		playfield.addGroup(items);
		playfield.addGroup(torpedos);
		playfield.addGroup(enemies);
		playfield.addGroup(blockades);
		playfield.addGroup(players);
		playfield.addGroup(enemyTorpedos);
		playfield.addGroup(overlayTracker.getOverlayGroups().get(2));
		playfield.addCollisionGroup(torpedos, enemies, torpedoCollider);
        playfield.addCollisionGroup(enemyTorpedos, players, torpedoPlayerCollider);
        playfield.addCollisionGroup(items, players, itemPlayerCollider);
        playfield.addCollisionGroup(enemyTorpedos, blockades, torpedoBlockCollider);
	}

	private void initLevels() {
		levelManager = new GalaxyLevelManager();
		levelManager.addLevels(Resources.getString("levelFilesDirectory"),new File(Resources.getString("levelNamesFile")));
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
		Sprite temp = new Sprite(Resources.getImage("health"), game.getWidth()/2, 0);
		temp.setVerticalSpeed(ITEM_SPEED);
		items.add(temp);
	}
	
	private void spawnBombs() {
		if(getRandomSeed(1000)<BOMB_FREQUENCY) {
			spawnEnemyBomb();
		}
	}
	
	
	private void spawnEnemyBomb() {
		int enemySeed;
		Sprite[] enemySprites = (Sprite[]) enemies.getSprites();
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
	
	private void checkLevel() {
		if(levelManager.getCurrentLevel()==0){
				loadLevel();
		}
		
		for(com.golden.gamedev.object.Sprite e : enemies.getSprites()) {
			if(e.isActive()) {
				break;
			}
			else {
				if(levelManager.getCurrentLevel()<TOTAL_LEVELS) {
					enemies.clear();
					loadLevel();
				}
				else {
					((DropThis) game).gameOver();
				}
			}
		}
	}
	
	private void loadLevel(){
		SpriteGroup temp = levelManager.loadNextLevel().getGroup("enemies");
		for(com.golden.gamedev.object.Sprite s : temp.getSprites()) {
			if(s!=null) {
				enemies.add(s);
			}
		}
	}
	

	private void checkDefeat() {
		for(com.golden.gamedev.object.Sprite enemy: enemies.getSprites()) {
			if(enemy!=null) {
				if (isAtBorder((Sprite) enemy)) {
					((DropThis) game).gameOver();
					break;
				}
			}
		}
		if(livesStat.getStat()<=0) {
			((DropThis) game).gameOver();
		}
	}
	
	private boolean isAtBorder(Sprite enemy){
		return enemy.getY() >= MAX_ALLOWED_ENEMY_YPOS;
	}
	
	private void clearEnemies(){
		enemies.clear();
	}
}
