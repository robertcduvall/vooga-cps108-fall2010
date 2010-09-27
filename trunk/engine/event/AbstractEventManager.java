package engine.event;

import java.util.*;
/**
 * This class is a top abstract class which should be subclassed by classes which want to add 
 * specific events and listen to specific events. This class handles receiving events and dispatching
 * events.
 * 
 * 
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
	
///**
// * remove a eventlistener in the Vector 
// * @param ml: MyEventListener interface
// */
//	public void removeMyEventListener(MyEventListener ml) {
//		mapRepository.remove(ml);
//	}

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