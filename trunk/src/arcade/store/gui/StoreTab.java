package arcade.store.gui;

import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.core.api.Tab;
import arcade.core.mvc.Controller;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;

/**
 * @author: 		Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date:			12-16-10
 * @description: 	StoreTab extends JPanel and implements Tab. It contains a resource bundle
 * 					that subclasses of StoreTab can use to extract Strings from a properties
 * 					file.
 */

public class StoreTab extends JPanel implements Tab {

	private static final long serialVersionUID = 1L;
	private ResourceBundle storeTabBundle;

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * refresh updates the content of the Store Tab
	 */
	@Override
	public void refresh() {
		
	}

	/**
	 * 
	 * @param bundle
	 */
	public void setResourceBundleFilePath(String filePath) {
		storeTabBundle = ResourceBundle.getBundle(filePath);
	}

	/**
	 * This method gets a String from a StoreTab
	 * 
	 * @param key
	 *            a String for a variable in a properties file
	 * @return a String from a properties file
	 */
	protected String getString(String key) {
		return storeTabBundle.getString(key);
	}

	/**
	 * This method sets up the control.
	 * 
	 * @param control
	 */
	public void setController(IController control) {

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
