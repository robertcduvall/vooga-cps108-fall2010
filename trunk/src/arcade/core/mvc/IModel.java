package arcade.core.mvc;


/**
 * 
 * @author: 		Jimmy Mu
 * @date:			12-09-10
 * @description:	This is the IModel class. An IModel object is 
 * 					any object that is treated as the Model in the 
 * 					Model View Controller concept. The method supported 
 * 					in this interface is setController.
 */

public interface IModel {

	/**
	 * setController registers a IModel with an IController object.
	 * Once registered, the IModel object can then communicate with
	 * the IController to relay information or ask for services.
	 * @param control
	 */
	public void setController(IController control);
	
}
