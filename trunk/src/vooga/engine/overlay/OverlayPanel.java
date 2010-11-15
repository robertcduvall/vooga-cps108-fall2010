package vooga.engine.overlay;

import com.golden.gamedev.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * The OverlayPanel provides a convenient way for users
 * to evenly place overlays across the top or
 * bottom of the screen.  About 3 overlays or less is
 * recommended since OverlayPanel does not take measures
 * to prevent overlapping.
 * 
 * <xmp>
 * OverlayBar	overlay1 = new OverlayBar(stat1,100);
 * OverlayIcon	overlay2 = new OverlayIcon(stat2, image, "Lives: ");
 * OverlayStat	overlay3 = new OverlayStat("Score: ", stat3);
 * 
 * OverlayPanel panel = new Overlay("Overlays", someGame, true);
 * panel.add(overlay1);
 * panel.add(overlay2);
 * panel.add(overlay3);
 * panel.initialize();
 * playField.add(panel);
 * </xmp>
 * @author Se-Gil Feldsott & Andrew Brown
 */
public class OverlayPanel extends SpriteGroup{

	private static final double OVERLAY_OFFSET = 1.7;
	private static final int NEGATIVE = -1;
	private static final int EXTRA_PIXEL = 1;
	private static final int MIDDLE = 2;
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
		super(name);
		isOnTop = topOrBottom;
		screenWidth = game.getWidth();
		screenHeight = game.getHeight();
	}
	
	/**
	 * This sets up the panel by calculating important values
	 * in the OverlayPanel regarding spacing of the Overlays
	 * and it also places the OverlayPanel into the world.
	 * It also builds the Overlay SpriteGroup.
	 */
	public void initialize()
	{
		int numberOfOverlays = this.getSize();
		int[] xCoordinates = setXCoordinates(numberOfOverlays);
		int distanceFromEdge = twoPercentOfScreenHeight();
		int yCoordinate = distanceFromEdge;
		int yOffset = 0;	//The offsets are for in case the Overlay would run
		int xOffset = 0;	//off the screen.
		if(!isOnTop)
			yCoordinate = screenHeight - distanceFromEdge;
		int i=0;
		for(Sprite overlay : this.getSprites())
		{
			if(overlay == null)
			{
				i++;
				this.remove(overlay);
				continue;
			}
			xOffset = NEGATIVE*getHalf(overlay.getWidth());
			
			if(getHalf(overlay.getHeight()) > distanceFromEdge)
				yOffset = getHalf(overlay.getHeight()) - distanceFromEdge + EXTRA_PIXEL;
			if(!isOnTop)
				yOffset*=NEGATIVE;
			if(i==0 && moreThanOneOverlay(numberOfOverlays))
				xOffset = 0;
			else if(notLastOverlay(numberOfOverlays, i) &&
					moreThanOneOverlay(numberOfOverlays))
				xOffset -= overlay.getWidth()*OVERLAY_OFFSET;
			overlay.setLocation(xCoordinates[i]+xOffset, yCoordinate+yOffset);
			xOffset = 0;
			yOffset = 0;
			
			i++;
		}
	}

	private boolean moreThanOneOverlay(int numberOfOverlays) {
		return numberOfOverlays > 1;
	}

	private boolean notLastOverlay(int numberOfOverlays, int i) {
		return i==numberOfOverlays-1;
	}

	private int twoPercentOfScreenHeight() {
		return screenHeight/50;
	}
	
	/**
	 * Calculates the x-coordinates for the horizontal
	 * spacing of the overlays in OverlayPanel.
	 * @param numberOfOverlays
	 * @return xCoordinates
	 */
	private int[] setXCoordinates(int numberOfOverlays)
	{
		int[] xCoordinates = new int[numberOfOverlays];
		if(numberOfOverlays == 1)
			xCoordinates[0] = screenWidth/MIDDLE;
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
	
	/**
	 * Returns half of the passed value.
	 * @param number
	 * @return number / 2
	 */
	private int getHalf(int number)
	{
		return number / 2;
	}
}
