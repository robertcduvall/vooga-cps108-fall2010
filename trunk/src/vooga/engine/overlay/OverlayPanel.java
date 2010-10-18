package vooga.engine.overlay;

import com.golden.gamedev.*;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;

/**
 * The OverlayPanel provides a convenient way for users
 * to evenly place up to 5 overlays across the top or
 * bottom of the screen.
 * 
 * OverlayPanel panel = new Overlay(someGame, true);
 * panel.addOverlay(overlay1);
 * panel.addOverlay(overlay2);
 * panel.addOverlay(overlay3);
 * panel.initialize();
 * playField.addGroup(panel.getOverlayGroup());
 * @author Se-Gil Feldsott & Andrew Brown
 */
public class OverlayPanel extends OverlayManager{

	private boolean isOnTop;
	private int screenHeight;
	private int screenWidth;
	
	/**
	 * Constructs the initial panel with a given world,
	 * and establishing its position on the top or bottom.
	 * @param game
	 * @param topOrBottom
	 */
	public OverlayPanel(String name, Game game, boolean topOrBottom)
	{
		super(name, 0, 0);
		isOnTop = topOrBottom;
		screenWidth = game.getWidth();
		screenHeight = game.getHeight();
	}
	
	/**
	 * This calculates important values in the OverlayPanel
	 * regarding spacing of the Overlays and it also places
	 * the OverlayPanel into the world.  It also builds the
	 * Overlay SpriteGroup.
	 */
	public void initialize()
	{
		int numberOfOverlays = this.getSize();
		int[] xCoordinates = setXCoordinates(numberOfOverlays);
		int distanceFromEdge = screenHeight/50;
		int yCoordinate = distanceFromEdge;
		int yOffset = 0;	//The offsets are for in case the Overlay would run
		int xOffset = 0;	//off the screen.
		if(!isOnTop)
			yCoordinate = screenHeight - distanceFromEdge;
		int i=0;
		for(Overlay overlay : (Overlay[])this.getSprites())
		{
			xOffset = (-1)*overlay.getWidth()/2;
			
			if(overlay.getHeight()/2 > distanceFromEdge)
				yOffset = overlay.getHeight()/2 - distanceFromEdge + 1;
			if(!isOnTop)
				yOffset*=(-1);
			if(i==0 && numberOfOverlays > 1)
//				xOffset = (overlay.getWidth()*3)/5;
				xOffset = 0;
			else if(i==numberOfOverlays-1 && numberOfOverlays > 1)
//				xOffset = ((overlay.getWidth()*3)/5)*(-1);
				xOffset += overlay.getWidth()/2;
//			overlay.setLocation(xCoordinates[i]+xOffset, yCoordinate+yOffset);
//			overlay.render(overlay.getImage().createGraphics(), xCoordinates[i]+xOffset, yCoordinate+yOffset);
			overlay.setX(xCoordinates[i]+xOffset);
			overlay.setY(yCoordinate+yOffset);
			xOffset = 0;
			yOffset = 0;
			
			i++;
		}
	}
	
	private int[] setXCoordinates(int numberOfOverlays)
	{
		int[] xCoordinates = new int[numberOfOverlays];
		if(numberOfOverlays == 1)
			xCoordinates[0] = screenWidth/2;
		else
		{
			int xIncrement = screenWidth / (numberOfOverlays-1);
			for(int i=0; i < numberOfOverlays; i++)
			{
				xCoordinates[i] = xIncrement * i;		//x-coordinates for the overlays
			}
		}
		return xCoordinates;
	}
	
}
