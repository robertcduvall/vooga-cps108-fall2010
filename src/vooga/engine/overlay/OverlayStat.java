package vooga.engine.overlay;



import java.awt.Graphics2D;
import java.util.Map;


/**
 * The Overlay stat class to display a String and something the user defines
 * 
 * For an example see <A HREF="OverlayExample.html">OverlayExample.java</A>.
 * 
 * @author Justin Goldsmith
 * 
 */


public class OverlayStat extends OverlayString {
	
	private Stat<?> myGenTracker;
	
	/**
	 * Creates a OverlayStat Class
	 * @param label String to display
	 * @param tracker Can be a Stat of any type.  Will call the toString of the type to display.
	 */
	public OverlayStat(String label, Stat<?> tracker){
		super(label);
		myGenTracker = tracker;
	}	
	
	
	public OverlayStat(Map<String, String> attributes, OverlayTracker tracker){
		super(attributes, tracker);
		String statLoc = attributes.get("stat");
		myGenTracker = tracker.getStat(statLoc);		
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
		if(myGenTracker != null){
			return myGenTracker.getStat().toString();
		}else {
			return "";
		}
		
	}

	

}
