package vooga.engine.resource.modules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

public class DoubleModule extends ResourceModule{
	
	private static Map<String, Double> doubleMap = new HashMap<String, Double>();

	@Override
	public void clearElements() {
		doubleMap.clear();		
	}

	@Override
	public void loadElements(Collection<Element> elements) {
		for (Element e: elements) {
        	String name = e.getAttribute("name");
        	double value = Double.parseDouble(e.getAttribute("value"));
        	loadDouble(name, value);
        }	
	}
	
	/**
	 * Puts a new entry into this Resources's doubleMap, with a String key
	 * and a double value.
	 */
	public static void loadDouble(String key, double doubleToLoad){
		doubleMap.put(key, doubleToLoad);
	}
	
	/**
	 * Returns the double associated with the given double label.
	 */
	public static double getDouble(String key){
		return doubleMap.get(key);
	}

}
