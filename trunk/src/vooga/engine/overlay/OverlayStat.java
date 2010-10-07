package vooga.engine.overlay;



import java.awt.Graphics2D;


/**
 * The Overlay stat class to display a String and something the user defines
 * @author Justin Goldsmith
 * 
 * <p>Stat<Integer> stat = new Stat<Integer>(5);</p>
 * <p>OverlayStat overlay = new OverlayStat("score: ", stat);</p>
 * 
 *  <p>All overlays must be updated and rendered, This is the responsibility of the game creator</p>
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
