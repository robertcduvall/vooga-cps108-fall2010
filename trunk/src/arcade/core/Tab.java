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
public abstract class Tab extends JPanel {
	
	public abstract JComponent getContent();

	/**
	 * This method returns a controller interface
	 * associated with the this JComponent. This 
	 * is used for the GUI to communicate with some
	 * intermediate for the back-end model.
	 * @return
	 */
	public IController getController()
	{
		return null;
	}
	
	/**
	 * This method is needed to refresh the tab
	 * when 
	 */
	public void refresh()
	{
		
	}
}
