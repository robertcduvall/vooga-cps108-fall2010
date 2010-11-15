package vooga.engine.overlay;



import java.awt.Graphics2D;
import java.util.Map;


/**
 * The Overlay stat class to display a String and something the user defines
 * @author Justin Goldsmith
 * 
 * <pre>
 * <p>Stat<Integer> stat = new Stat<Integer>(5);</p>
 * <p>OverlayStat overlay = new OverlayStat("score: ", stat);</p>
 * </pre>
 * 
 *  <p>All overlays must be updated and rendered, This is the responsibility of the game creator</p>
 */


public class OverlayStat extends OverlayString {
	
	private Stat<?> myGenTracker;
	
	/**
	 * Creates a OverlyStat Class
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
