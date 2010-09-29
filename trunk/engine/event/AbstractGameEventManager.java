package engine.event;

import java.util.*;
/**
 * This class is a top abstract class which should be subclassed by classes which want to add 
 * specific events and listen to specific events. This class handles receiving events and dispatching
 * events.
 * 
 * 
 * Example:
 * public class Ghost extends AbstractGameEventManager implements PacmanMoveListener {
	private int x,y;
	
	Ghost(int x, int y) {
		this. x = x;
		this. y = y;
	}

	@Override
	public void actionPerformed(IGameEvent e) {
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

public abstract class AbstractGameEventManager {
/**
*
*/
	// non-static doesn't work???why 
	//two subclasses only share the static instance variable in superclass???
	private static Map<String,ArrayList<IEventListener>> mapRepository=new HashMap<String,ArrayList<IEventListener>>();
	private ArrayList<IEventListener> eventList;
	
	public AbstractGameEventManager() {
	}

/**
 * add a eventlistener to the Vector in order to dispatch it later 	
 * @param ml: IEventListener interface
 */
	public void addEventListener(String key,IEventListener ml) {
		if(mapRepository.containsKey(key)){
			mapRepository.get(key).add(ml);
		}
		else{
			eventList=new ArrayList<IEventListener>();
			eventList.add(ml);
			mapRepository.put(key, eventList);
		}
	}
	
/**
 * remove a eventlistener in the Map 
 * @param ml: IEventListener interface
 */
	public void removeEventListener(String key,IEventListener ml) {
		mapRepository.get(key).remove(ml);
	}

/**
 * dispatch a event and let the listener receive this event	
 * @param myEvent: MyEvent interface
 */
	public void fireEvent(String EventKey,IGameEvent gameEvent) {  //change to map
		for(IEventListener e:mapRepository.get(EventKey)){
			e.actionPerformed(gameEvent);
		}
	}

}