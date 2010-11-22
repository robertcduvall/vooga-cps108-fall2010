package vooga.games.cyberion.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.sprites.enemyshot.EnemyShot;
import vooga.games.cyberion.states.PlayState;

public class EnemyFireEvent implements IEventHandler {

	private Sprite[] enemy;
	private PlayState playState;

	public EnemyFireEvent(DropThis myGame, Sprite[] enemy, PlayState playState) {
		this.enemy = enemy;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
	}

	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		for (Sprite s : enemy) {
			if (s.getY() > 100) {
				EnemyShot sprite = new EnemyShot(playState.getPlayField()
						.getGroup("enemyShot").getActiveSprite().getImage());
				sprite.setX(s.getX());
				sprite.setY(s.getY());
				sprite.setVerticalSpeed(2);
				playState.getPlayField().add(sprite);
			}
		}
		return false;
	}
}
