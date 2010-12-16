package arcade.core.mvc;

import arcade.core.Tab;


/**
 * 
 * @author: 		Jimmy Mu
 * @date: 			12-09-10
 * @description:	This controller interface represents an abstract controller
 * 					in the Model View Controller design. The three basic methods
 * 					provided in here are initialize, addModel, and add Viewer.	 
 */

public interface IController {

	/**
	 * initialize specifies the action to take when the the controller initializes
	 * or when an update method needs to be processed
	 */
	public void initialize();
	
	/**
	 * addModel adds a generic IModel and associate that object with the IController. In
	 * this way, the IModel can talk with the IController.
	 * @param model
	 */
	public void addModel(IModel model);
	
	/**
	 * addViewer adds a generic Tab UI component to the IController. In this manner,
	 * the IControl can relay information to the IController.
	 * @param viewer
	 */
	public void addViewer(Tab viewer);
}
