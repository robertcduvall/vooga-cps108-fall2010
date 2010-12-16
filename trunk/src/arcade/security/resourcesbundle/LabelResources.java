package arcade.security.resourcesbundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A class for accessing the labels for all of the GUI componenets
 * 
 * @author Meng Li
 * 
 */
public class LabelResources {

	private static ResourceBundle rb = ResourceBundle
			.getBundle("arcade.security.resources.labels");

	/**
	 * Returns the label associated with a particular key
	 * 
	 * @param key
	 *            the key to be looking up
	 * @return the label associated with that key
	 */
	public static String getLabel(String key) {
		try {
			String res = rb.getString(key);
			return res;
		} catch (MissingResourceException e) {
			return "no such label key in the labels.properties file.";
		}
	}
}
