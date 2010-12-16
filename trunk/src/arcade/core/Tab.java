package arcade.core;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.core.mvc.IController;


/**
 * @author Derek Zhou, Yang Su, Aaron Choi, Jimmy Mu
 * 
 */
@SuppressWarnings("serial")
public abstract interface Tab {
	
//	public abstract JComponent getContent();

	/**
	 * This method returns a controller interface
	 * associated with the this JComponent. This 
	 * is used for the GUI to communicate with some
	 * intermediate for the back-end model.
	 * @return
	 */
	public IController getController();
	
	/**
	 * This method is needed to refresh the tab
	 * when ever a tab is clicked
	 */
	public void refresh();
}
