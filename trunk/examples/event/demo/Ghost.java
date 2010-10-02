package examples.event.demo;
import examples.event.pacmanmoveevent.PacmanMoveEvent;
import examples.event.pacmanmoveevent.PacmanMoveListener;
import examples.event.event.*;

/**
 * Object ghost that tries to follow pacman and kill him.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * 
 */
public class Ghost implements PacmanMoveListener {

	private int x, y;

	Ghost(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	// This method will be called when the event ghost is listening to occurs
	public void actionPerformed(IEvent e) {
		moveAction((PacmanMoveEvent) e);
	}

	@Override
	// This method handles pacman move event such that ghost would follow
	// pacman's movement
	public void moveAction(PacmanMoveEvent e) {
		setX(e.getX());
		setY(e.getY());
		System.out.println("Ghost is following pacman!");
	}

}
