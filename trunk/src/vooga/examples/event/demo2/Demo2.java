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
		EventPool eventPool = EventPool.getInstance();
		PacmanEvent pacmanEvent = new PacmanEvent(pacman,ghost);
		eventPool.addEvent(pacmanEvent);
		int pacmanSpeed = 1;
		int ghostSpeed = 2;
		while(true){
			eventPool.checkEvents();
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
