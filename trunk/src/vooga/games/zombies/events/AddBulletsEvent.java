package vooga.games.zombies.events;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.games.zombies.weapons.Bullet;

public class AddBulletsEvent implements IEventHandler {

	SpriteGroup bullets;

	public AddBulletsEvent(SpriteGroup bullets) {
		this.bullets = bullets;
	}

	@Override
	public boolean isTriggered() {
		// this is specially triggered by the player
		return false;
	}

	@Override
	public void actionPerformed() {
		return;
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

}
