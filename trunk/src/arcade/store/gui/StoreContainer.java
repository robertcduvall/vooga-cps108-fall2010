package arcade.store.gui;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.control.MainController;
import arcade.store.gui.tabs.MainPageTab;

public class StoreContainer extends JPanel implements Tab{

	MainController mainController;
	
	private ResourceBundle tabBundle = ResourceBundle
	.getBundle("arcade.store.gui.resources.tabList");
	
	
	public StoreContainer() {
		mainController = new MainController();

		setName("Store");
		add(new MainPageTab());
//		JFrame frame = new JFrame();
//		frame.add(	getContent());
//		frame.setSize(700, 500);
//	
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//this method sets up the first tab
//		controller.setMainTabView(this);
	}
	
//	@Override
	public JComponent getContent() {
		return this;
	}
	
	private JTabbedPane createTabs() {
		JTabbedPane alltabs = new JTabbedPane();
		// JPanel main = createArcadeView();
		// everything.addTab("Arcade", null, main, "Arcade Main View");
		
		for (String classname : getTabList()) {
			if (classname.isEmpty())
				continue;
			try {

				Tab t = (Tab) getObject(classname);
				String name = ((JComponent) t).getName();	
				
				mainController.addSubTab(name, t);
				
				//initialize all the start up features of the 
				t.getController().initialize();
				
				alltabs.addTab(((JComponent) t).getName(), null, t.getContent(),
						((JComponent) t).getToolTipText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return alltabs;
	}


	
	private String[] getTabList() {
		
		Enumeration<String> iterator = tabBundle.getKeys();
		ArrayList<String> tabs = new ArrayList<String>();
		
		while(iterator.hasMoreElements())
		{
			String tab = iterator.nextElement();
			tabs.add(tab);
		}
		return tabs.toArray(new String[tabs.size()]);
	}


	private Tab getObject(String classname) throws ClassNotFoundException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException {
	
		Class<?> tabClass = Class.forName(classname);
		Tab newTab = (Tab) tabClass.newInstance();
		return newTab;
	}
	
	public static void main(String[] args) {
		new StoreContainer();
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
