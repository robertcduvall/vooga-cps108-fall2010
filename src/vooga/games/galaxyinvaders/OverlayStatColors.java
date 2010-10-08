package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFontManager;

import vooga.engine.overlay.*;

public class OverlayStatColors extends OverlayString {


	private StatInt myIntTracker;
	private Stat<?> myGenTracker;
	//private Stat myGenTracker;

	/**
	 * Creates a OverlyStat Class
	 * @param label String to display
	 * @param tracker Int specific stat
	 */
	public OverlayStatColors(String label, StatInt tracker){
		super(label);
		myIntTracker = tracker;
	}
	
	public OverlayStatColors(String str, StatInt tracker, Font font, Color color){
		super(str, font, color);
		myIntTracker = tracker;	
	}

	/**
	 * Creates a OverlyStat Class
	 * @param label String to display
	 * @param tracker Can be a Stat of any type.  Will call the toString of the type to display.
	 */
	public OverlayStatColors(String label, Stat<?> tracker){
		super(label);
		myGenTracker = tracker;
	}	

	/**
	 * Used to render to the screen.
	 * @param g Graphic to render image to.
	 */
	@Override
	public void render(Graphics2D g){
		String track = setStat();
		print(getString() + " " + track, g);
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

