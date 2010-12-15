package arcade.core.mvc;


/**
 * 
 * @author Jimmy Mu
 * @date 12-09-10
 */

public interface IViewer {

	/**
	 * This method sets up the control for the viewer.
	 * @param control
	 */
	public void setController(IController control);
	
	/**
	 * Gets the current controller
	 * @return
	 */
	public IController getController();
	
}
