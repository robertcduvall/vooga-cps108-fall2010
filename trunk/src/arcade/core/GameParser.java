package arcade.core;

import java.util.Map;
import java.util.ResourceBundle;



public class GameParser {

	private ResourceBundle properties;
	public void parseGame(String game, Map<String, String[]> gameMap)
	{
		try {
			
			String gamePropertiesFile = "src.vooga.games." + game + "resources.game.properties";
			properties = ResourceBundle.getBundle(gamePropertiesFile);
			gameMap.put("genre", new String[]{properties.getString("genre")});
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
