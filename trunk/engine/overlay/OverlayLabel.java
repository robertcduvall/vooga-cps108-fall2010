package engine.overlay;

import greenfoot.*;

/**
 * @author Se-Gil Feldsott
 * Takes an OverlayString and places it over the top
 * of another Actor.  It constantly updates the
 * position in case 
 */
public class OverlayLabel extends Actor {

	private Actor myActor;
	/**
	 * The string / value to be displayed over the Actor.
	 */
	private OverlayString myOverlayString;
	
	public OverlayLabel(Actor actor, OverlayString overlayString)
	{
		myActor = actor;
		myOverlayString = overlayString;
	}

	public void act()
	{
		int xCoordinate = myActor.getX();
		//Make the label clear the top of the image of the Actor
		int yCoordinate = myActor.getY()+(myActor.getImage().getHeight()*3)/5;
		myOverlayString.setLocation(xCoordinate, yCoordinate);
	}
	
}
