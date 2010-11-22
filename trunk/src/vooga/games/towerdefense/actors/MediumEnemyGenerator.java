package vooga.games.towerdefense.actors;

import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.enemies.EnemySpawn;
import vooga.games.towerdefense.events.BuildEnemyEvent;
import vooga.games.towerdefense.events.EnemyFailEvent;
import vooga.games.towerdefense.events.EnemyHitEvent;

import com.golden.gamedev.object.Timer;

public class MediumEnemyGenerator extends EnemyGenerator{
	private static final int SECOND = 100;
	private Timer hit1;
	private Timer hit2;
	private Timer hit3;
	private Timer spawn;
	private Timer gameTimer;
	private int spawnSpeed;
	
	public MediumEnemyGenerator(String resourcePathName, EnemyFailEvent failEvent, BuildEnemyEvent buildEvent, EnemyHitEvent hitEvent) {
		super(resourcePathName, failEvent, buildEvent, hitEvent);
		hit1 = new Timer(SECOND * 10);
		hit2 = new Timer(SECOND * 12);
		hit3 = new Timer(SECOND * 14);
		spawn = new Timer(SECOND * 20);
		gameTimer = new Timer(SECOND * 20);
		spawnSpeed = 40;
	}

	@Override
	protected void createEnemies(long elapsedTime) {
		if (hit1.action(elapsedTime)) {
			myBuildEvent.addEnemy(new Enemy(myPath, 50, 1, myFailEvent, myHitEvent));
		}
		if (hit2.action(elapsedTime)) {
			myBuildEvent.addEnemy(new Enemy(myPath, 80, 2, myFailEvent, myHitEvent));
		}
		if (hit3.action(elapsedTime)) {
			myBuildEvent.addEnemy(new Enemy(myPath, 40, 3, myFailEvent, myHitEvent));
		}
		if (spawn.action(elapsedTime)) {
			myBuildEvent.addEnemy(new EnemySpawn(myPath, spawnSpeed, myFailEvent, myBuildEvent, myHitEvent));
		}
		if (gameTimer.action(elapsedTime)) {
			long delay = spawn.getDelay() / 2;
			if (delay < 500) {
				spawn.setDelay(SECOND / 2);
			} else {
				spawn.setDelay(delay);
			}
			if (delay > 5000) {
				spawnSpeed -= 10;
			} else if (delay < 501) {
				spawnSpeed += 10;
			}
		}
	}
}
