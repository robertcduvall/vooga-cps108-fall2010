package vooga.examples.factory;

import org.w3c.dom.NamedNodeMap;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelParser;

/**
This class provides full implementation for LevelParser.
 * An example of how this could be used in a game is as follows:
 * To understand the formatting of the XML file look at tutorialXML
 * 
 * This ultimate purpose of the class is to turn an .xml file into a VoogaPlayField,
* which will contain all resources that constitute a level. This level, in the form of a
* VoogaPlayField, can be used to construct a GameState for the given level, which should
* handle rendering and updating of the level.
 * @author Derek
*
*/
public class LevelParserExample extends LevelParser{
	public void processMySprite(NamedNodeMap parameters)
	{
		String playerName = parameters.getNamedItem("name").getNodeValue();
		int health = Integer.parseInt(parameters.getNamedItem("health").getNodeValue());
		double damage = Double.parseDouble(parameters.getNamedItem("damage").getNodeValue());
		double xRange = Double.parseDouble(parameters.getNamedItem("xValue").getNodeValue());
		double yRange = Double.parseDouble(parameters.getNamedItem("yValue").getNodeValue());
		double vxValue = Double.parseDouble(parameters.getNamedItem("vxValue").getNodeValue());
		
		MySprite sprite = new MySprite(playerName, health, damage);
		sprite.doSomething(somethingToDo);
		sprite.setLocation(xRange, yRange);
		sprite.setHorizontalSpeed(vxValue);
	}
}
