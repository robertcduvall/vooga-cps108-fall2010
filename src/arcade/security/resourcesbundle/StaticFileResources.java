package arcade.security.resourcesbundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A class for accessing the Strings in the resource files.
 * 
 * @author Meng Li
 * 
 */
public class StaticFileResources {

	private static ResourceBundle rb = ResourceBundle
			.getBundle("arcade.security.resources.filepath");

	/**
	 * Get the String associated with a specific key
	 * 
	 * @param key
	 *            the key to look up
	 * @return the String attached to that key
	 */
	public static String getPath(String key) {
		try {
			String res = rb.getString(key);
			return res;
		} catch (MissingResourceException e) {
			return "no such files in the file system";
		}
	}
}
