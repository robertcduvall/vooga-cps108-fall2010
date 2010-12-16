package arcade.core.mvc;


/**
 * 
 * @author:			Jimmy Mu
 * @date: 			12-09-10
 * @description:	The IViewer represents the View in the Model View Control
 * 					concept. The interface provides the method of setting
 * 					up the controller and the method to fetch the controller
 * 					if needed. The reason why getController is necessary
 * 					is that Arcade uses reflection to set up the Store container
 * 					and this is the only to get a controller and set the controller
 * 					up with other UI components.
 */

public interface IViewer {

	/**
	 * This method sets up the control for the viewer. Doing this will
	 * allow the IViewer to communicate with the IController and 
	 * request information or services from each other.
	 * @param control
	 */
	public void setController(IController control);
	
	/**
	 * Gets the current IController in use.
	 * @return
	 */
	public IController getController();
	
}
