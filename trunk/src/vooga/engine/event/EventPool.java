package vooga.engine.event;

import java.util.ArrayList;
import vooga.examples.event.demo2.Demo2;
/**
 * The <code>EventPool</code> collects all the events from the game loop. User has the ability to add
 * event object which implements <code>IEventHandler</code> interface. Also the user has the flexibility 
 * to remove the event from the EventPool if a certain event is not wanted anymore. In the game loop, <code>
 * (EventPool)object.checkEvents()</code> needs to be called to check all the events. If an event's condition is satisfied,
 * the event will be triggered or fired automatically. For more details about how to use, please see the 
 * {@link Demo2}.
 *  
 * @see IEventHandler
 * @author Meng Li
 * @author Hao He
 * @author Cody Kolodziejzyk
 *
 */
public class EventPool {
	
	ArrayList<IEventHandler> eventList = new ArrayList<IEventHandler>();	
	/**
	 * Add event into the eventPool so that eventPool can check the event for you in the game loop.
	 * @param event The object which implements <code>IEventHandler</code> interface.  
	 */
	public void addEvent(IEventHandler event){
		eventList.add(event);
	}
	/**
	 * Get the a list of events which contains all the events in the EventPool
	 * @return ArrayList<IEventHandler> Returns an event list object which is an arrayList of objects which implement <code>IEventHandler</code>
	 * interface.
	 */
	public ArrayList<IEventHandler> getEventList(){
		return eventList;
	}
	
	/**
	 * Set the eventList object.
	 * @param eventList An event list object which is an arrayList of objects which implement <code>IEventHandler</code> interface. 
	 */
	public void setEventList(ArrayList<IEventHandler> eventList){
		this.eventList = eventList;
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
