package arcade.security.mvc;

/**
 * 
 * @author Jimmy Mu
 * @date 12-09-10
 * @description This static class sets up the all the references for the model
 * view control to work and initializes the start setting for the MVC. 
 */

public class MVCInitializer {

	/**
	 * This method initializes all references needed for a MVC by setting up the
	 * references needed for viewer, controller, and model.
	 * @param view
	 * @param model
	 * @param control
	 */
	public static void initialize(IViewer view, IModel model, MainController control)
	{
		control.setViewer(view);	
		control.setModel(model);
		
		model.setController(control);
		view.setController(control);
		
		//set up the things you want to do at the very start!
		control.setUpInitalMVC();
	}

}
