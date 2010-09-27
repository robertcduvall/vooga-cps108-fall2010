package engine.overlay;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;


/**
 * The Overlay stat class to display a String and something the user defines
 * @author Justin Goldsmith
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
		act();
	}
	
	/**
	 * Creates a OverlyStat Class
	 * @param label String to display
	 * @param tracker Can be a Stat of any type.  Will call the toString of the type to display.
	 */
	public OverlayStat(String label, Stat<?> tracker){
		super(label);
		myGenTracker = tracker;
		act();
	}	

	/**
	 * Creats String that will be displayed
	 */
	@Override
	public void act()
	{
		String track = setStat();
		print(getString() + " " + track);
	}

	private String setStat() {
		if(myIntTracker != null){
			return Integer.toString(myIntTracker.getStat());
		}else{
			return myGenTracker.getStat().toString();
		}
		
	}
	

}
