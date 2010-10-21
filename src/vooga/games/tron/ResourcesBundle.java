package vooga.games.tron;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourcesBundle {
	
	private static ResourceBundle bundle=ResourceBundle.getBundle("vooga.games.tron.resources.game");
	
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
