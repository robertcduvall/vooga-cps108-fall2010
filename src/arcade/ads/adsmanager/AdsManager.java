package arcade.ads.adsmanager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import arcade.ads.adscontent.BasicAd;
import arcade.ads.thread.AdsThread;
import arcade.ads.thread.RotateThread;
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

	public static List<BasicAd> ads;
	public static int index;
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;
	private Time currentTime;
	private Graphics2D gs;
	private AdsThread adsthread;
	RotateThread thread;
	private JPanel panel;

	/**
	 * Default constructor
	 */
	public AdsManager() {
		ads = new ArrayList<BasicAd>();
		adsthread = new AdsThread(this);
		thread = new RotateThread(this);
	}

	/**
	 * Initialize ads pool
	 */
	public AdsManager(JPanel panel) {
		ads = new ArrayList<BasicAd>();
		adsthread = new AdsThread(this);
		thread = new RotateThread(this);
		panel.addMouseListener(this);
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
		if (ads != null)
			ads.remove(0);
	}

	/**
	 * remove the last ad
	 */
	public void removeLast() {
		if (ads != null)
			ads.remove(ads.size() - 1);
	}

	/**
	 * remove a specific ad
	 * 
	 * @param ad
	 */
	public void remove(BasicAd ad) {
		ads.remove(ad);
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
		System.out.println(panel.getHeight());
		render();
	}

	/**
	 * render ads
	 */
	public void render() {
		// System.out.println(gs==null);
		if (!ads.isEmpty()) {
			// System.out.printlnindex();
			// System.out.println("rendering");
			ads.get(index).render(gs, panel.getWidth(), panel.getHeight());
		}
	}

	/**
	 * retrieve new ads from web server
	 */
	public String retrieve() {
		return null;
	}

	public BasicAd getCurrentAd() {
		return ads.get(index);
	}

	/**
	 * move to the next ads
	 * 
	 * @return
	 */
	public static void nextAds() {
		index = (index == ads.size() - 1 ? 0 : index + 1);
	}

	/**
	 * move to the previous ads
	 * 
	 * @return
	 */
	public static void prevAds() {
		index = (index == 0 ? ads.size() - 1 : index - 1);
	}

	/**
	 * set timer
	 * 
	 * @param time
	 */
	public void setTime(Time time) {
		this.currentTime = time;
	}

	/**
	 * get current time
	 * 
	 * @return
	 */
	public Time getTime() {
		return currentTime;
	}

	public void setPanel(JPanel p) {
		this.panel = p;
		this.gs = (Graphics2D) p.getGraphics();
	}

	public void setAds(String file) {
		ads = XMLtoAds.convertAds(file);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

}
