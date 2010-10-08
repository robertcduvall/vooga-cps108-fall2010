package Collision;

import CyberionSprite.EnemyShip;

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
//when a playershot collides with an enemy, enemy's life decreases, shot disappears, and a sound is played
	public void collided(Sprite playershot, EnemyShip enemy) {
		enemy.setLife(enemy.getLife() - 1);
		playershot.setActive(false);
		bsSound.play("Resources/explosion.wav");
	}

}
