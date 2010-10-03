package vooga.engine.overlay;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import com.golden.gamedev.util.ImageUtil;
import com.sun.media.sound.Toolkit;



/**
 * The OverlayStatImage class displays a GreenfootImage defined by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 * 
 * GreenfootImage image = new GreenfootImage("...");
 * OverlayStatImage overlay = new OverlayStatImage(image, 10, 10);
 *
 */
public class OverlayStatImage extends Overlay{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public OverlayStatImage(BufferedImage image, int width, int height){
		this(image);
		setImage(ImageUtil.resize(this.getImage(), width, height));
	}
	
	/**
	 * Creats a Image to be placed in game.
	 * @param location file location of image to be displayed
	 * @throws MalformedURLException 
	 */
	public OverlayStatImage(String location) throws MalformedURLException{
		URL url = new URL(location);
		setImage(ImageUtil.getImage(url));
	}
	
	/**
	 * Creats a Image to be placed in game.
	 * @param location file location of image to be displayed
	 * @param width width of image to be displayed
	 * @param height height of image to be displayed
	 * @throws MalformedURLException 
	 */
	public OverlayStatImage(String location, int width, int height) throws MalformedURLException{
		this(location);
		setImage(ImageUtil.resize(this.getImage(), width, height));
	}
	
}
