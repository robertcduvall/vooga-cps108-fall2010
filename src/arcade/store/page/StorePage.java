package arcade.store.page;

import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JFrame;

/**
 * 
 * @author Drew Sternsky, Jimmy Mu, Marcus Molchany
 * @date 12-03-10
 * @description This is a bsic store page, the back end version of a generic GUI store page.
 * 			
 */

public abstract class StorePage {

	private Map<String, StorePage> pageLinks;
	private JFrame surface;
	
	public StorePage(JFrame gui)
	{
		pageLinks = new HashMap<String, StorePage>();
		surface = gui;
	}
		
	public void addLink(String pageName, StorePage page)
	{
		pageLinks.put(pageName, page);
	}

	public JFrame getThisJFrame()
	{
		return surface;
	}
	
	public StorePage getPageLink(String linkName)
	{
		if(pageLinks.containsKey(linkName))
			return pageLinks.get(linkName);
		else
			return this;
	}
	
	/**
	 * Adds text the desired text area
	 * @param framework
	 */
	public abstract void paint();
	
	
	/**
	 * specifies what happens when an input event is fired,
	 * i.e. a mouse click or a keyboard press
	 * @param event
	 */
	public abstract void processOnEvent(InputEvent event);
	
	
	
}
