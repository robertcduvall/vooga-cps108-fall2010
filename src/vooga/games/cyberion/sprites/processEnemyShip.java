package vooga.games.cyberion.sprites;

import org.w3c.dom.NamedNodeMap;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.games.cyberion.events.PlayerFireEvent;
import vooga.games.cyberion.events.PlayerMoveEvent;

import com.golden.gamedev.engine.BaseInput;

import vooga.engine.event.EventPool;
import vooga.engine.control.KeyboardControl;

import java.util.Random;

import vooga.games.cyberion.events.EnemyFireEvent;

//main player class. extends sprite and includes relevant
// player information such as life and weapon power. creates fire event
// when the F key is pressed. also updates position based on wasd commands.


/**
This class provides full implementation for LevelParser.
 * An example of how this could be used in a game is as follows:
 * To understand the formatting of the XML file look at tutorialXML
 * 
 * This ultimate purpose of the class is to turn an .xml file into a VoogaPlayField,
* which will contain all resources that constitute a level. This level, in the form of a
* VoogaPlayField, can be used to construct a GameState for the given level, which should
* handle rendering and updating of the level.
* 
* In order to use your own specific sprites extend LevelParser and add a method named 
* as the following
* 
* process + Name of Specific Sprite
* 
* and then define the fields in the xml file and use those attributes and create your sprite
* do what you need to the sprite and call the other methods needed for all sprites.
 * @author Derek
*
*/
public class processEnemyShip extends LevelParser{
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

}

