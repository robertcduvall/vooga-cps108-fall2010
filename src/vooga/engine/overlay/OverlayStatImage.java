package vooga.engine.overlay;


import java.awt.image.BufferedImage;
import java.util.Map;

import com.golden.gamedev.util.ImageUtil;




/**
 * The OverlayStatImage class displays a GreenfootImage defined by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 * 
 * <pre>
 * <p>GreenfootImage image = new GreenfootImage("...");</p>
 * <p>OverlayStatImage overlay = new OverlayStatImage(image, 10, 10);</p>
 * </pre>
 * 
 *  <p>All overlays must be updated and rendered, This is the responsibility of the game creator</p>
 *
 */
public class OverlayStatImage extends Overlay{
	
	

	/**
	 * Creats a Image to be placed in game.
	 * @param image image to display
	 */
	public OverlayStatImage(BufferedImage image){
		setImage(image);
	}
	/**
	 * Creats a Image to be placed in game.
	 * @param image image to display		 
	 * @param width width of image
	 * @param height height of image
	 */
	protected OverlayStatImage(BufferedImage image, int width, int height){
		this(image);
		setImage(ImageUtil.resize(this.getImage(), width, height));
	}
	
	protected OverlayStatImage(Map<String, String> attributes, OverlayTrackerTemp tracker){
		
	}
	
	protected void scale(int width, int height){
		setImage(ImageUtil.resize(this.getImage(), width, height));
	}
	
	@Override
	protected OverlayStatImage clone(){
		return new OverlayStatImage(getImage());
	}
	
	
}
