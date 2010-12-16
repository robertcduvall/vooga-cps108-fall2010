package arcade.ads.util;

import java.util.ArrayList;
import java.util.Date;
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
//		if (ads != null
//				&& !ads.isEmpty()
//				&& ads.get(index).getExpireDate()
//						.before(new Date(System.currentTimeMillis()))) {
//			ads.remove(index);
//			index = (index == ads.size() ? 0 : index);
//		}
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

	public void clear() {
		this.ads.clear();
	}

	public void removeExpiredAds() {

		int i = 0;
		while (i < ads.size()) {
			if (ads.get(i).getExpireDate()
					.before(new Date(System.currentTimeMillis()))) {
				ads.remove(i);
			} else {
				i++;
			}
		}

		// for (BasicAd ad : ads) {
		// if (ad.getExpireDate().before(new Date(System.currentTimeMillis())))
		// {
		// ads.remove(ad);
		// }
		// }
	}

	public List<BasicAd> retrieveActiveAds() {
		ArrayList<BasicAd> moreAds = new ArrayList<BasicAd>();
		for (int i=0;i < ads.size();i++) {
			if (ads.get(i).getEffectiveDate()
					.before(new Date(System.currentTimeMillis()))) {
				moreAds.add(ads.get(i));
			}
		}
		return moreAds;
	}

	public void addMoreAds(List<BasicAd> ad) {
		if (ad != null && !ad.isEmpty())
			for (BasicAd a : ad) {
				ads.add(a);
			}
	}
}
