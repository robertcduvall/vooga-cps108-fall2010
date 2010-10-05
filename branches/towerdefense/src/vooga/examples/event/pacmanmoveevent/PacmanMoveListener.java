package vooga.examples.event.pacmanmoveevent;

import vooga.examples.event.event.IEventListener;

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
