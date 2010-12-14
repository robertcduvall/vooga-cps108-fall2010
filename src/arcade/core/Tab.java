package arcade.core;

import java.awt.*;
import java.awt.Event.*;

import javax.swing.*;

import arcade.core.mvc.IController;


/**
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
@SuppressWarnings("serial")
public abstract interface Tab {
	
	public abstract JComponent getContent();

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
	 * when 
	 */
	public void refresh();
}
