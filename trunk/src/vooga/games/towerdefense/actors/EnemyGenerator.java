package vooga.games.towerdefense.actors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.path.PathPoint;

/**
 * Creates the enemies for each level
 * @author Justin
 *
 */
public abstract class EnemyGenerator extends BetterSprite{
	
	private List<PathPoint> myPath;
	protected EnemyFailEvent myFailEvent;
	
	
	public EnemyGenerator(String resourcePathName, EnemyFailEvent failEvent){
		myFailEvent = failEvent;
		myPath = createPath(resourcePathName);
	}


	private List<PathPoint> createPath(String resourcePathName) {
		List<PathPoint> path = new ArrayList<PathPoint>();
		try {
		String filePath = Resources.getString(resourcePathName);
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		while (sc.hasNextInt()) {
			int x = sc.nextInt();
			if (sc.hasNextInt()) {
				int y = sc.nextInt();
				path.add(new PathPoint(x, y));
			}
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return path;
	}

	
	
	
	
}
