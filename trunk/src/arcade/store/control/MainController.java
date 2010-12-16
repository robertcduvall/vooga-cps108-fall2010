package arcade.store.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.core.mvc.Controller;
import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;

/**
 * 
 * @author:			Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date: 			12-16-10
 * @description: 	The MainController is the centralized Controller class that organizes
 * 					all the tabs together. It has all the information about its subtabs
 * 					and have references to the StoreContainer and also the StoreModel.
 * 					It provides a potential means of linking different UI's and controllers.
 */

public class MainController extends Controller{

	private StoreModel model;
	private Map<String, Tab> tabs;
	
	public MainController(String filepath) {
		super(filepath);
		model = new StoreModel(this);
		
		//set up the link to the Store
		addModel(model);
		
		tabs = new HashMap<String, Tab>();
	}
	
	/**
	 * addSubTab adds a specific subtab and sets the StoreModel
	 * with the controller of each tabCompoent.
	 * @param name
	 * @param tabComponent
	 */
	public void addSubTab(String name, Tab tabComponent)
	{
		IController control = tabComponent.getController();
		control.addModel(model);
		
		tabs.put(name, tabComponent);
	}
	
	
	/**
	 * This method allows access to a specific tab given that tab's tabName
	 * @param tabName
	 * @return
	 */
	public Tab getTab(String tabName)
	{
		return tabs.get(tabName);
	}
	
}
