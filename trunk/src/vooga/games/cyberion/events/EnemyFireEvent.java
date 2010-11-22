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
			if (s != null && s.getY() > 50 && Math.random() + 2 > s.getX() / s.getY() && Math.random() > .99
					&& s.getY() < 600 && s.isActive()) {
				((EnemyShip) s).setShooting(false);
				EnemyShot sprite = new EnemyShot();
				sprite.setX(s.getX());
				sprite.setY(s.getY());
				sprite.setVerticalSpeed(.2);
				playState.getPlayField().getGroup("enemyShot").add(sprite);

			}
		}
		return true;
	}
}
