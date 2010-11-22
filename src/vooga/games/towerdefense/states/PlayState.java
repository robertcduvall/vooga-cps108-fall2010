package vooga.games.towerdefense.states;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.control.MouseControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.towerdefense.actors.EnemyGenerator;
import vooga.games.towerdefense.actors.MediumEnemyGenerator;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.collisions.ShotToEnemyCollision;
import vooga.games.towerdefense.events.BuildEnemyEvent;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.events.EnemyHitEvent;
import vooga.games.towerdefense.events.FindTargetEvent;
import vooga.games.towerdefense.events.ShootEvent;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

/**
 * The main state of the game in which the user is actively
 * playing the game. Due to the LevelParser systems currently 
 * not being functional, we used hardcoded level loading rather 
 * than xml based level loading so that we could at least get 
 * something on the screen. As a result, only the one hardcoded 
 * level is currently implemented. 
 * 
 * @author Daniel Koverman
 *
 */
public class PlayState extends GameState{	
	
	private PlayField myPlayField;
	private LevelManager myLevelManager;
	private OverlayTracker myTracker;
	private SpriteGroup myEnemyGroup;
	private SpriteGroup myShotGroup;
	
	public PlayState(OverlayTracker tracker, LevelManager levelManager){
		myLevelManager = levelManager;
		myTracker = tracker;
	}

	@Override
	public void initialize() {
		initPlayField();
		addOverlays();
		myPlayField.addGroup(myEnemyGroup);
		myPlayField.addGroup(myShotGroup);
		addPlayField(myPlayField);
		initCollisions();
	}

	private void initCollisions() {
		ShotToEnemyCollision testCollision = new ShotToEnemyCollision();
		myPlayField.addCollisionGroup(myShotGroup, myEnemyGroup, testCollision);
		
	}

	private void addOverlays() {
		myPlayField.addGroup(myTracker.getOverlayGroup("play"));
		
	}

	private void initPlayField(){
		myPlayField = myLevelManager.loadFirstLevel();
		myPlayField.setBackground(initBackground());
		
		EventPool eventPool = new EventPool();
		myPlayField.addEventPool(eventPool);
		
		BuildTowerEvent buildTower = new BuildTowerEvent(myPlayField);
		FindTargetEvent findTarget = new FindTargetEvent(myPlayField);
		ShootEvent shootEvent = new ShootEvent();
		
		eventPool.addEvent(buildTower);
		Player player = initPlayer(buildTower, findTarget, shootEvent);
		SpriteGroup playerGroup = new SpriteGroup("player");
		playerGroup.add(player);
		myPlayField.addGroup(playerGroup);
		EnemyHitEvent enemyHit = new EnemyHitEvent(player);
		
		myEnemyGroup = new SpriteGroup("enemyGroup");
		myShotGroup = new SpriteGroup("shotGroup");
		
		shootEvent.setHitEvent(enemyHit, myShotGroup);
		BuildEnemyEvent buildEnemy = new BuildEnemyEvent(myEnemyGroup);
		EnemyFailEvent failEvent = new EnemyFailEvent(player);
		
		EnemyGenerator enemyGenerator = new MediumEnemyGenerator("easyLevelPathPoints", failEvent, buildEnemy, enemyHit);
		player.addPathBoundary(enemyGenerator.getPathPoint());
		myPlayField.add(enemyGenerator);
		
		eventPool.addEvent(failEvent);
		eventPool.addEvent(buildEnemy);
		eventPool.addEvent(findTarget);
		eventPool.addEvent(shootEvent);
		eventPool.addEvent(enemyHit);
		
		myPlayField.addControl("player", initControl(player));
		//myPlayField.addControl("player", initCheats(player));
	}
	
	private Background initBackground(){
		BufferedImage backgroundImage = Resources.getImage("easyLevelBackground");
		backgroundImage = ImageUtil.resize(backgroundImage, Resources.getInt("gameWidth"), Resources.getInt("gameHeight"));
		return new ImageBackground(backgroundImage);
	}
	
	private Player initPlayer(BuildTowerEvent buildTowerEvent, FindTargetEvent findTarget, ShootEvent shootEvent){
		Player player = new Player(Resources.getImage("towerPreview"), 0 , 0, buildTowerEvent, findTarget, shootEvent, myTracker.getStat("money" , new Integer(0)), myTracker.getStat("score" , new Integer(0)), myTracker.getStat("selfEsteem" , new Integer(0)));		
		return player;
	}
	
	public Control initControl(BetterSprite player){
		MouseControl playerControl = new MouseControl(player, Resources.getGame());	
		playerControl.addInput(MouseEvent.BUTTON1, "onClick", "vooga.games.towerdefense.actors.Player");
		//playerControl.addInput(MouseEvent.MOUSE_MOVED, "move", "vooga.games.towerdefense.actors.Player");
		
		return playerControl;
	}
	
	public Control initCheats(BetterSprite player){
		KeyboardControl playerControl = new KeyboardControl(player, Resources.getGame());	
		playerControl.addInput(KeyEvent.VK_H, "cheatOn", "vooga.games.towerdefense.actors.Player");
		//playerControl.addInput(MouseEvent.MOUSE_MOVED, "move", "vooga.games.towerdefense.actors.Player");
		
		return playerControl;
	}
}
