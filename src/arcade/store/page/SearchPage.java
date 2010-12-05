package arcade.store.page;

import java.awt.event.InputEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import arcade.store.StoreLibrary;

public class SearchPage extends StorePage{

	private ResourceBundle searchBundle = ResourceBundle.getBundle("resources.SearchPage");
	
	public SearchPage(JFrame gui) {
		super(gui);
	}
	
	private boolean linkExist(String linkName)
	{
		return StoreLibrary.getPage(linkName) != null;
	}
	
	
	public StorePage getStorePage(String linkName){
		
		if(!linkExist(linkName))
		{
			paintLinkNotFoundMessage();
			return this;
		}
		
		return StoreLibrary.getPage(linkName);
	}
	
	/**
	 * This method paints to its GUI element 
	 * and displays the the link not found message
	 */
	private void paintLinkNotFoundMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processOnEvent(InputEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	

}
