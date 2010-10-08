package CyberionSprite;

import java.awt.image.BufferedImage;
import java.util.Random;

import GameEvent.EnemyFireEvent;
import GameEvent.PlayerFireEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import engine.event.EventManager;

public class EnemyShip extends Sprite{
	private int life;
	private Random rnd;
	private EventManager eventManager;

	public EnemyShip(BufferedImage image, double x, double y, int life, EventManager eventManager) {

		super(image, x, y);
		setLife(life);
		rnd = new Random();
		this.eventManager = eventManager;

	}

	public void update(long elapsedTime) {
		
		super.update(elapsedTime);
		
		if (life <= 0)
			this.setActive(false);
		
		if(rnd.nextInt(1000)>994){
			eventManager.fireEvent("EnemyFireEvent", new EnemyFireEvent(this,
					"EnemyFireEvent", getX(), getY()));
		}
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

}
