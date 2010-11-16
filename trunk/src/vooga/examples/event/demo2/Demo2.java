package vooga.examples.event.demo2;

import vooga.engine.event.EventPool;
import vooga.engine.event.IEventHandler;
/**
 * This demo <code>Demo2</code> shows how to use {@link EventPool} and {@link IEventHandler} to create 
 * events in game.
 * <p>
 * Taking ZombieLand game for example,Strong zombies, enemies of weak human, are trying to catch human in one line and end the game. The
 * speed of the zombie is faster than human. Here is the fire event condition: when the zombie catches 
 * up with human which indicated by their x position, the human will be killed the killevent will 
 * be triggered. In this case, we can write a event class such as HumanKilledbyZombieEvent.Add it in the
 * EventLoop class. And the eventLoop will check the event for you. 
 * </p>
 * @author Meng Li
 *
 */
public class Demo2 {
	private Human human;
	private Zombie zombie; 

	public Demo2(){
		human = new Human(10);
		zombie = new Zombie(2);
	}

	public void gameLoop(){
		// how to setup the event before the game loop. This will be one of the instance in LevelField
		EventPool eventPool = new EventPool();
		HumanKilledbyZombieEvent humankilledEvent = new HumanKilledbyZombieEvent(human,zombie);
		eventPool.addEvent(humankilledEvent);
		//
		int humanSpeed = 1;
		int ghostSpeed = 2;
		while(true){
			//this will check all the registered events for the game, user don't need to write any if
			// and else statement, also user doesn't need check when to fire event. The eventPool will fire
			//the event for you as long as the condition in the HumanKilledbyZombieEvent has been met .
			eventPool.checkEvents();
			//user has flexibility to remove a certain event
			//eventPool.removeEvent(humankilledEvent);
			human.setX(human.getX()+humanSpeed);
			zombie.setX(zombie.getX()+ghostSpeed);
			System.out.println("Human position: " +human.getX()+";"+ "zombie position: "+zombie.getX());
		}
	}

	public static void main(String[] args){
		Demo2 demo = new Demo2();
		demo.gameLoop();
	}
}
