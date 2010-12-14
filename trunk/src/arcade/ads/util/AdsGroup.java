package arcade.ads.util;

import java.util.ArrayList;
import java.util.List;

import arcade.ads.adscontent.BasicAd;

public class AdsGroup {
	private List<BasicAd> ads;

	private int index;

	public AdsGroup() {
		ads = new ArrayList<BasicAd>();
	}

	/**
	 * add one ad to the rear of the list
	 * 
	 * @param ad
	 */
	public void add(BasicAd ad) {
		ads.add(ad);
	}

	/**
	 * remove the first ad
	 */
	public void removeFirst() {
		if (ads != null && !ads.isEmpty())
			ads.remove(0);
	}

	/**
	 * remove the last ad
	 */
	public void removeLast() {
		if (ads != null && !ads.isEmpty())
			ads.remove(ads.size() - 1);
	}

	/**
	 * remove a specific ad
	 * 
	 * @param ad
	 */
	public void remove(BasicAd ad) {
		if (ads != null && !ads.isEmpty())
			ads.remove(ad);
	}

	/**
	 * return one ad that should be rendered now
	 * 
	 * @return
	 */
	public BasicAd getCurrentAd() {
		if (ads != null && !ads.isEmpty())
			return ads.get(index);
		else
			return null;
	}

	/**
	 * move to the next ads
	 * 
	 * @return
	 */
	public void nextAds() {
		if (ads != null && !ads.isEmpty())
			index = (index == ads.size() - 1 ? 0 : index + 1);
	}

	/**
	 * move to the previous ads
	 * 
	 * @return
	 */
	public void prevAds() {
		if (ads != null && !ads.isEmpty())
			index = (index == 0 ? ads.size() - 1 : index - 1);
	}

	public List<BasicAd> getAds() {
		return ads;
	}

	public void setAds(List<BasicAd> ads) {
		this.ads = ads;
	}
}