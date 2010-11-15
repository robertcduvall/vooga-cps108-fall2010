package vooga.examples.event.demo2;

import vooga.engine.event.IEventHandler;
/**
 * Class <code>PacmanKilledEvent</code> implements the interface {@link #IEventHandler IEventHandler} to create 
 * an event for the Pacman game. 
 * <p>
 * There are two methods in <code>PacmanKilledEvent</code> interface.
 * Please see {@link #actionPerformed() actionPerformed} , {@link #isTriggerred() isTriggerred}.
 * </p>
 * @author Meng Li
 *
 */
public class PacmanKilledEvent implements IEventHandler {
	private Pacman pacman;
	private Ghost ghost;
	/**
	 * Constructor. User can pass objects or variables through this constructor.
	 * @param pacman
	 * @param ghost
	 */
	public PacmanKilledEvent(Pacman pacman, Ghost ghost){
		this.pacman=pacman;
		this.ghost=ghost;
	}
	/**
	 * User defines what to do after event has been triggered.
	 */
	@Override
	public void actionPerformed() {
		System.out.println("ghost position "+ghost.getX()+"="+"pacman position "+pacman.getX()+",so pacman has been killed");
		System.exit(0);
	}
	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return  ghost.getX()==pacman.getX();
	}

}
