package vooga.examples.event.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonEventManager {
	private static Map<String, ArrayList<IEventListener>> listenerRepository = new HashMap<String, ArrayList<IEventListener>>();
	private static ArrayList<IEventListener> eventList;
	private static SingletonEventManager instance =new SingletonEventManager();
	
	/**
	 * make it singleton
	 */
	private SingletonEventManager(){	
	}
	/**
	 * return the instance of the class
	 * @return
	 */
	public static SingletonEventManager getEventManagerInstance(){
		return instance;
	}
	/**
	 * add a eventlistener to the Vector in order to dispatch it later
	 * 
	 * @param key
	 *            Name of the event
	 * @param listener
	 *            The listener to be added
	 */
	public static void addEventListener(String key, IEventListener listener) {
		if (listenerRepository.containsKey(key)) {
			listenerRepository.get(key).add(listener);
		} else {
			eventList = new ArrayList<IEventListener>();
			eventList.add(listener);
			listenerRepository.put(key, eventList);
		}
	}

	/**
	 * remove a event listener in the repository
	 * 
	 * @param key
	 *            Name of the event
	 * @param listener
	 *            The listener to be removed
	 */
	public static void removeEventListener(String key, IEventListener listener) {
		if (listenerRepository.get(key) != null) {
			listenerRepository.get(key).remove(listener);
		}
	}

	/**
	 * dispatch a event and let the listener receive this event
	 * 
	 * @param key
	 *            Name of the event
	 * @param event
	 *            The triggered event
	 */
	public static void fireEvent(String key, IEvent event) {
		if (listenerRepository.get(key) != null) {
			for (IEventListener e : listenerRepository.get(key)) {
				e.actionPerformed(event);
			}
		}
	}
}

