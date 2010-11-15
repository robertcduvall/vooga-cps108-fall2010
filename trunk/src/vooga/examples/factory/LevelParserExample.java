package vooga.examples.factory;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelParser;

/**
This class provides full implementation for LevelParser.
 * An example of how this could be used in a game is as follows:
 * To understand the formatting of the XML file look at tutorialXML
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
