package vooga.engine.overlay;


import java.awt.image.BufferedImage;
import java.util.Map;

import vooga.engine.resource.Resources;

import com.golden.gamedev.util.ImageUtil;




/**
 * The OverlayStatImage class displays a GreenfootImage defined by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 * 
 * <pre>
 * OverlayStatImage overlay = new OverlayStatImage(Resources.getImage("someName"), 10, 10);
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
	 * Creates a Image to be placed in game.
	 * @param image image to display		 
	 * @param width width of image
	 * @param height height of image
	 */
	protected OverlayStatImage(BufferedImage image, int width, int height){
		this(image);
		setImage(ImageUtil.resize(this.getImage(), width, height));
	}
	
	public OverlayStatImage(Map<String, String> attributes, OverlayTracker tracker){
		this(Resources.getImage(attributes.get("image")));
		String width = attributes.get("width");
		String height = attributes.get("height");
		OverlayStatImage osi;
		if(width!=null && height!=null){
			setImage(ImageUtil.resize(this.getImage(), Integer.valueOf(width), Integer.valueOf(height)));
		}
		setLocation(attributes);
	}
	
	protected void scale(int width, int height){
		setImage(ImageUtil.resize(this.getImage(), width, height));
	}
	
	@Override
	protected OverlayStatImage clone(){
		return new OverlayStatImage(getImage());
	}
	
	
}
