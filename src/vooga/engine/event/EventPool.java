package vooga.engine.event;

import java.util.ArrayList;
/**
 * The <code>EventPool</code> collects all the events from the game loop. Also the user has the flexibility to remove the event
 * from the EventPool if a certain event is not wanted anymore.
 * This is a singleton pattern and the object instance should be obtained by 
 * <code> EventPool object = EventPool.getInstance();
 * </code>
 * @see IEventHandler
 * @author Meng Li
 * @author Hao He
 * @author Cody Kolodziejzyk
 *
 */
public class EventPool {
	ArrayList<IEventHandler> eventList = new ArrayList<IEventHandler>();
	private static EventPool instance =new EventPool();
	/**
	 * Private constructor
	 */
	private EventPool(){	
	}
	/**
	 * Static method to get singleton object
	 * @return <code>EventPool</code> object
	 */
	public static EventPool getInstance(){
		return instance;
	}
	/**
	 * Add event into the eventPool so that eventPool can check the event for you in the gameloop.
	 * @param event The class object which implements <code>IEventHandler</code> interface.  
	 */
	public void addEvent(IEventHandler event){
		eventList.add(event);
	}
	/**
	 * Remove event from the eventPool so that this event will not trigger anything in the gameloop.
	 * @param event The class object which implements <code>IEventHandler</code> interface.  
	 */
	public void removeEvent(IEventHandler event){
		if(eventList.contains(event))
		eventList.remove(event);
		else return;
	}
	/**
	 * Put this method in the update game loop so that all the events will be checked automatically 
	 * and fired accordingly.
	 */
	public void checkEvents(){
		for(IEventHandler s:eventList){
			if(s.isTriggered()){
				s.actionPerformed();
			}
		}
	}
}
