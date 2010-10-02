package vooga.engine.overlay;

import java.util.*;
import greenfoot.*;

/**
 * The OverlayManager class lets you create a group of Overlays, which can then
 * be manipulated. You translate all the overlays, absolutely position them, or
 * remove them all at once.
 * 
 * This example creates an OverlayManager from the World class and bases it at point (0,0).
 * Two Overlays are then added to the manager. 
 * 
 * OverlayManger manager = new OverlayManger(this,0,0);
 * manager.addOverlay(someOverlay,10,10);
 * manager.addOverlay(anotherOverlay,20,20);
 * 
 * @author Andrew Brown
 */
public class OverlayManager {

	private Collection<Overlay> myOverlays;
	private World myWorld;
	private int myX;
	private int myY;

	/**
	 * Constructs an OverlayManager that uses the given x and y coordinate as
	 * the offset for all its Overlays.
	 * 
	 * @param world
	 *            The world the OverlayManager will draw the Overlays in.
	 * @param x
	 *            The x offset of all the Overlays (you can think of it as the x
	 *            coordinate of the top-left corner of the OverlayManager).
	 * @param y
	 *            The y offset of all the Overlays (you can think of it as the y
	 *            coordinate of the top-left corner of the OverlayManager).
	 */
	public OverlayManager(World world, int x, int y) {
		myWorld = world;
		myX = x;
		myY = y;
		myOverlays = new HashSet<Overlay>();
	}

	/**
	 * Add the given Overlay to OverlayManager's group. The x and y coordinates
	 * are the offset from the x and y coordinates of the OverlayManager.
	 * 
	 * @param overlay
	 *            The overlay to add to the OverlayManager's group.
	 * @param x
	 *            The x coordinate of the overlay relative to the x coordinate
	 *            of the OverlayManager. Must be greater than 0.
	 * @param y
	 *            The y coordinate of the overlay relative to the y coordinate
	 *            of the OverlayManager. Must be greater than 0.
	 */
	public void addOverlay(Overlay overlay, int x, int y) {
		if (overlay != null && x > 0 && y > 0) {
			myOverlays.add(overlay);
			myWorld.addObject(overlay, myX + x, myY + y);
		}
	}

	/**
	 * Translates all the Overlays in the OverlayManager by the x and y amounts
	 * given. If the given translation tries to move the Overlay out of the
	 * World the Overlay simply stops at the edge of the World.
	 * 
	 * @param x
	 *            The amount to translate the Overlays on the x axis.
	 * @param y
	 *            The amount to translate the Overlays on the y axis.
	 */
	public void translateOverlays(int x, int y) {
		for (Overlay o : myOverlays) {
			int newX = o.getX() + x;
			int newY = o.getY() + y;
			if (newX < 0)
				newX = 0;
			if (newX > (myWorld.getWidth() - 1))
				newX = myWorld.getWidth() - 1;
			if (newY < 0)
				newY = 0;
			if (newY > (myWorld.getHeight() - 1))
				newY = myWorld.getHeight() - 1;
			o.setLocation(newX, newY);
		}
	}

	/**
	 * Absolutely positions all the Overlays in the OverlayManager relative to
	 * the given x,y point.
	 * 
	 * @param x
	 *            The x coordinate at which to anchor the OverlayManager. Must
	 *            be within the boundaries of the World.
	 * @param y
	 *            The y coordinate at which to anchor the OverlayManager. Must
	 *            be within the boundaries of the World.
	 */
	public void setOverlayPosition(int x, int y) {
		if (x >= 0 && x <= (myWorld.getWidth() - 1) && y >= 0
				&& y <= (myWorld.getHeight() - 1)) {
			myX = x;
			myY = y;
		}
	}

	/**
	 * Removes all the Overlays in the OverlayManager from the World.
	 */
	public void removeOverlays() {
		for (Overlay o : myOverlays) {
			myWorld.removeObject(o);
		}
	}

	/**
	 * Returns all the Overlays in the OverlayManager.
	 * 
	 * @return Returns the Set of Overlays in the OverlayManager.
	 */
	public Collection<Overlay> getOverlays() {
		return myOverlays;
	}

	/**
	 * Returns the World the OverlayManager draws the Overlays in.
	 * 
	 * @return The World that the OverlayManager draws the Overlays in.
	 */
	public World getWorld() {
		return myWorld;
	}

}
