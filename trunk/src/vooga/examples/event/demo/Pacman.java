package vooga.examples.event.demo;
import vooga.examples.event.event.SingletonEventManager;
import vooga.examples.event.pacmanmoveevent.PacmanMoveEvent;

/**
 * Object pacman. It will notify the game's event manager of its movement when
 * the player tries to move it.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 */
public class Pacman {
	private int x, y;

	Pacman(int x, int y) {
		this.x = x;
		this.y = y;		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		// Inform event manager of its movement
		SingletonEventManager.fireEvent("PacmanMoveEvent", new PacmanMoveEvent(this,
				"PacmanMoveEvent", x, y));
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		// Inform event manager of its movement
		SingletonEventManager.fireEvent("PacmanMoveEvent", new PacmanMoveEvent(this,
				"PacmanMoveEvent", x, y));
		this.y = y;
	}

}
