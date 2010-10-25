package vooga.games.cyberion.sprites;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.engine.event.EventManager;
import vooga.games.cyberion.events.EnemyFireEvent;

import com.golden.gamedev.object.Sprite;

//enemy class that handles enemy' life, and when they fire.
public class EnemyShip extends Sprite {
	private int life;
	private Random rnd;
	private EventManager eventManager;

	public EnemyShip(BufferedImage image, double x, double y, int life,
			EventManager eventManager) {

		super(image, x, y);
		setLife(life);
		rnd = new Random();
		this.eventManager = eventManager;
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);

		if (life <= 0)
			this.setActive(false);
		//enemies fire randomly with a chance of 3/1000 for every update call
		if (rnd.nextInt(1000) > 997) {
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
