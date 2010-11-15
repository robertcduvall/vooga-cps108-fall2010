package vooga.examples.factory;

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
public class LevelParserExample {
	public static void main(String[] args)
	{
		LevelParser parse = new LevelParser();
		Game g = new Game();
		parse.getPlayfield("tutorialXML.xml", g);
	}
}
