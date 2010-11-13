package vooga.engine.event;

import java.util.ArrayList;
/**
 * Collect all the events from the game loop. Also the user has the flexibility to remove the event
 * from the EventPool if a certain event is not wanted anymore.
 * This is a singleton pattern and the object should be obtained by 
 * <code> EventPool object = EventPool.getInstance();
 * </code>
 * 
 * @author Meng Li
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
	 * Get singleton object
	 * @return
	 */
	public static EventPool getInstance(){
		return instance;
	}
	/**
	 * Add event into the eventPool so that eventPool can check the event for you in the gameloop.
	 * @param event
	 */
	public void addEvent(IEventHandler event){
		eventList.add(event);
	}
	/**
	 * Remove event from the eventPool so that this event will not trigger anything in the gameloop.
	 * @param event
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
			if(s.isTriggerred()){
				s.actionPerformed();
			}
		}
	}
}
