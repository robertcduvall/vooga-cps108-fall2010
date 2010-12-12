package arcade.ads.adscontent;

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

public abstract class FlashAds extends BasicAds {

	private Flash flash;

	public FlashAds(String name, Video video) {
		super(name);
		this.flash = flash;
	}

	public FlashAds(String name, Flash flash, int xMin, int xMax, int yMin,
			int yMax) {
		super(name, xMin, xMax, yMin, yMax);

	}

	@Override
	public void onClick() {
		// open a url
	}

	@Override
	public void onMouseOver() {
		// play the flash
		// highlight or scale ads

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		// render img
	}

	@Override
	public abstract boolean isActive();

}
