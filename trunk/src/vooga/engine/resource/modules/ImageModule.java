package vooga.engine.resource.modules;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import vooga.engine.core.Game;

/**
 * ResourceModule designed to track Images which are 
 * stored as BufferedImages. Depends on the Game class 
 * to load image files as BufferedImages.
 * 
 * @author Daniel Koverman
 *
 */
public class ImageModule extends ResourceModule{
	
	
	private Map<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();

	@Override
	public void loadElements(Collection<Element> elements) {
		for (Element e: elements) {
        	String name = e.getAttribute("name");
        	String path = e.getAttribute("path");
        	loadImage(name, "images/" + path);
        }		
	}
	
	/**
	 * Puts a new entry into this Resources's imageMap, with a String key
	 * and a BufferedImage[] value. This method is used when a single image is
	 * being put into the map.
	 */
	public void loadImage(String key, File file) {
		imageMap.put(key,getGame().getImage(file.getPath()));
	}

	/**
	 * Puts a new entry into this Resources's imageMap, with a String key
	 * and a BufferedImage[] value. This method is used when a single image is
	 * being put into the map.
	 */
	public void loadImage(String key, String filePath) {
		loadImage(key, new File(getDefaultPath() + filePath));
	}
	
	public BufferedImage getImage(String key) {
		return imageMap.get(key);
	}

	@Override
	public void clearElements() {
		imageMap.clear();
		
	}

}
