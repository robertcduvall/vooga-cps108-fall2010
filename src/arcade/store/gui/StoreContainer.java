package arcade.store.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.store.control.MainController;


/**
 * 
 * @author: 		Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date: 			12-16-10
 * @description: 	This is the UI for the main store tab inside the Arcade. It creates
 * 					and contains all the subtabs inside itself. (Although we admit this 
 * 					may not be the best design since an object operates as the factory for JPanel
 * 					and also the object itself, we are limited by the fact that Arcade can only
 * 					instantiate the StoreContainer in this manner. Ideally, we would 
 * 					like to have a StoreContainer Factory class that would produce
 * 					the StoreContainer JPanel and pass that JPanel the the Arcade. Given the current
 * 					way Arcade is setup, we can only create the StoreContainer in this manner.
 */
public class StoreContainer extends JPanel implements Tab{

	private static final long serialVersionUID = 1L;
	
	//this is set up because it is impossible to pass this String in 
	//because the Arcade can only create objects that have no parameters
	private static final String SUBTAB_FILE_PATH= "arcade.store.gui.resources.tabList";
	private static final String MAIN_CONTROL_FILE_PATH = "arcade.store.resources.MainController";
	
	private MainController mainController;

	/**
	 * this is the basic constructor for the StoreContainer
	 */
	public StoreContainer() {
		setName("Store");
	}

	/**
	 * getContent returns this object as an UI component to be used by the arcade
	 * @return JComponet
	 */
	public JComponent getContent() {
		return this;
	}

	/**
	 * creatTabs sets up the all the subtabs of the StoreContainer
	 * and set up the event listener for switching between the subtabs
	 * @return
	 */
	private JTabbedPane createTabs() {
		//final is used here because this is the only way that 
		final JTabbedPane tabsPanel = new JTabbedPane();
		
		setUpTabs(tabsPanel);
		setUpTabListener(tabsPanel);
		return tabsPanel;
	}
	
	/**
	 * setUpTabs finds the Strings of all the subtabs that are 
	 * located inside the ResourceTabList properties file, 
	 * instantiate the tabs, and add them to the panel.
	 * @param storeContainer
	 */
	private void setUpTabs(final JTabbedPane storeContainer) {
		
		for (String classname : getTabList()) {
			if (classname.isEmpty())
				continue;
			try {

				StoreTab t = (StoreTab) getObject(classname);
				//t.setResourceBundleFilePath(FILE_PATH);
				String name = ((JComponent) t).getName();	

				mainController.addSubTab(name, t);
				
				//initialize all the start up features of the 
				t.getController().initialize();

				storeContainer.addTab(((JComponent) t).getName(), null, t,
						((JComponent) t).getToolTipText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * setUpTabListener sets the JPanel to listen for changes between
	 * different tabs. This allow different sub-tabs to be updated without
	 * having to manually update the sub-tabs.
	 * @param storeContainer
	 */
	private void setUpTabListener(final JTabbedPane storeContainer) {
		storeContainer.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = storeContainer.getSelectedIndex();
				Component selected = storeContainer.getComponentAt(selectedIndex);
				if(selected instanceof Tab)
					((Tab) selected).refresh();
				
				// Update tab titles
				storeContainer.setTitleAt(selectedIndex,selected.getName());
			}
		});
	}

	/**
	 * This method obtains the list of string of all the subclasses used
	 * for reflection.
	 * @return String array
	 */
	private String[] getTabList() {

		ResourceBundle tabBundle = ResourceBundle.getBundle(SUBTAB_FILE_PATH);
		
		Enumeration<String> iterator = tabBundle.getKeys();
		ArrayList<String> tabs = new ArrayList<String>();

		while(iterator.hasMoreElements())
		{
			String tab = iterator.nextElement();
			tabs.add(tab);
		}
		return tabs.toArray(new String[tabs.size()]);
	}


	/**
	 * This method uses reflection to create the subtabs that are 
	 * that are added to the UI.
	 * @param classname
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Tab getObject(String classname) throws ClassNotFoundException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException {

		Class<?> tabClass = Class.forName(classname);
		Tab newTab = (Tab) tabClass.newInstance();
		return newTab;
	}

	/**
	 * This is a main method that used to test a stable, self-contained
	 * version of the StoreContainer.
	 * @param args
	 */
	public static void main(String[] args) {
		new StoreContainer();
	}

	/**
	 * getController returns the mainController.
	 */
	@Override
	public IController getController() {
		return mainController;
	}

	/**
	 * refresh is not used because it is useful
	 * for our purpose.
	 */
	@Override
	public void refresh() {
		
	}

	@Override
	public void initialize() {
		mainController = new MainController(MAIN_CONTROL_FILE_PATH);
		mainController.addViewer(this);
		setLayout(new BorderLayout());
		this.add(createTabs(), BorderLayout.NORTH);
	}


}
