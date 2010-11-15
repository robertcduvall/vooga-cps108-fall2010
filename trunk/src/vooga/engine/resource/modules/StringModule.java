package vooga.engine.resource.modules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

/**
 * ResourceModule designed to track Strings which act 
 * as constants throughout the game.
 * 
 * @author Daniel Koverman
 *
 */
public class StringModule extends ResourceModule{
	private static Map<String, String> stringMap = new HashMap<String, String>();

	@Override
	public void clearElements() {
		stringMap.clear();
		
	}

	@Override
	public void loadElements(Collection<Element> elements) {
		 for (Element e: elements) {
         	String name = e.getAttribute("name");
         	String value = e.getAttribute("value");
         	loadString(name, value);
         }		
	}
	

	/**
	 * Puts a new entry into this Resources's stringMap, with a String key
	 * and a String value.
	 */
	public static void loadString(String key, String stringToLoad){
		stringMap.put(key, stringToLoad);
	}
	
	/**
	 * Returns the String associated with the given String label.
	 */
	public static String getString(String key){
		return stringMap.get(key);
	}

}
