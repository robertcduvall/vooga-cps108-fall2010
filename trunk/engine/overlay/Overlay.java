package engine.overlay;

import greenfoot.*;

/**
 * Generic Overlay class to be extended by any Overlay class that
 * uses a Stat object to keep track of it's current value.
 * @author Se-Gil Feldsott
 */
public abstract class Overlay extends Actor {

	private Stat myStat;
	
	/**
	 * Returns the width of this actor.
	 * @return
	 */
	public int getWidth()
	{
		return this.getImage().getWidth();
	}
	
	/**
	 * Returs the height of this actor.
	 * @return
	 */
	public int getHeight()
	{
		return this.getImage().getHeight();
	}
	
	
}
