package arcade.ads;

import java.util.List;

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
