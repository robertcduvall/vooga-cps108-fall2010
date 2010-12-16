package arcade.ads.thread;

import java.util.List;

import arcade.ads.adsmanager.AdsManager;

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

	private AdsManager manager;
	private boolean flag = true;

	public RotateThread(AdsManager manager) {
		this.manager = manager;
	}

	/**
	 * Sleep for current ad's duration time, then move on to the next ad.
	 */
	public void run() {
		while (true) {
			if (!flag) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					if (!manager.getRenderedAdsGroup().getAds().isEmpty()) {
						Thread.sleep(manager.getRenderedAdsGroup().getCurrentAd().getDuration());
						manager.getRenderedAdsGroup().nextAds();
						manager.render();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
