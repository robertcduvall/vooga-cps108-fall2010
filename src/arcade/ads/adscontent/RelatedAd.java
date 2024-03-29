package arcade.ads.adscontent;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import arcade.ads.adsclassification.IRelatedAds;

/**
 * A general related ad that takes in a BasicAd (ImageAd, VideoAd, etc.) and a list of ads.
 * 
 * @author Kate Yang
 *
 */
public class RelatedAd extends BasicAd implements IRelatedAds{

	private BasicAd ad;
	private List<String> tagList;

	public RelatedAd(BasicAd ad){
		this(ad, new ArrayList<String>());
	}

	public RelatedAd(BasicAd ad, List<String> list){
		super();
		this.ad = ad;
		tagList = list;
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
	public void onLeftClick() {
		ad.onLeftClick();
	}

	@Override
	public void onRightClick() {
		ad.onRightClick();
	}

	@Override
	public void render(Graphics2D g) {
		ad.render(g);
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		ad.render(g,x,y);
	}

	@Override
	public void update(long elapsedTime) {
		ad.update(elapsedTime);
	}
	
	@Override
	public void onMouseOver() {
		ad.onMouseOver();
	}

	@Override
	public void render(JPanel p) {
		ad.render(p);
		
	}

	@Override
	public void render(JPanel p, int x, int y) {
		ad.render(p, x, y);
	}
	
	@Override
	public Date getExpireDate() {
		return this.ad.getExpireDate();
	}; 
	
	@Override
	public Date getEffectiveDate() {
		return this.ad.getEffectiveDate();
	}; 
	
	@Override
	public long getDuration() {
		return this.ad.getDuration();
	}; 

	}