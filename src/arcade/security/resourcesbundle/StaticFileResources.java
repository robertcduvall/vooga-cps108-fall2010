package arcade.security.resourcesbundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * 
 * @author Meng Li
 *
 */
public class StaticFileResources {
	//the base name getBundle should look like this: meng_Inter.**.**.**
	private static ResourceBundle rb = ResourceBundle.getBundle("arcade.security.resources.filepath");
	public static String getPath(String key){
		try{		
			String res=rb.getString(key);
			return res;
		}
		catch(MissingResourceException e){		
			return "no such files in the file system";
		}					
	}
}
