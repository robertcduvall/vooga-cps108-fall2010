package arcade.store.page;

import java.awt.event.InputEvent;
import java.util.List;

import javax.swing.JFrame;

import arcade.store.StoreLibrary;

/**
 * 
 * @author Drew Strensky, Jimmy Mu, Marcus Molchany
 *
 */

public class MainPage extends StorePage{

	public MainPage(JFrame gui) {
		super(gui);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void paint() {
		// How would you able to paint onto the GUI's surface?
		
	}

	@Override
	public void processOnEvent(InputEvent event) {
		// Figure out how events are to be processed
		
	}

	/**
	 * This method figures out how to organize the data based on you want it.
	 * @param pages
	 * @param criteria
	 * @return
	 */
	public List<StorePage> search(List<StorePage> pages, String criteria)
	{
		return StoreLibrary.organize(pages, criteria, this);
	}
	
	public void paintSearchResult()
	{
		//TODO Solve the paint
		
	}
	
	/**
	 * This method paints to its GUI element 
	 * and displays the the link not found message
	 */
	public void paintSearchNotFoundMessage() {
		// TODO Auto-generated method stub
	}
	
}
