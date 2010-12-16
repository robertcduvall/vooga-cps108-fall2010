package arcade.ads.adsmanager;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.ads.adsclassification.IRelatedAds;
import arcade.ads.adscontent.BasicAd;
import arcade.ads.thread.AdsThread;
import arcade.ads.thread.RotateThread;
import arcade.ads.util.AdsGroup;
import arcade.ads.xml.XMLtoAds;

/**
 * Ads manager helps ads distributor to place ads in their framework. It can add
 * new ads, remove ads, update and render ads. It can also check ads' validity.
 * It can move to the next or the previous ads. It supports fancy operation like
 * ads rotation, flip, etc...
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */

public class AdsManager implements MouseListener {

	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;
	private Date currentDate;
	private Graphics2D gs;
	private AdsThread adsthread;
	private RotateThread thread;
	private static JPanel panel;

	private AdsGroup renderedAdsGroup;

	private AdsGroup activeAdsGroup;

	private AdsGroup toBeActiveAdsGroup;

	/**
	 * Default constructor
	 */
	public AdsManager() {
		renderedAdsGroup = new AdsGroup();
		activeAdsGroup = new AdsGroup();
		toBeActiveAdsGroup = new AdsGroup();
		renderedAdsGroup.setAds(activeAdsGroup.getAds()); // default rendered
															// ads

		adsthread = new AdsThread(this);
		thread = new RotateThread(this);
	}

	/**
	 * Initialize ads pool
	 */
	public AdsManager(JPanel panel) {
		this();
		panel.addMouseListener(this);
		setPanel(panel);
	}

	public void runAdsManager() {
		adsthread.start();
		thread.start();
	}

	/**
	 * Set up displaying area
	 * 
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 */
	public AdsManager(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	/**
	 * rotate ads
	 */
	public void rotate() {
		// System.out.println("rotate");
		thread.setFlag(true);
	}

	public void stoprotate() {
		thread.setFlag(false);
	}

	/**
	 * adjust ads so that it fits in the window
	 */
	public void fitWindow() {

	}

	/**
	 * update ads
	 */
	public void update() {
		renderedAdsGroup.removeExpiredAds();
		renderedAdsGroup.addMoreAds(activeAdsGroup.retrieveActiveAds());
		render();
	}

	/**
	 * render ads
	 */
	public void render() {
		// System.out.println(gs==null);
		if (!renderedAdsGroup.getAds().isEmpty()) {
			// System.out.printlnindex();
			// System.out.println("rendering");
			System.out.println(renderedAdsGroup.getCurrentAd() == null);
			renderedAdsGroup.getCurrentAd().render(panel, panel.getWidth(),
					panel.getHeight());
		}
	}

	/**
	 * retrieve new ads from web server
	 */
	public String retrieve() {
		return null;
	}

	public void setRenderedAds() {
		renderedAdsGroup.clear();
		renderedAdsGroup.setAds(activeAdsGroup.getAds());
	}

	public void setRenderedAds(String... tags) {
		renderedAdsGroup.clear();
		for (String tag : tags)
			for (BasicAd ad : activeAdsGroup.getAds()) {
				if ((ad instanceof IRelatedAds)
						&& ((IRelatedAds) ad).getCategories().contains(tag))
					renderedAdsGroup.add(ad);
			}
	}

	public void setRenderedAds(File file) {
		renderedAdsGroup.setAds(XMLtoAds.convertAds(file));
	}

	public void setActiveAds(File file) {
		activeAdsGroup.setAds(XMLtoAds.convertAds(file));
	}

	public void setRenderedAds(AdsGroup group) {
		renderedAdsGroup.setAds(group.getAds());
	}

	/**
	 * set date
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.currentDate = date;
	}

	/**
	 * get current time
	 * 
	 * @return
	 */
	public Date getTime() {
		return currentDate;
	}

	public void setPanel(JPanel p) {
		this.panel = p;
		this.gs = (Graphics2D) p.getGraphics();
	}

	public AdsGroup getRenderedAdsGroup() {
		return renderedAdsGroup;
	}

	public void setRenderedAdsGroup(AdsGroup renderedAdsGroup) {
		this.renderedAdsGroup = renderedAdsGroup;
	}

	public AdsGroup getActiveAdsGroup() {
		return activeAdsGroup;
	}

	public void setActiveAdsGroup(AdsGroup activeAdsGroup) {
		this.activeAdsGroup = activeAdsGroup;
	}

	public AdsGroup getToBeActiveAdsGroup() {
		return toBeActiveAdsGroup;
	}

	public void setToBeActiveAdsGroup(AdsGroup toBeActiveAdsGroup) {
		this.toBeActiveAdsGroup = toBeActiveAdsGroup;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			renderedAdsGroup.getCurrentAd().onLeftClick();

		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("right button");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static JComponent getAdBoard() {
		return panel;
	}
}
