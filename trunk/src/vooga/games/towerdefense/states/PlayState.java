package vooga.games.towerdefense.states;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import vooga.engine.control.Control;
import vooga.engine.control.MouseControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.towerdefense.actors.EasyEnemyGenerator;
import vooga.games.towerdefense.actors.EnemyGenerator;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.events.BuildEnemyEvent;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.events.EnemyFailEvent;

import com.golden.gamedev.object.Background;
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
	
	public PlayState(LevelManager levelManager){
		myLevelManager = levelManager;
	}

	@Override
	public void initialize() {
		initPlayField();
		addPlayField(myPlayField);
	}

	private void initPlayField(){
		myPlayField = myLevelManager.loadFirstLevel();
		myPlayField.setBackground(initBackground());
		
		EventPool eventPool = new EventPool();
		myPlayField.addEventPool(eventPool);
		
		BuildTowerEvent buildTower = new BuildTowerEvent(myPlayField);
		
		
		
		eventPool.addEvent(buildTower);
		Player player = initPlayer(buildTower);
		myPlayField.add(player);
		
		BuildEnemyEvent buildEnemy = new BuildEnemyEvent(myPlayField);
		EnemyFailEvent failEvent = new EnemyFailEvent(player);
		EnemyGenerator enemyGenerator = new EasyEnemyGenerator("easyLevelPathPoints", failEvent, buildEnemy);
		eventPool.addEvent(failEvent);
		eventPool.addEvent(buildEnemy);
		myPlayField.add(enemyGenerator);
		
		myPlayField.addControl("player", initControl(player));
	}
	
	private Background initBackground(){
		BufferedImage backgroundImage = Resources.getImage("background");
		backgroundImage = ImageUtil.resize(backgroundImage, Resources.getInt("gameWidth"), Resources.getInt("gameHeight"));
		return new ImageBackground(backgroundImage);
	}
	
	private Player initPlayer(BuildTowerEvent buildTowerEvent){
		OverlayTracker tracker =  myPlayField.getOverlayTracker();
		Player player = new Player(Resources.getImage("towerPreview"), 0 , 0, buildTowerEvent, tracker.getStat("money" , new Integer(0)), tracker.getStat("score" , new Integer(0)), tracker.getStat("selfEsteem" , new Integer(0)));		
		return player;
	}
	
	public Control initControl(BetterSprite player){
		MouseControl playerControl = new MouseControl(player, Resources.getGame());	
		playerControl.addInput(MouseEvent.BUTTON1, "onClick", "vooga.games.towerdefense.actors.Player");
		//playerControl.addInput(MouseEvent.MOUSE_MOVED, "move", "vooga.games.towerdefense.actors.Player");
		
		return playerControl;
	}

}
