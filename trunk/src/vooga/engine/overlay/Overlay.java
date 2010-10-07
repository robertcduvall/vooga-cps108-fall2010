package vooga.engine.overlay;

import com.golden.gamedev.object.Sprite;



/**
 * Generic Overlay class to be extended by any Overlay class that
 * uses a Stat object to keep track of it's current value.
 * @author Se-Gil Feldsott
 */
public abstract class Overlay extends Sprite {

	private Stat myStat;
	
}
