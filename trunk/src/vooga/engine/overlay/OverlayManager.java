package vooga.engine.overlay;

import java.awt.Graphics2D;
import java.util.*;
import com.golden.gamedev.*;

/**
 * The OverlayManager class lets you create a group of Overlays, which can then
 * be manipulated. You translate all the overlays, absolutely position them, or
 * remove them all at once.
 * 
 * This example creates an OverlayManager class and bases it at point (0,0).
 * Two Overlays are then added to the manager. 
 * 
 * </br>
 * <code>
 * </br>
 * OverlayManger manager = new OverlayManger(this,0,0);
 * </br>
 * manager.addOverlay(someOverlay,10,10);
 * </br>
 * manager.addOverlay(anotherOverlay,20,20);
 * </code>
 * 
 * @author Andrew Brown
 */
public class OverlayManager {

	protected Collection<Overlay> myOverlays;
	private Game myGame;
	private double myX;
	private double myY;

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
	public OverlayManager(Game game, double x, double y) {
		myGame = game;
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
			overlay.render(overlay.getImage().createGraphics(), (int)(myX + x), (int)(myY + y));
		}
	}
	/**
	 * Calls update on each of the Overlays contained in the manager.
	 * @param elapsedTime
	 */
	public void update(long elapsedTime){
		for(Overlay o : myOverlays){
			o.update(elapsedTime);
		}
	}
	
	/**
	 * Calls render on each of the Overlays contained in the manager.
	 * @param g
	 */
	public void render(Graphics2D g){
		for(Overlay o : myOverlays){
			o.render(g);
		}
	}

	/**
	 * Translates all the Overlays in the OverlayManager by the x and y amounts
	 * given. If the given translation tries to move the Overlay out of the
	 * Game the Overlay simply stops at the edge of the Game.
	 * 
	 * @param x
	 *            The amount to translate the Overlays on the x axis.
	 * @param y
	 *            The amount to translate the Overlays on the y axis.
	 */
	public void translateOverlays(int x, int y) {
		for (Overlay o : myOverlays) {
			double newX = o.getX() + x;
			double newY = o.getY() + y;
			if (newX < 0)
				newX = 0;
			if (newX > (myGame.getWidth() - 1))
				newX = myGame.getWidth() - 1;
			if (newY < 0)
				newY = 0;
			if (newY > (myGame.getHeight() - 1))
				newY = myGame.getHeight() - 1;
			o.setLocation(newX, newY);
		}
	}

	/**
	 * Absolutely positions all the Overlays in the OverlayManager relative to
	 * the given x,y point.
	 * 
	 * @param x
	 *            The x coordinate at which to anchor the OverlayManager. Must
	 *            be within the boundaries of the Game.
	 * @param y
	 *            The y coordinate at which to anchor the OverlayManager. Must
	 *            be within the boundaries of the Game.
	 */
	public void setOverlayPosition(int x, int y) {
		if (x >= 0 && x <= (myGame.getWidth() - 1) && y >= 0
				&& y <= (myGame.getHeight() - 1)) {
			myX = x;
			myY = y;
		}
	}

	/**
	 * Removes all the Overlays in the OverlayManager from the Game by setting them to not-active.
	 */
	public void removeOverlays() {
		for (Overlay o : myOverlays) {
			o.setActive(false);
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
	 * Returns the Game the OverlayManager draws the Overlays in.
	 * 
	 * @return The Game that the OverlayManager draws the Overlays in.
	 */
	public Game getGame() {
		return myGame;
	}

}
