package vooga.engine.overlay;

import java.util.Map;

import org.w3c.dom.Element;

import com.golden.gamedev.object.Sprite;



/**
 * Generic Overlay class to be extended by any Overlay class that
 * uses a Stat object to keep track of it's current value.
 * 
 * <pre>To use the Overlay Creator any class that extends Overlay Must have a a constructor that takes in a 
 * Map<String, Sting> and a OverlayTracker, and makes the Overlay.</pre> 
 * @author Se-Gil Feldsott
 */
public abstract class Overlay extends Sprite {

	private Stat myStat;
	
	
	protected void setLocation(Map<String, String> attributes){
		String xLocStr = attributes.get("xLoc");
		int xLoc;
		if(xLocStr == null){
			xLoc = 0;
		}else{
			xLoc = Integer.valueOf(xLocStr);
		}
		String yLocStr = attributes.get("yLoc");
		int yLoc;
		if(yLocStr == null){
			yLoc = 0;
		}else{
			yLoc = Integer.valueOf(yLocStr);
		}
		setLocation(xLoc, yLoc);
	}
	
}
