package vooga.engine.overlay;

import com.golden.gamedev.*;
import com.golden.gamedev.engine.*;
import com.golden.gamedev.object.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * The OverlayPanel provides a convenient way for users
 * to evenly place up to 5 overlays across the top or
 * bottom of the screen.
 * 
 * OverlayPanel panel = new Overlay(someWorld, true);
 * panel.add(overlay1);
 * panel.add(overlay2);
 * panel.add(overlay3);
 * panel.initialize();
 * @author Se-Gil Feldsott & Andrew Brown
 */
public class OverlayPanel extends OverlayManager{

//	private Collection<Overlay> myOverlays;
	private boolean isOnTop;
	
	/**
	 * Constructs the initial panel with a given world,
	 * and establishing its position on the top or bottom.
	 * @param thisWorld
	 * @param topOrBottom
	 */
	public OverlayPanel(Game thisGame, boolean topOrBottom)
	{
		super(thisGame,0,0);
		isOnTop = topOrBottom;
		myOverlays = new ArrayList<Overlay>();
	}
	
	/**
	 * Adds an overlay to the panel.
	 * @param overlay
	 */
	public void addOverlay(Overlay overlay){
        myOverlays.add(overlay);
	}
	
	/**
	 * This calculates important values in the OverlayPanel
	 * regarding spacing of the Overlays and it also places
	 * the OverlayPanel into the world.
	 */
	public void initialize()
	{
		int numberOfOverlays = myOverlays.size();
		int[] xCoordinates = setXCoordinates();
		int distanceFromEdge = getGame().getHeight()/50;
		int yCoordinate = distanceFromEdge;
		int yOffset = 0;	//The offsets are for in case the Overlay would run
		int xOffset = 0;	//off the screen.
		if(!isOnTop)
			yCoordinate = getGame().getHeight() - distanceFromEdge;
		int i=0;
		for(Overlay overlay : myOverlays)
		{
			overlay.render(overlay.getImage().createGraphics(), 0, yCoordinate);
			
			if(overlay.getHeight()/2 > distanceFromEdge)
				yOffset = overlay.getHeight()/2 - distanceFromEdge + 1;
			if(!isOnTop)
				yOffset*=(-1);
			if(i==0)
				xOffset = (overlay.getWidth()*3)/5;
			else if(i==numberOfOverlays-1)
				xOffset = ((overlay.getWidth()*3)/5)*(-1);
			overlay.setLocation(xCoordinates[i]+xOffset, yCoordinate+yOffset);
			xOffset = 0;
			yOffset = 0;
			i++;
		}
	}
	
	private int[] setXCoordinates()
	{
		int numberOfOverlays = myOverlays.size();
		int[] xCoordinates = new int[numberOfOverlays];
		if(numberOfOverlays == 1)
			xCoordinates[0] = getGame().getWidth()/2;
		else
		{
			int xIncrement = getGame().getWidth() / (numberOfOverlays-1);
			for(int i=0; i < numberOfOverlays; i++)
			{
				xCoordinates[i] = xIncrement * i;		//x-coordinates for the overlays
			}
		}
		return xCoordinates;
	}
	
}