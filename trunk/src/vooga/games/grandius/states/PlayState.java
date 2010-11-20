package vooga.games.grandius.states;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.collisions.BasicCollision;
import vooga.games.grandius.events.FireBlackHoleEvent;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.games.grandius.events.FireVerticalEvent;

import com.golden.gamedev.object.SpriteGroup;

public class PlayState extends GameState {
	
	private static DropThis myGame;
	
	//private PlayField myPlayField;
	private Map<SpriteGroup, Double> spriteGroupSpeedMap;
	private SpriteGroup playerGroup;
	private SpriteGroup projectileGroup;
	private SpriteGroup enemyProjectileGroup;
	private SpriteGroup enemyGroup;
	private SpriteGroup bossPartGroup;
	private SpriteGroup bossGroup;  
	private SpriteGroup missileGroup;
	private SpriteGroup blackHoleGroup;
	private SpriteGroup cometsGroup;
	private SpriteGroup backgroundGroup; 
	private Player player;
	
	private EventPool eventPool;
	
	private boolean reacherShieldsDepleted;
	private boolean skipLevel = false;
	
	private LevelManager myLevelManager;
	//private OverlayTracker overlayTracker;
	private KeyboardControl playerControl;
	private static final String PLAYER_CLASS = Resources.getString("playerClass");
	private static final double PLAYER_SPEED = Resources.getDouble("playerSpeed");
	
	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;
	}
	
	@Override
	public void initialize() {
		OverlayCreator.setGame(myGame);
		//overlayTracker = OverlayCreator.createOverlays(Resources.getString("OverlayPath"));
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		PlayField testField = initPlayField(); //Important that background group is always created first.
		initEvents();
		//TODO create PlayField from level files
		//PlayField newField = myLevelManager.loadFirstLevel();
		//this.addUpdatePlayField(newField);
		//this.addRenderPlayField(newField);
		this.addUpdatePlayField(testField);
		this.addRenderPlayField(testField);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		playerControl.update();
		eventPool.checkEvents();
//		updateScreenSprites(); //TODO is this method needed?
		//updateEntities();
		//checkBossParts();
		//checkLevelComplete();
	}
	
	/**
	 * Initializes the specific Events handled in the Grandius PlayState.
	 */
	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new FireHorizontalEvent(myGame, player, this));
		eventPool.addEvent(new FireVerticalEvent(myGame, player, this));
		eventPool.addEvent(new FireMissileEvent(myGame, player, this));
		eventPool.addEvent(new FireBlackHoleEvent(myGame, player, this));
	}
	
	/**
	 * Adds the Player to the PlayerGroup SpriteGroup.
	 */
	private SpriteGroup addPlayer(SpriteGroup playerGroup) {
		Stat<Integer> initLives = new Stat<Integer>(3);// = overlayTracker.getStat("initLives", new Integer(0));
		Stat<Integer> initCash = new Stat<Integer>(0);// = overlayTracker.getStat("initCash", new Integer(0));
		Stat<Integer> initScore = new Stat<Integer>(0);// = overlayTracker.getStat("initScore", new Integer(0));
		BetterSprite playerSprite = new BetterSprite(
				Resources.getImage("playerImage"),
				Resources.getInt("playerInitialX"),
				Resources.getInt("playerInitialY"));
		player = new Player("alive", playerSprite, initLives, initCash, initScore);
		player.setActive(true);
		initControls();
		playerGroup.add(player);	
		return playerGroup;
	}
	
	/**
	 * Creates the different SpriteGroups and registers them to the PlayState's PlayField. 
	 * Also adds the necessary SpriteGroup entries to the spriteGroupSpeedMap.
	 */
	private PlayField initPlayField() {
		PlayField newField = new PlayField();
		backgroundGroup =       		new SpriteGroup("Background");
		backgroundGroup.add(new BetterSprite(Resources.getImage("backgroundImage")));
		newField.addGroup(backgroundGroup);
		playerGroup =            		new SpriteGroup("Player");
		playerGroup = addPlayer(playerGroup);
		newField.addGroup(playerGroup);
		projectileGroup =               newField.addGroup(new SpriteGroup("Projectile"));
		enemyProjectileGroup =  		newField.addGroup(new SpriteGroup("EnemyProjectile"));
		enemyGroup =                    newField.addGroup(new SpriteGroup("Enemy"));
		bossPartGroup =                 newField.addGroup(new SpriteGroup("BossPart"));
		bossGroup =                     newField.addGroup(new SpriteGroup("Boss"));
		missileGroup =                  newField.addGroup(new SpriteGroup("Missile"));
		blackHoleGroup =                newField.addGroup(new SpriteGroup("BlackHole"));
		cometsGroup = createCometsGroup();
		
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>(); //TODO get rid of the negative signs?
		spriteGroupSpeedMap.put(projectileGroup,        Resources.getDouble("projectileSpeed"));
		spriteGroupSpeedMap.put(enemyProjectileGroup,   -Resources.getDouble("projectileSpeed"));
		spriteGroupSpeedMap.put(enemyGroup,             Resources.getDouble("enemySpeed"));
		spriteGroupSpeedMap.put(bossPartGroup,          -Resources.getDouble("bossPartSpeed"));
		spriteGroupSpeedMap.put(bossGroup,              -Resources.getDouble("bossSpeed"));
		spriteGroupSpeedMap.put(missileGroup,           Resources.getDouble("projectileSpeed"));
		
		return newField;
	}
	
	/**
	 * Adds the necessary CollisionGroups to the PlayState.
	 */
	public void initCollisions(List<BasicCollision> collisions) { //TODO find a way to make this method private?
		PlayField newField = new PlayField();
		newField.addCollisionGroup(playerGroup, enemyGroup, collisions.get(0));
		newField.addCollisionGroup(playerGroup, bossPartGroup, collisions.get(1));
		newField.addCollisionGroup(playerGroup, bossGroup, collisions.get(2));
		newField.addCollisionGroup(projectileGroup, enemyGroup, collisions.get(3));
		newField.addCollisionGroup(projectileGroup, bossPartGroup, collisions.get(4));
		newField.addCollisionGroup(playerGroup, enemyProjectileGroup, collisions.get(5));
		newField.addCollisionGroup(projectileGroup, bossGroup, collisions.get(6));
		newField.addCollisionGroup(missileGroup, enemyGroup, collisions.get(7));
		newField.addCollisionGroup(missileGroup, bossPartGroup, collisions.get(8));
		newField.addCollisionGroup(missileGroup, bossGroup, collisions.get(9));
		newField.addCollisionGroup(blackHoleGroup, enemyGroup, collisions.get(10));
		this.addUpdatePlayField(newField); //TODO right way to do this?
		this.addRenderPlayField(newField);
	}
	
	public void initControls() {
		playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_UP, "moveUp", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_DOWN, "moveDown", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_ALT, "shoot", PLAYER_CLASS);
	}
	
	/**
	 * Creates background "comet sprites" to provide illusion of movement through space.
	 */
	private SpriteGroup createCometsGroup() {
		SpriteGroup cometGroup = new SpriteGroup("Comets");
		for (int j = 0; j < Resources.getInt("numComets"); j++) {
			Random valX = new Random();
			Random valY = new Random();
			double x = valX.nextDouble();
			double y = valY.nextDouble();
			BetterSprite backgroundSprite = new BetterSprite(Resources.getImage("cometImage"),
					(x * Resources.getInt("cometX")), 
					(y * Resources.getInt("cometY")));
			backgroundSprite.setHorizontalSpeed(Resources.getDouble("cometVX"));
			cometGroup.add(backgroundSprite);
		}
		return cometGroup;
	}
		
}