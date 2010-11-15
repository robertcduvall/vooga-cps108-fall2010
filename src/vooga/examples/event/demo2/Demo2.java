package vooga.examples.event.demo2;

import vooga.engine.event.EventPool;

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
