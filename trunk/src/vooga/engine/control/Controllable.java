package vooga.engine.control;

/**
 * Interface that can be used by classes that wish to use a control scheme. Has the 
 * required methods that every object being controlled by the Controller needs.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public interface Controllable {
	
	/**
	 * Implement the addInput methods here. The addInput method registers inputs with methods so
	 * at run time the Controller knows what actions to perform when it encounters a certain input.
	 */
	public void setListeners();
	

}
