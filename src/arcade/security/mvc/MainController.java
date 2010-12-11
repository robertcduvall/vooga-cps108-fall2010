package arcade.security.mvc;

import java.lang.reflect.Method;
import java.util.ResourceBundle;


/**
 * 
 * @author Jimmy Mu
 * @date 12-09-10
 * @description
 * 
 * 
 * Format:
 * In reflection bundle
 * Control=the class to get the method from
 * 
 * 
 */

public abstract class MainController {

	//the Reflection Bundle is used to get the filepath for an IController associated
	//with a request String. The reflectionTypeBundle contains list of 
	private ResourceBundle reflectionBundle;
	private ResourceBundle reflectionTypeBundle;
	
	private IViewer viewer;
	private IModel model;
	
	public MainController(String reflectionFilePath, String reflectionTypeFilePath)
	{
		try{
			reflectionBundle = ResourceBundle.getBundle(reflectionFilePath);
			reflectionTypeBundle = ResourceBundle.getBundle(reflectionTypeFilePath);
		}
		catch(Exception e)
		{
			throw MVCExceptions.FILE_PATH_ERROR;
		}
	}
	/**
	 * 
	 * @param currentviewer
	 */
	public void setViewer(IViewer currentviewer)
	{
		viewer = currentviewer;
	}
	
	/**
	 * Setting the model and the viewer;
	 * @param currentmodel
	 */
	public void setModel(IModel currentmodel)
	{
		model = currentmodel;
	}
	
	/**
	 * This method takes in a string for a request. The request 
	 * is then used as key to get an IController. The appropriate
	 * The operate method of the IController is then called
	 * to process a specific request on a model or a viewer or both. 
	 * @param request
	 */
	public void processRequest(String request)
	{
		try{
		
			String requestType = reflectionTypeBundle.getString(request);
			
			if(requestType.equals("class"))
			{	//class reflection
				String className = reflectionBundle.getString(request);
			
				IController controller = (IController) Class.forName(className).newInstance();
				controller.operate(model, viewer);
			}
			else
			{	//method reflection
				
				String methodName = reflectionBundle.getString(request);
				
				String controlClassName = reflectionBundle.getString("controller");
				
				Class cls = Class.forName(controlClassName);
				
				Class paratypes[] = new Class[2];
				
				paratypes[0] = viewer.getClass();
				paratypes[1] = model.getClass();
				
				Method method = cls.getMethod(methodName, paratypes);
				
				Object arglist[] = new Object[2];
				arglist[0] = viewer;
				arglist[1] = model;
				
				method.invoke(cls.newInstance(), arglist);
					
			}
		}
		catch(Exception e)
		{
			throw MVCExceptions.INVALID_CONTROLLER_REQUEST;
		}	
	}
	
	/**
	 * This method sets up the gui and the model 
	 * components that the developer would like
	 * to see when the application first loads.
	 */
	public abstract void setUpInitalMVC();
	
}
