package examples.event.pacmanmoveevent;

import examples.event.event.IEventListener;

/**
 * This listener interface for receiving pacman's move event.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * 
 */
public interface PacmanMoveListener extends IEventListener {
	public void moveAction(PacmanMoveEvent e);
}
