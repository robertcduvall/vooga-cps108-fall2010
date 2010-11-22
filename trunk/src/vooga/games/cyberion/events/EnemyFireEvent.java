package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.sprites.enemyshot.EnemyShot;
import vooga.games.cyberion.states.PlayState;

public class EnemyFireEvent implements IEventHandler {


	private EnemyShip enemy;
	private PlayState playState;

	public EnemyFireEvent(EnemyShip enemy, PlayState playState) {
	
		this.enemy = enemy;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
		EnemyShot sprite = new EnemyShot();
		sprite.setX(enemy.getX());
		sprite.setY(enemy.getY());
		sprite.setVerticalSpeed(-2);
		System.out.println("being here");
		playState.getPlayField().add(sprite);
	}

	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return false;
	}
}
