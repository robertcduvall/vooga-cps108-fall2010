package vooga.games.grandius.states;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayPanel;
import vooga.engine.overlay.OverlayStatImage;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.collisions.BasicCollision;
import vooga.games.grandius.enemy.common.Zipster;
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
	
	//private boolean reacherShieldsDepleted;
	private boolean skipLevel = false;
	
	private LevelManager myLevelManager;
	private OverlayTracker overlayTracker;
	private OverlayPanel overlayPanel;
	private OverlayStatImage livesIcon;
	
	private KeyboardControl playerControl;
	private static final String PLAYER_CLASS = Resources.getString("playerClass");
	private static final double PLAYER_SPEED = Resources.getDouble("playerSpeed");
	
	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;
	}
	
	@Override
	public void initialize() {
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		PlayField newField = myLevelManager.loadFirstLevel();
		player = (Player) newField.getGroup("playerGroup").getSprites()[0];
		initControls();
		initEvents();
		//TODO create PlayField from level files
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
		//this.addUpdatePlayField(testField);
		//this.addRenderPlayField(testField);
		initOverlays();
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
//		player = new Player("alivePlayer");
		
		player.setActive(true);
		initControls();
		playerGroup.add(player);	
		return playerGroup;
	}
	
//	/**
//	 * Creates the different SpriteGroups and registers them to the PlayState's PlayField. 
//	 * Also adds the necessary SpriteGroup entries to the spriteGroupSpeedMap.
//	 */
//	private PlayField initSpriteGroups() {
//		PlayField newField = new PlayField();
//		backgroundGroup =       		new SpriteGroup("Background");
//		backgroundGroup.add(new BetterSprite(Resources.getImage("backgroundImage")));
//		newField.addGroup(backgroundGroup);
//		playerGroup =            		new SpriteGroup("Player");
//		playerGroup = addPlayer(playerGroup);
//		newField.addGroup(playerGroup);
//		projectileGroup =               newField.addGroup(new SpriteGroup("Projectile"));
//		enemyProjectileGroup =  		newField.addGroup(new SpriteGroup("EnemyProjectile"));
//		//TODO the createEnemiesGroup() method should be replaced by the use of the level XML file
//		enemyGroup =                    newField.addGroup(new SpriteGroup("Enemies"));
//		bossPartGroup =                 newField.addGroup(new SpriteGroup("BossPart"));
//		bossGroup =                     newField.addGroup(new SpriteGroup("Boss"));
//		missileGroup =                  newField.addGroup(new SpriteGroup("Missile"));
//		blackHoleGroup =                newField.addGroup(new SpriteGroup("BlackHole"));
//		cometsGroup = 					newField.addGroup(createCometsGroup());
//		
//		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>(); //TODO get rid of the negative signs?
//		spriteGroupSpeedMap.put(projectileGroup,        Resources.getDouble("projectileSpeed"));
//		spriteGroupSpeedMap.put(enemyProjectileGroup,   -Resources.getDouble("projectileSpeed"));
//		spriteGroupSpeedMap.put(enemyGroup,             Resources.getDouble("enemySpeed"));
//		spriteGroupSpeedMap.put(bossPartGroup,          -Resources.getDouble("bossPartSpeed"));
//		spriteGroupSpeedMap.put(bossGroup,              -Resources.getDouble("bossSpeed"));
//		spriteGroupSpeedMap.put(missileGroup,           Resources.getDouble("projectileSpeed"));
//		
//		return newField;
//	}
	
	//Old test playfield method
