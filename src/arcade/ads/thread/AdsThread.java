package arcade.ads.thread;

import arcade.ads.adsmanager.AdsManager;

/**
 * This thread is used for handling ads. We make it a new thread so that it
 * keeps updating while user can perform other actions.
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */
public class AdsThread extends Thread {

	private AdsManager manager;

	/**
	 * create a new thread for displaying ads
	 * 
	 * @param manager
	 */
	public AdsThread(AdsManager manager) {
		this.manager = manager;
	}

	/**
	 * run ads thread
	 */
	public void run() {
		while (true) {
			manager.update();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
