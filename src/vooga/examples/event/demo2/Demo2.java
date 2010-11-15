package vooga.examples.event.demo2;

import vooga.engine.event.EventPool;
import vooga.engine.event.IEventHandler;
/**
 * This demo <code>Demo2</code> shows how to use {@link EventPool} and {@link IEventHandler} to create 
 * events in game.
 * <p>
 * Taking Pacman game for example, ghosts, enemies of pacman, are trying to catch pacman in one line and end the game. The
 * speed of ghost is faster than pacman. Here is the fire event condition: when the ghost catches 
 * up with pacman which indicated by their x position, the pacman will be killed the killevent will 
 * be triggered. In this case, we can write a event class such as PacmanKilledEvent.Add it in the
 * EventLoop class. And the eventLoop will check the event for you. 
 * </p>
 * @author Meng Li
 *
 */
public class Demo2 {
	private Pacman pacman;
	private Ghost ghost; 

	public Demo2(){
		pacman = new Pacman(10);
		ghost = new Ghost(2);
	}

	public void gameLoop(){
		// how to setup the event before the gameloop
		EventPool eventPool = EventPool.getInstance();
		PacmanKilledEvent pacmanEvent = new PacmanKilledEvent(pacman,ghost);
		eventPool.addEvent(pacmanEvent);
		//
		int pacmanSpeed = 1;
		int ghostSpeed = 2;
		while(true){
			//this will check all the registered events for the game, user don't need to write any if
			// and else statement, also user doesn't need check when to fire event. The eventPool will fire
			//the event for you as long as the condition in the PacmanEvent has been met .
			eventPool.checkEvents();
			//user has flexibility to remove a certain event
			//eventPool.removeEvent(pacmanEvent);
			pacman.setX(pacman.getX()+pacmanSpeed);
			ghost.setX(ghost.getX()+ghostSpeed);
			System.out.println("Pacman position: " +pacman.getX()+";"+ "Ghost position: "+ghost.getX());
		}
	}

	public static void main(String[] args){
		Demo2 demo = new Demo2();
		demo.gameLoop();
	}
}
