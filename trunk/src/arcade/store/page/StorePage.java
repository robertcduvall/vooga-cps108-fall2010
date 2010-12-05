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
	private Map<String, String> pageInformation;
	private JFrame surface;
	
	public StorePage(JFrame gui)
	{
		pageLinks = new HashMap<String, StorePage>();
		pageInformation = new HashMap<String, String>();
		surface = gui;
	}

	/**
	 * Check for the information inside the information tag
	 * @param key
	 * @return
	 */
	public String getInformation(String key)
	{
		return pageInformation.get(key);
	}
	
	/**
	 * Adds this page information for the parser
	 * @param tag
	 * @param info
	 */
	public void addInformation(String tag, String info)
	{
		pageInformation.put(tag, info);
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
