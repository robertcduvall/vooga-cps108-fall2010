package arcade.ads.adsclassification;

import java.util.List;

/**
 * This is simple class will provide general functionality for all ads, such as
 * name, start and end date, maximum vies, onClick, onMouseOver, update, and
 * render methods. We feel like these are the very basic methods and ideas that
 * every single ad is going to need.
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */

public interface IRelatedAds {

	/**
	 * get ads' categories
	 * 
	 * @return
	 */
	public List<String> getCategories();

	/**
	 * add ads' categories
	 * 
	 * @param categ
	 * @return
	 */
	public void addCategories(String...tag);
}
