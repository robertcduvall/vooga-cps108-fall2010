package vooga.engine.overlay;

import java.awt.Graphics2D;
import java.util.*;
import com.golden.gamedev.*;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;

/**
 * The OverlayManager extends SpriteGroup. It lets you create a group of Overlays and provides convenience methods for manipulating the Overlays,
 * relative to each other. For example, you can translate all the overlays or reposition them all at once.
 * 
 * 
 * This example creates an OverlayManager class and bases it at point (50,10). Two
 * overlays are then added to the manager. The overlays will be rendered offset from their given location by (50,10).
 * We then translate both overlays 10 pixels to the right.
 * 
 * </br> <code>
 * </br>
 * OverlayManager scoreGroup = new OverlayManager("scoreGroup",50,10);
 * </br>
 * scoreGroup.add(someOverlay);
 * </br>
 * scoreGroup.add(anotherOverlay);
 * </br>
 * scoreGroup.translateOverlays(10,0);
 * </code>
 * 
 * @author Andrew Brown
 */
public class OverlayManager extends SpriteGroup{

	private double myX;
	private double myY;

	/**
	 * Constructs an OverlayManager that uses the given x and y coordinate as
	 * the offset for all its Overlays.
	 * 
	 * @param name
	 *            The name of the OverlayManager.
	 * @param x
	 *            The x-coordinate of the OverlayManager.
	 * @param y
	 *            The y-coordinate of the OverlayManager.
	 */
	public OverlayManager(String name, double x, double y) {
		super(name);
		myX = x;
		myY = y;
	}

	/**
	 * Calls render on the SpriteGroup contained in the manager.
	 * 
	 * @param g
	 */
//	@Override
//	public void render(Graphics2D g) {
//		for(Sprite s : getSprites()){
//			if(s != null){
//				int newX = (int)(s.getX()+myX);
//				int newY = (int)(s.getY()+myY);
//				s.render(g, newX, newY);
//			}
//		}
//	}

	/**
	 * Translates the OverlayManager by the x and y amounts
	 * given.
	 * 
	 * @param x
	 *            The amount to translate the OverlayManager on the x axis.
	 * @param y
	 *            The amount to translate the OverlayManager on the y axis.
	 */
	public void translateOverlays(int x, int y) {
		myX += x;
		myY += y;
	}

	/**
	 * Changes the position of the OverlayManager.
	 * 
	 * @param x
	 *            The x coordinate of the OverlayManager.
	 * @param y
	 *            The y coordinate of the OverlayManager.
	 */
	public void setOverlayPosition(int x, int y) {
		if (x >= 0 && y >= 0) {
			myX = x;
			myY = y;
		}
	}

}
