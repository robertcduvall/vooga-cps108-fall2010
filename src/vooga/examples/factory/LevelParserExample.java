package vooga.examples.factory;

import org.w3c.dom.NamedNodeMap;

import vooga.engine.core.BetterSprite;
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
public class LevelParserExample extends LevelParser{
	public MySprite processMySprite(NamedNodeMap parameters)
	{
		String playerName = parameters.getNamedItem("name").getNodeValue();
		int health = Integer.parseInt(parameters.getNamedItem("health").getNodeValue());
		double damage = Double.parseDouble(parameters.getNamedItem("damage").getNodeValue());
		double xRange = Double.parseDouble(parameters.getNamedItem("xValue").getNodeValue());
		double yRange = Double.parseDouble(parameters.getNamedItem("yValue").getNodeValue());
		double vxValue = Double.parseDouble(parameters.getNamedItem("vxValue").getNodeValue());
		
		MySprite sprite = new MySprite(playerName, health, damage);
		sprite.doSomething("somethingToDo");
		sprite.setLocation(xRange, yRange);
		sprite.setHorizontalSpeed(vxValue);
		return sprite;
	}
	
	public class MySprite extends BetterSprite
	{
		private String myName;
		private int myHealth;
		private double myDamage;
		
		public MySprite(String name, int health, double damage)
		{
			myName = name;
			myHealth = health;
			myDamage = damage;
		}
		public void doSomething(String doThis)
		{
			System.out.println("Doing SOMETHING! Yes.");
		}
	}
}
