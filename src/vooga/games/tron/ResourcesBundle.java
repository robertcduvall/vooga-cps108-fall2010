package vooga.games.tron;

/**
 * resource bundle, not for handling sounds and images, but for storing file path
 * @author Meng Li
 */
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourcesBundle {
	
	private static ResourceBundle bundle=ResourceBundle.getBundle("vooga.games.tron.resources.game1");
	
	public static String getString(String key){
		try{		
			String res=bundle.getString(key);
			return res;
		}
		catch(MissingResourceException e){		
			return "no such resource";
		}	
	}
}
