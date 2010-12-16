package arcade.store.gui;

import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.core.mvc.Controller;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;

/**
 * StoreTab extends JPanel and implements Tab. It contains a resource bundle
 * that subclasses of StoreTab can use to extract Strings from a properties
 * file.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class StoreTab extends JPanel implements Tab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle storeTabBundle;
	private Controller controller;

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

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
		controller = (Controller) control;
	}

}
