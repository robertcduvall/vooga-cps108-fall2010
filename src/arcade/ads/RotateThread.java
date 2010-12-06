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

	private AdsManager manager;
	private boolean flag;

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
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					if (!manager.ads.isEmpty()) {
						System.out.println("start");
						Thread.sleep(manager.ads.get(manager.index)
								.getDuration());
						System.out.println("end");
						AdsManager.nextAds();
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
