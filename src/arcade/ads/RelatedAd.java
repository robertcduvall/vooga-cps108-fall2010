package arcade.ads;

import java.util.ArrayList;
import java.util.List;

public class RelatedAd extends BasicAds {

	private BasicAds ad;
	private List<String> tagList;

	public RelatedAd(BasicAds ad){
		this(ad, new ArrayList<String>());
	}

	public RelatedAd(BasicAds ad, List<String> list){
		this.ad = ad;
		tagList = list;
	}

	public void update(){
		ad.update(endTime);
	}

	public void render(){
		ad.render(null);
	}

	public List<String> getCategories(){
		return tagList;
	}

	public void addCategories(String... tags){
		for (String tag: tags)
			tagList.add(tag);
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return ad.isActive();
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		ad.onClick();
	}

	@Override
	public void onMouseOver() {
		// TODO Auto-generated method stub
		ad.onMouseOver();
	}

	}