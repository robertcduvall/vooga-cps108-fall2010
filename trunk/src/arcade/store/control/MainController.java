package arcade.store.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;


public class MainController implements IController{

	private StoreModel model;
	private Map<String, Tab> tabs;
	
	
	public MainController() {
		model = new StoreModel(this);
		tabs = new HashMap<String, Tab>();
	}
	
	public void addSubTab(String name, Tab tabComponent)
	{
		IController control = tabComponent.getController();
		control.addModel(model);
		
		tabs.put(name, tabComponent);
	}
	
	@Override
	public void addModel(IModel model) {
		
	}

	@Override
	public void addViewer(Tab viewer) {
		
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
