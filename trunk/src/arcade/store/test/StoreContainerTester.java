package arcade.store.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import arcade.store.gui.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.core.api.Tab;
import arcade.core.mvc.IController;
import arcade.store.control.MainController;

public class StoreContainerTester {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FILE_PATH = "arcade.store.gui.resources.storeTabProperties";

	private MainController mainController;

	private ResourceBundle tabBundle = ResourceBundle
	.getBundle("arcade.store.gui.resources.tabList");


	public StoreContainerTester() {
		mainController = new MainController("arcade.store.gui.resources.tabList");
		JFrame frame = new JFrame();
		frame.add(createTabs());
		frame.setVisible(true);
		frame.setSize(800, 500);

	}


	private JTabbedPane createTabs() {

		final JTabbedPane everything = new JTabbedPane();

		for (String classname : getTabList()) {
			if (classname.isEmpty())
				continue;
			try {

				StoreTab t = (StoreTab) getObject(classname);
				t.setResourceBundleFilePath(FILE_PATH);
				String name = ((JComponent) t).getName();	

				mainController.addSubTab(name, t);
				
				//initialize all the start up features of the 
				t.getController().initialize();

				everything.addTab(((JComponent) t).getName(), null, t,
						((JComponent) t).getToolTipText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		everything.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = everything.getSelectedIndex();
				Component selected = everything.getComponentAt(selectedIndex);
				if(selected instanceof Tab)
					((Tab) selected).refresh();
				// Update tab titles
				everything.setTitleAt(selectedIndex,selected.getName());
			}
		});
		return everything;

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
		new StoreContainerTester();
	}

	
	

}
