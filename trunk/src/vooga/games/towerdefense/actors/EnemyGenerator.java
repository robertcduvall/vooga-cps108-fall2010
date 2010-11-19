package vooga.games.towerdefense.actors;

import java.util.List;

import vooga.engine.core.BetterSprite;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.path.PathPoint;

/**
 * Creates the enemies for each level
 * @author Justin
 *
 */
public abstract class EnemyGenerator extends BetterSprite{
	
	private List<PathPoint> myPath;
	
	public EnemyGenerator(String resourcePathName, EnemyFailEvent hitEvent){
		
	}
	
	
	
}
