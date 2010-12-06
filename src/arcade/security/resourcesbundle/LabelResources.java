package arcade.security.resourcesbundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * 
 * @author Meng Li
 *
 */
public class LabelResources {
	//the base name getBundle should look like this: meng_Inter.**.**.**
	private static ResourceBundle rb = ResourceBundle.getBundle("arcade.security.resources.labels");	
	public static String getLabel(String key){
		try{		
			String res=rb.getString(key);
			return res;
		}
		catch(MissingResourceException e){		
			return "no such label key in the labels.properties file.";
		}					
	}
}
