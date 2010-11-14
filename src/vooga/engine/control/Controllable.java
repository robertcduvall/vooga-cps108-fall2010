package vooga.engine.player.control;

/**
 * Interface that can be used by classes that wish to use a control scheme. Has the 
 * required methods that every object being controlled by the Controller needs.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public interface Controllable {
	
	/**
	 * Implement the addInput methods here; addInput methods must be used for Controller
	 * to work
	 */
	public void setListeners();
	

}
