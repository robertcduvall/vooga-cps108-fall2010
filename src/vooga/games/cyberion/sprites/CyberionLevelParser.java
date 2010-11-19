package vooga.games.cyberion.sprites;

import java.util.Random;

import org.w3c.dom.NamedNodeMap;

import vooga.engine.core.BetterSprite;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;

public class CyberionLevelParser extends LevelParser{

	public EnemyShip processEnemyShip(NamedNodeMap parameters)
	{
		String enemyImage = parameters.getNamedItem("imageName").getNodeValue();
		int maxX = Integer.parseInt(parameters.getNamedItem("maxX").getNodeValue());
		int maxY = Integer.parseInt(parameters.getNamedItem("maxY").getNodeValue());

		EnemyShip sprite = null;
		for (int i=0;i<Integer.parseInt(parameters.getNamedItem("number").getNodeValue());i++){
		Random random = new Random();
		sprite = new EnemyShip(Resources.getImage(enemyImage), random.nextInt(maxX),
				-random.nextInt(maxY), 1, null);
		}
		//sprite.setLocation(xRange, yRange);
		//sprite.setHorizontalSpeed(vxValue);
		return sprite;
	}
	
	public PlayerShip processPlayerShip(NamedNodeMap parameters)
	{
		String playerName = parameters.getNamedItem("name").getNodeValue();
		String playerImage = parameters.getNamedItem("imageName").getNodeValue();
		
		PlayerShip sprite = new PlayerShip(playerName, new BetterSprite(Resources.getImage(playerImage)));
		//sprite.setLocation(xRange, yRange);
		//sprite.setHorizontalSpeed(vxValue);
		return sprite;
	}
}
