package arcade.core.mvc;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

/**
 * This class represents a Controller 
 * @author Yijia Mu
 *
 */

public class Controller implements IController{

	private ResourceBundle mirror;

	public Controller(String filepath)
	{
		mirror = ResourceBundle.getBundle(filepath);

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addModel(IModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addViewer(IViewer viewer) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method uses reflection to process all the 
	 * events from the viewer and the controller.
	 * @param eventName
	 */
	public void processEvent(String eventName) {

		try {
			String methodname = mirror.getString(eventName);
			Class ctrlClass = this.getClass();
			Method currMethod = ctrlClass.getMethod(methodname, null);
			currMethod.invoke(this, null);
		} 
		catch (Exception e)
		{
				//specify what happens upon exception
				//this exception specifies that methods are not found
				
		}
	}


}
