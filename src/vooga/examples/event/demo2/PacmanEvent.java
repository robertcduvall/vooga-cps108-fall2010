package vooga.examples.event.demo2;

import vooga.engine.event.IEventHandler;

public class PacmanEvent implements IEventHandler {
	private Pacman pacman;
	private Ghost ghost;
	/**
	 * constructor. User can pass objects or variables through this constructor.
	 * @param pacman
	 * @param ghost
	 */
	public PacmanEvent(Pacman pacman, Ghost ghost){
		this.pacman=pacman;
		this.ghost=ghost;
	}
	/**
	 * user defines what to do after event has been triggered.
	 */
	@Override
	public void actionPerformed() {
		System.out.println("ghost position "+ghost.getX()+"="+"pacman position "+pacman.getX()+",so pacman has been killed");
			System.exit(0);
	}
	/**
	 * user defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggerred() {
		return  ghost.getX()==pacman.getX();
	}

}
