package CyberionSprite;

import java.awt.image.BufferedImage;

import GameEvent.EnemyFireEvent;
import GameEvent.EnemyFireListener;
import GameEvent.PlayerMoveEvent;
import GameEvent.PlayerMoveListener;

import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.Sprite;

import engine.event.IEvent;
//similar to PlayerShot in concept, except shots are fired randomly and 
//with a derection determined by the position of th player.
public class EnemyShot extends AdvanceSpriteGroup implements EnemyFireListener,
		PlayerMoveListener {
	private BufferedImage image;
	private double playerX;
	private double playerY;
	private double dist;
	private double speed = 0.3;

	public EnemyShot(String name, BufferedImage image) {
		super(name);
		this.image = image;
	}

	@Override
	public void actionPerformed(IEvent e) {
		if (e.getName().matches("PlayerMoveEvent")) {
			moveAction((PlayerMoveEvent) e);
		}
		if (e.getName().matches("EnemyFireEvent")) {
			fireAction((EnemyFireEvent) e);
		}

	}

	@Override
	//takes player position and fires in that direction
	public void fireAction(EnemyFireEvent e) {
		dist = Math.pow((playerX - e.getX()) * (playerX - e.getX())
				+ (playerY - e.getY()) * (playerY - e.getY()), 0.5);
		Sprite shot = new Sprite(image, e.getX(), e.getY());
		shot.setHorizontalSpeed(speed * (playerX - e.getX()) / dist);
		shot.setVerticalSpeed(speed * (playerY - e.getY()) / dist);
		add(shot);
	}

	@Override
	public void moveAction(PlayerMoveEvent e) {
		playerX = e.getX();
		playerY = e.getY();
	}

}
