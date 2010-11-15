package vooga.engine.resource.modules;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

public class SoundModule extends ResourceModule{
	
	private static Map<String, String> soundMap = new HashMap<String, String>();

	@Override
	public void clearElements() {
		soundMap.clear();
		
	}

	@Override
	public void loadElements(Collection<Element> elements) {
		for (Element e: elements) {
        	String name = e.getAttribute("name");
        	String path = e.getAttribute("path");
        	loadSound(name, "sounds/" + path);
        }		
	}
	
	/**
	 * Puts a new entry into this Resources's soundMap, with a String key
	 * and a String value.
	 */
	public void loadSound(String key, File file) {
		soundMap.put(key, file.getPath());
	}

	/**
	 * Puts a new entry into this Resources's soundMap, with a String key
	 * and a String value.
	 */
	public void loadSound(String key, String filePath) {
		loadSound(key, new File(getDefaultPath() + filePath));
	}
	
	/**
	 * Returns the String associated with the given Sound label.
	 */
	public static String getSound(String key) {
		return soundMap.get(key);
	}

}
