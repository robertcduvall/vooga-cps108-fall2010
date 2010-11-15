package vooga.engine.resource.modules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

public class IntegerModule extends ResourceModule{
	private static Map<String, Integer> integerMap = new HashMap<String, Integer>();

	@Override
	public void clearElements() {
		integerMap.clear();		
	}

	@Override
	public void loadElements(Collection<Element> elements) {
		for (Element e: elements) {
        	String name = e.getAttribute("name");
        	int value = Integer.parseInt(e.getAttribute("value"));
        	loadInt(name, value);
        }
	}
	
	/**
	 * Puts a new entry into this Resources's integerMap, with a String key
	 * and an int value.
	 */
	public static void loadInt(String key, int intToLoad){
		integerMap.put(key, intToLoad);
	}
	
	/**
	 * Returns the int associated with the given int label.
	 */
	public static int getInt(String key){
		return integerMap.get(key);
	}

}
