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
import vooga.games.towerdefense.level.Level;
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
	
	private OverlayTracker myTracker;
	
	public PlayState(OverlayTracker tracker){
		myTracker = tracker;
	}
	
	public void changeLevel(String xmlFilePath, String backgroundImageKey, String pathPointsKey){
		this.removeEverything();
		for(String key:myTracker.statKeySet()){
			myTracker.getStat(key).reset();
		}
		Level level = new Level(myTracker, xmlFilePath, backgroundImageKey, pathPointsKey);
		addPlayField(level.getPlayField());
	}

	@Override
	public void initialize() {
	
	}
}
