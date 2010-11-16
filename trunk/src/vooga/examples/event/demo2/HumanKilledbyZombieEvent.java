package vooga.examples.event.demo2;

import vooga.engine.event.IEventHandler;
/**
 * Class <code>HumanKilledbyZombieEvent</code> implements the interface {@link IEventHandler} to create 
 * an event for the ZombieLand game. 
 * <p>
 * There are two methods in <code>HumanKilledbyZombieEvent</code> interface.
 * Please see {@link #actionPerformed()} , {@link #isTriggered()}.
 * </p>
 * 
 * @author Meng Li
 *
 */
public class HumanKilledbyZombieEvent implements IEventHandler {
	
	private Human human;
	private Zombie zombie;
	/**
	 * Constructor. User can pass objects or variables through this constructor.
	 * @param human Human class object
	 * @param ghost Ghost class object
	 */
	public HumanKilledbyZombieEvent(Human human, Zombie ghost){
		this.human=human;
		this.zombie=ghost;
	}
	/**
	 * User defines what to do after event has been triggered.
	 */
	@Override
	public void actionPerformed() {
		System.out.println("zombie position "+zombie.getX()+"="+"human position "+human.getX()+",so human has been killed");
		System.exit(0);
	}
	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return  zombie.getX()==human.getX();
	}

}
