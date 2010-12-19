package arcade.core.mvc;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import arcade.core.api.Tab;
import arcade.store.gui.StoreTab;

/**
 *
 * @author: 		Jimmy Mu
 * @date:			12-16-10
 * @description:	This class represents a control unit in the Model View Controller.
 * 					Basically, the control unit can add a model and a viewer. It is 
 * 					supplemented with the ability to use the resource bundle
 * 					and call reflection on keys to get methods and call those 
 * 					methods on itself. This is especially useful when the subclass
 * 					have methods that needed to be called. 
 */

public abstract class Controller implements IController{

	private ResourceBundle reflectionMirror;
	
	/**
	 * This is the basic constructor. It takes in a filepath
	 * so that it can use reflection 
	 * @param filepath
	 */
	public Controller(String filepath)
	{
		reflectionMirror = ResourceBundle.getBundle(filepath);		
	}
	

	/**
	 * This method specifies the first actions to perform when the 
	 * controller is created.
	 */
	@Override
	public abstract void initialize();

	/**
	 * This method adds the model associated with the controller.
	 */
	@Override
	public abstract void addModel(IModel model);

	/**
	 * this method adds the viewr associated with the controller.
	 */
	@Override
	public abstract void addViewer(Tab tab); 
	/**
	 * This method uses reflection to process all the 
	 * events from the viewer and the controller.
	 * @param eventName
	 */
	public void processEvent(String eventName) {

		try {
			Class<?> classType = this.getClass();
			String methodname = reflectionMirror.getString(eventName);
			Method currMethod = classType.getMethod(methodname, null);
			currMethod.invoke(this, null);
		} 
		catch (Exception e)
		{
			throw ControlExceptions.METHOD_NOT_FOUND;
		}
	}
	
	/**
	 * This method process the a string of events based on the value
	 * set up by the delim inside the properties file used
	 * for the current resource bundle
	 * @param eventString
	 */
	public void processEventString(String eventString)
	{
		try
		{
			String delim = reflectionMirror.getString("delim");
			String eventList = reflectionMirror.getString(eventString);
			String[] events = eventList.split(delim);
			
			for(String event: events)
			{
				if(event == null)
					continue; 
				
				processEvent(event);
			}
		}
		catch (Exception e)
		{
			throw ControlExceptions.DELIMITER_PROBLEM;
		}	
	}
	
	/**
	 * This method gets a String from a Controller
	 * TODO:comment on this class
	 * @param key
	 * @return
	 */
	public String getString(String key)
	{
		return reflectionMirror.getString(key);
	}


}
