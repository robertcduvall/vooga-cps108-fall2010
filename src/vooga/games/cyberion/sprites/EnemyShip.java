package vooga.games.cyberion.sprites;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.games.cyberion.events.EnemyFireEvent;

public class EnemyShip extends BetterSprite {
		private int life;
		private Random rnd;
		private EventPool eventManager;

		public EnemyShip(BufferedImage image, double x, double y, int life,
				EventPool eventManager) {

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
				eventManager.addEvent( new EnemyFireEvent(this,
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