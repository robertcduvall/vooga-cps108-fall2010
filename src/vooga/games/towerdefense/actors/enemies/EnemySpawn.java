package vooga.games.towerdefense.actors.enemies;

import java.util.ArrayList;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.Utility;

import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.events.BuildEnemyEvent;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.path.PathPoint;

/**
 * Controls the enemy spawn speeds, path, speed of travel, and the amount of hit points 
 * each enemy has. It also detects whether or not it is hit and adjusts the score and health.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 */

public class EnemySpawn extends Enemy {
	
	private BuildEnemyEvent myBuildEvent;

	public EnemySpawn(ArrayList<PathPoint> path, int speed, EnemyFailEvent failEvent, BuildEnemyEvent buildEvent) {
		super(path, speed, 10, failEvent);
		myBuildEvent = buildEvent;
		
	}
	
	@Override
	protected void setImage() {
		setImage(Resources.getImage("duvallFaceYellow"));
	}
	
	@Override
	public void gotHit() {
		setActive(false);
		for(int i = 0; i< 5; i++){
			Enemy temp = new Enemy(myPath, Utility.getRandom(20, 80), Utility.getRandom(1, 3), myFailEvent);
			temp.myLoc = myLoc-(i*5);
			myBuildEvent.buildEnemy(temp);
		}
		super.gotHit();
	}
	
	
	

}
