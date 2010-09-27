package engine.event;


/**
 * A object of a class which wants to addMyEventListener or a class which wants to listen to an event 
 * must implement this interface
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @Version: September 26th
 *
 */
public interface MyEventListener {
   /**
    * When the event is triggered, this method will be called.
    * @param e:MyEvent interface
    */
   public void actionPerformed(MyEvent e);
}