package arcade.core;

import java.util.Map;
import java.util.ResourceBundle;



public class GameParser {

	private ResourceBundle properties;
	public void parseGame(String game, Map<String, String[]> gameMap)
	{
		try {
			
			String gamePropertiesFile = "vooga.games." + game + ".resources.game";
			properties = ResourceBundle.getBundle(gamePropertiesFile);
			gameMap.put("splash", new String[]{properties.getString("splashImage")});
			gameMap.put("name", new String[]{properties.getString("name")});
			gameMap.put("genre", new String[]{properties.getString("genre")});
			gameMap.put("description", new String[]{properties.getString("description")});
			parseTags(gameMap);
			parseInstructions(gameMap);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parseTags(Map<String, String[]> gameMap)
	{
		String[] tags = properties.getString("tags").split(",");
		gameMap.put("tags", tags);
	}
	
	private void parseInstructions(Map<String, String[]> gameMap)
	{
		String[] instructions = properties.getString("instructions").split(",");
		gameMap.put("instructions", instructions);
	}
}
