package vooga.engine.overlay;

import com.golden.gamedev.object.Sprite;



/**
 * Takes an OverlayString and places it over the top
 * of another Actor.  It constantly updates the
 * position in case the Actor moves around.
 * 
 * Actor player1 = new Actor();
 * OverlayString playerName = new OverlayString("Player 1");
 * OverlayLabel playerLabel = new OverlayLabel(player1, playerName);
 * 
 * @author Se-Gil Feldsott
 */
public class OverlayLabel extends Sprite {

	/**
	 * Sprite that will have the label displayed above.
	 */
	private Sprite mySprite;
	/**
	 * The string / value to be displayed over the Actor.
	 */
	private OverlayString myOverlayString;
	
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
