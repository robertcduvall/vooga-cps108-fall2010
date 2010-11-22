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
import vooga.games.towerdefense.actors.towers.Fast;
import vooga.games.towerdefense.actors.towers.Normal;
import vooga.games.towerdefense.actors.towers.Sniper;
import vooga.games.towerdefense.actors.towers.Tower;
import vooga.games.towerdefense.buttons.TowerSwitchButton;
import vooga.games.towerdefense.collisions.ShotToEnemyCollision;
import vooga.games.towerdefense.events.AfterCounterEvent;
import vooga.games.towerdefense.events.BuildEnemyEvent;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.events.EnemyHitEvent;
import vooga.games.towerdefense.events.FindTargetEvent;
import vooga.games.towerdefense.events.ShootEvent;
import vooga.widget.counter.Counter;

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
	
	
	private static final int SECOND = 1000;
	private PlayField myPlayField;
	private LevelManager myLevelManager;
	private OverlayTracker myTracker;
	private SpriteGroup myEnemyGroup;
	private SpriteGroup myShotGroup;
	private EnemyGenerator myEnemyGenerator;
	
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
		Counter counter = new Counter(10, SECOND, SECOND*2, 150, new AfterCounterEvent(myPlayField, myEnemyGenerator));
		counter.setLocation(Resources.getInt("playFieldWidth")/2 - 75,Resources.getInt("playFieldHeight")/2 - 75);
		myPlayField.add(counter);
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
		
		myEnemyGenerator = new MediumEnemyGenerator("easyLevelPathPoints", failEvent, buildEnemy, enemyHit);
		player.addPathBoundary(myEnemyGenerator.getPath());
		
		TowerSwitchButton normalTower = initButton(Resources.getImage("normalTower"), 880, 260, player, new Normal(0,0, shootEvent));
		TowerSwitchButton fastTower = initButton(Resources.getImage("fastTower"), 880, 360, player, new Fast(0,0, shootEvent));
		TowerSwitchButton sniperTower = initButton(Resources.getImage("sniperTower"), 880, 460, player, new Sniper(0,0, shootEvent));
		
		myPlayField.add(normalTower);
		myPlayField.add(fastTower);
		myPlayField.add(sniperTower);
		
		eventPool.addEvent(normalTower);
		eventPool.addEvent(fastTower);
		eventPool.addEvent(sniperTower);
		
		eventPool.addEvent(failEvent);
		eventPool.addEvent(buildEnemy);
		eventPool.addEvent(findTarget);
		eventPool.addEvent(shootEvent);
		eventPool.addEvent(enemyHit);
		
		myPlayField.addControl("player", initControl(player));
		myPlayField.addControl("playerCheats", initControlKeyBoard(player));
		myPlayField.addControl("pause", initControlPause());
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
	
	private TowerSwitchButton initButton(BufferedImage bi, double x, double y, Player player, Tower tower){
		TowerSwitchButton normalTower = new TowerSwitchButton(Resources.getGame(), bi, x, y, player);
		normalTower.setTower(tower);
		return normalTower;
	}
	
	public Control initControl(BetterSprite player){
		MouseControl playerControl = new MouseControl(player, Resources.getGame());	
		playerControl.addInput(MouseEvent.BUTTON1, "onClick", "vooga.games.towerdefense.actors.Player");
		//playerControl.addInput(MouseEvent.MOUSE_MOVED, "move", "vooga.games.towerdefense.actors.Player");
		
		return playerControl;
	}
	
	public Control initControlKeyBoard(BetterSprite player){
		KeyboardControl playerControl = new KeyboardControl(player, Resources.getGame());	
		playerControl.addInput(KeyEvent.VK_H, "cheatOn", "vooga.games.towerdefense.actors.Player");
		
		return playerControl;
	}
	
	public Control initControlPause(){
		KeyboardControl pauseControl = new KeyboardControl(this, Resources.getGame());	
		pauseControl.addInput(KeyEvent.VK_P, "pause", "vooga.games.towerdefense.Pauser");
		
		return pauseControl;
	}
}
