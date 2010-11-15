package vooga.engine.control;

/**
 * Interface that can be used by classes that want to extend the Control class. The methods
 * in this class are required to create a new Control scheme.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public interface Controller {

	public void initializeMappings();
	/**
     * Create keyset to map input to method. Can be overwritten to create new control scheme  
     * 
     * @param listen Use a String version of what to listen to (eg. "a"
     * for "KEYBOARD" or "1" for "MOUSE")
     * 
     * @param method Name of method to map to (do not include brackets)
     * 
     * @param classname Name of class that wants to use this (eg.
     * "Player" or "Game")
     * 
     * @param paramVals Value of the parameters that the method has
     */
	public void addInput(int listen, String method, String classname, Object... paramVals);
    /**
     * Sets the parameter types that need to be used for the next method
     * 
     * @param parameterTypes The types of parameters that need to be used. Need to be implemented in the form <br />
     * <i>setParams(new Class[]{int.class, String.class})</i><br /> if you want to tell Controller that the next method takes
     * an int followed by a String.
     */
    public void setParams(Class<?>... parameterTypes);
    /**
     * Invoke methods here. Call method each time through game loop
     */
	public void update();
	
}
