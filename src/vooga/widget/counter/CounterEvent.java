package vooga.widget.counter;

import vooga.engine.event.IEventHandler;


/**
 * This class is used in conjunction with the Counter.
 * This class should be extended and the actionPerformed() method should be overrided
 * to perform what ever action wanted.  
 * 
 * 
 * @author Justin Goldsmith
 */
public abstract class CounterEvent{

	public void trigger(){
		actionPerformed();
	}
	
	public abstract void actionPerformed();

}
