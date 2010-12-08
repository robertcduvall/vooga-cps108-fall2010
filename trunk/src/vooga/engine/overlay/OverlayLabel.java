package vooga.engine.overlay;

import java.awt.Color;

import com.golden.gamedev.object.Sprite;



/**
 * Takes an OverlayString and places it over the top
 * of another Sprite.  It constantly updates the
 * position in case the Sprite moves around. This could be
 * used to display a player's name above his character.
 * 
 * @author Se-Gil Feldsott
 */
public class OverlayLabel extends Sprite {

	/**
	 * Sprite that will have the label displayed above.
	 */
	private Sprite mySprite;
	/**
	 * The string / value to be displayed over the Sprite.
	 */
	private OverlayString myOverlayString;
	
	public OverlayLabel(Sprite spr, String string, Color color)
	{
		this(spr, new OverlayString(string, color));
	}
	
	public OverlayLabel(Sprite sprite, OverlayString overlayString)
	{
		mySprite = sprite;
		myOverlayString = overlayString;
	}

	public void act()
	{
		double xCoordinate = mySprite.getX();
		//Make the label clear the top of the image of the Actor
		double yCoordinate = getYCoordinateAboveSprite();
		myOverlayString.setLocation(xCoordinate, yCoordinate);
	}

	private double getYCoordinateAboveSprite() {
		return mySprite.getY()+(mySprite.getHeight()*3)/5 + myOverlayString.getHeight()/2;
	}
	
}
