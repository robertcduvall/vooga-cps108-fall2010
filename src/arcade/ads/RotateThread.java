package arcade.ads;

import java.util.List;

/**
 * This class helps rotate ads
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */

public class RotateThread extends Thread {
	private List<BasicAds> ads;
	private int index;

	public RotateThread(List<BasicAds> ads, int index) {
		this.ads = ads;
		this.index = index;
	}

	/**
	 * Sleep for current ad's duration time, then move on to the next ad.
	 */
	public void run() {
		try {
			Thread.sleep(ads.get(index).getDuration());
			AdsManager.nextAds();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
