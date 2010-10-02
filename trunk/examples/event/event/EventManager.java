package examples.event.event;

import java.util.*;

/**
 * This class manages the behavior of game events. Its main purpose is to manage
 * events, and inform listeners when the event they are listening to
 * happens.This class should be instantiated at the very beginning of the game,
 * and be shared by all objects who would like to use our event systems. It can
 * register and remove listener. In addition, it can establish relationships
 * between isolated objects.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @see examples/event
 * @Version: October 1st
 */

public class EventManager {

	private Map<String, ArrayList<IEventListener>> listenerRepository = new HashMap<String, ArrayList<IEventListener>>();
	private ArrayList<IEventListener> eventList;

	/**
	 * add a eventlistener to the Vector in order to dispatch it later
	 * 
	 * @param key
	 *            Name of the event
	 * @param listener
	 *            The listener to be added
	 */
	public void addEventListener(String key, IEventListener listener) {
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
	public void removeEventListener(String key, IEventListener listener) {
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
	public void fireEvent(String key, IEvent event) {
		if (listenerRepository.get(key) != null) {
			for (IEventListener e : listenerRepository.get(key)) {
				e.actionPerformed(event);
			}
		}
	}

}