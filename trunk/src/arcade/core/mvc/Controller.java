package arcade.core.mvc;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

/**
 * This class represents a Controller 
 * @author Yijia Mu
 *
 */

public class Controller implements IController{

	private ResourceBundle reflectionMirror;
	private Class<?> classType; 

	public Controller(String filepath)
	{
		reflectionMirror = ResourceBundle.getBundle(filepath);		
		classType = this.getClass();
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
			String methodname = reflectionMirror.getString(eventName);
			Method currMethod = classType.getMethod(methodname, null);
			currMethod.invoke(this, null);
		} 
		catch (Exception e)
		{
				throw ControlExceptions.METHOD_NOT_FOUND;
		}
	}


}
