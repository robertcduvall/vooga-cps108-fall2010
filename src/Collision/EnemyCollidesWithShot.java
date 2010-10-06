package Collision;

import CyberionSprite.EnemyShip;
import CyberionSprite.PlayerShip;
import CyberionSprite.PlayerShot;

import com.golden.gamedev.engine.BaseAudio;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class EnemyCollidesWithShot extends BasicCollisionGroup {

	private BaseAudio bsSound;
	public EnemyCollidesWithShot(BaseAudio bsSound) {
		this.bsSound = bsSound;
	}

	@Override
	public void collided(Sprite playershot, Sprite enemy) {
		collided(playershot, (EnemyShip) enemy);

	}

	public void collided(Sprite playershot, EnemyShip enemy) {
		enemy.setLife(enemy.getLife() - 1);
		playershot.setActive(false);
		bsSound.play("Resources/explosion.wav");
	}

}
