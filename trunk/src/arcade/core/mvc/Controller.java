package arcade.core.mvc;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import arcade.core.Tab;
import arcade.store.gui.StoreTab;

/**
 * This class represents a Controller 
 * @author Yijia Mu
 *
 */

public class Controller implements IController{

	private ResourceBundle reflectionMirror;
	
	private IModel model;
	private Tab viewer;
	
	public Controller(String filepath)
	{
		reflectionMirror = ResourceBundle.getBundle(filepath);		
	}
	

	@Override
	public void initialize() {

	}

	@Override
	public void addModel(IModel model) {
		this.model = model;
	}

	@Override
	public void addViewer(Tab tab) {
		viewer = tab;
	}
	
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