//	/**
//	 * Creates the different SpriteGroups and registers them to the PlayState's PlayField. 
//	 * Also adds the necessary SpriteGroup entries to the spriteGroupSpeedMap.
//	 */
//	private PlayField initPlayField() {
//		PlayField newField = new PlayField();
//		backgroundGroup =       		new SpriteGroup("Background");
//		backgroundGroup.add(new BetterSprite(Resources.getImage("backgroundImage")));
//		newField.addGroup(backgroundGroup);
//		playerGroup =            		new SpriteGroup("Player");
//		playerGroup = addPlayer(playerGroup);
//		newField.addGroup(playerGroup);
//		projectileGroup =               newField.addGroup(new SpriteGroup("Projectile"));
//		enemyProjectileGroup =  		newField.addGroup(new SpriteGroup("EnemyProjectile"));
//		//TODO the createEnemiesGroup() method should be replaced by the use of the level XML file
//		enemyGroup =                    newField.addGroup(createEnemiesGroup());
//		bossPartGroup =                 newField.addGroup(new SpriteGroup("BossPart"));
//		bossGroup =                     newField.addGroup(new SpriteGroup("Boss"));
//		missileGroup =                  newField.addGroup(new SpriteGroup("Missile"));
//		blackHoleGroup =                newField.addGroup(new SpriteGroup("BlackHole"));
//		cometsGroup = 					newField.addGroup(createCometsGroup());
//		
//		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>(); //TODO get rid of the negative signs?
//		spriteGroupSpeedMap.put(projectileGroup,        Resources.getDouble("projectileSpeed"));
//		spriteGroupSpeedMap.put(enemyProjectileGroup,   -Resources.getDouble("projectileSpeed"));
//		spriteGroupSpeedMap.put(enemyGroup,             Resources.getDouble("enemySpeed"));
//		spriteGroupSpeedMap.put(bossPartGroup,          -Resources.getDouble("bossPartSpeed"));
//		spriteGroupSpeedMap.put(bossGroup,              -Resources.getDouble("bossSpeed"));
//		spriteGroupSpeedMap.put(missileGroup,           Resources.getDouble("projectileSpeed"));
//		
//		return newField;
//	}
	
	public void initControls() {
		playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_UP, "moveUp", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_DOWN, "moveDown", PLAYER_CLASS);
	}
	
	private SpriteGroup createEnemiesGroup() {
		SpriteGroup enemiesGroup = new SpriteGroup("Enemies");
		Zipster zipster = new Zipster(400, 200);
		enemiesGroup.add(zipster);
		return enemiesGroup;
	}
	
	/**
	 * Creates background "comets" to provide illusion of movement through space.
	 */
	private SpriteGroup createCometsGroup() {
		SpriteGroup cometGroup = new SpriteGroup("Comets");
		for (int j = 0; j < Resources.getInt("numComets"); j++) {
			Random valX = new Random();
			Random valY = new Random();
			double x = valX.nextDouble();
			double y = valY.nextDouble();
			BetterSprite comet = new BetterSprite(Resources.getImage("cometImage"),
					(x * Resources.getInt("cometX")), 
					(y * Resources.getInt("cometY")));
			comet.setHorizontalSpeed(Resources.getDouble("cometVX"));
			cometGroup.add(comet);
		}
		return cometGroup;
	}
	
	/**
	 * Initializes Overlays - Lives, Cash, and Score.
	 */
	private void initOverlays() {
		OverlayCreator.setGame(myGame);
		overlayTracker = OverlayCreator.createOverlays(Resources.getString("overlayPath"));
//		player.setLives(overlayTracker.getStat("livesStat", new Integer(0)));
//		player.setScore(overlayTracker.getStat("scoreStat", new Integer(0)));
//		player.setCash(overlayTracker.getStat("cashStat", new Integer(0)));
		PlayField newField = new PlayField();
		newField.addGroup(overlayTracker.getOverlayGroup("first"));
		this.getUpdateField().add(newField);
		this.getRenderField().add(newField);
	}
	
	//TODO implement this method
//	private void checkCheats() {
//		if (keyPressed(KeyEvent.VK_ENTER)) {
//			JFrame frame = new JFrame();
//			String userInput = (String) JOptionPane.showInputDialog(frame,
//					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
//			if (userInput.equals(Resources.getString("invincibility")))
//				player.setInvincible();
//			else if (userInput.equals(Resources.getString("skipLevel")))
//				player.skipLevel();
//			else if (userInput.equals(Resources.getString("extraPoints")))
//				player.updateScore(1000000);
//			else if (userInput.equals(Resources.getString("extraCash")))
//				player.updateCash(5000);
//			else if (userInput.equals(Resources.getString("activateMissile")))
//				player.setMissileActive();
//		}
//	}
	
	//TODO this method is being used for collision handling also
	public Player getPlayer() {
		return player;
	}
		
}