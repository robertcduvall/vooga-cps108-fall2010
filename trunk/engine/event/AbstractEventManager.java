package engine.event;

import java.util.*;
/**
 * This class is a top abstract class which should be subclassed by classes which want to add 
 * specific events and listen to specific events. This class handles receiving events and dispatching
 * events.
 * 
 * 
 * Example:
 * public class Ghost extends AbstractEventManager implements PacmanMoveListener {
	private int x,y;
	
	Ghost(int x, int y) {
		this. x = x;
		this. y = y;
	}

	@Override
	public void actionPerformed(MyEvent e) {
		action((PacmanMoveEvent)e);
	}
	
	@Override
	public void action(PacmanMoveEvent e) {
		//do something
		setX(e.getX());
		setY(e.getY());
	}
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @Version: September 26th
 */

public abstract class AbstractEventManager {
/**
*
*/
	// non-static doesn't work???why 
	//two subclasses only share the static instance variable in superclass???
	private static Map<String,ArrayList<MyEventListener>> mapRepository=new HashMap<String,ArrayList<MyEventListener>>();
	private ArrayList<MyEventListener> eventList;
	
	public AbstractEventManager() {
	}

/**
 * add a eventlistener to the Vector in order to dispatch it later 	
 * @param ml: MyEventListener interface
 */
	public void addMyEventListener(String key,MyEventListener ml) {
		if(mapRepository.containsKey(key)){
			mapRepository.get(key).add(ml);
		}
		else{
			eventList=new ArrayList<MyEventListener>();
			eventList.add(ml);
			mapRepository.put(key, eventList);
		}
	}
	
/**
 * remove a eventlistener in the Map 
 * @param ml: MyEventListener interface
 */
	public void removeMyEventListener(String key,MyEventListener ml) {
		mapRepository.get(key).remove(ml);
	}

/**
 * dispatch a event and let the listener receive this event	
 * @param myEvent: MyEvent interface
 */
	public void fireEvent(String EventKey,MyEvent myEvent) {  //change to map
		for(MyEventListener e:mapRepository.get(EventKey)){
			e.actionPerformed(myEvent);
		}
	}

}
