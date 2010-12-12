package arcade.ads.adscontent;

import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;

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
public abstract class ImageAds extends BasicAds {

	protected Image img;
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;
	
	public ImageAds()
	{
		super();
	}

	public ImageAds(String name, Image img) {
		super(name);
		this.img = img;
	}

	public ImageAds(String name, Image img, int xMin, int xMax, int yMin,
			int yMax, String targetURL) {
		super(name, xMin, xMax, yMin, yMax, targetURL);

	}

	@Override
	public void onClick() {
		//open url
		openBrowser();
	}

	@Override
	public void onMouseOver() {
		// highlight or scale ads

	}


	@Override
	public abstract boolean isActive();

}
