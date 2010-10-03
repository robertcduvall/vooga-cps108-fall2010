package vooga.engine.overlay;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;


/**
 * The Overlay stat class to display a String and something the user defines
 * @author Justin Goldsmith
 * 
 * Stat<Integer> stat = new Stat<Integer>(5);
 * OverlayStat overlay = new OverlayStat("score: ", stat);
 */


public class OverlayStat extends OverlayString {
	
	private StatInt myIntTracker;
	private Stat<?> myGenTracker;
	//private Stat myGenTracker;
	
	/**
	 * Creates a OverlyStat Class
	 * @param label String to display
	 * @param tracker Int specific stat
	 */
	public OverlayStat(String label, StatInt tracker){
		super(label);
		myIntTracker = tracker;
	}
	
	/**
	 * Creates a OverlyStat Class
	 * @param label String to display
	 * @param tracker Can be a Stat of any type.  Will call the toString of the type to display.
	 */
	public OverlayStat(String label, Stat<?> tracker){
		super(label);
		myGenTracker = tracker;
	}	

	/**
	 * Creats String that will be displayed
	 */
	public void update(long t)
	{
		String track = setStat();
		print(getString() + " " + track);
	}

	private String setStat() {
		if(myIntTracker != null){
			return Integer.toString(myIntTracker.getStat());
		}else if(myGenTracker != null){
			return myGenTracker.getStat().toString();
		}else {
			return "";
		}
		
	}
	

}
