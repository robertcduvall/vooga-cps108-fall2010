package vooga.games.towerdefense.actors;

import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.enemies.EnemySpawn;
import vooga.games.towerdefense.events.BuildEnemyEvent;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.events.EnemyHitEvent;

import com.golden.gamedev.object.Timer;

public class MediumEnemyGenerator extends EasyEnemyGenerator{
	private static final int SECOND = 1000;
	private Timer hit1;
	private Timer hit2;
	private Timer hit3;
	private Timer spawn;
	private Timer gameTimer;
	private int spawnSpeed;
	
	public MediumEnemyGenerator(String resourcePathName, EnemyFailEvent failEvent, BuildEnemyEvent buildEvent, EnemyHitEvent hitEvent) {
		super(resourcePathName, failEvent, buildEvent, hitEvent);
		hit1 = new Timer(SECOND * 8);
		hit2 = new Timer(SECOND * 9);
		hit3 = new Timer(SECOND * 10);
		spawn = new Timer(SECOND * 45);
		gameTimer = new Timer(SECOND * 45);
		spawnSpeed = 80;
	}
}
