package vooga.examples.factory;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelParser;

/**
* This ultimate purpose of this class is to turn an .xml file into a VoogaPlayField,
* which will contain all resources that constitute a level. This level, in the form of a
* VoogaPlayField, can be used to construct a GameState for the given level, which should
* handle rendering and updating of the level.
* 
* 
* 
* 
* @author Cameron McCallie, John Kline, Jimmy Mu
* @example by Derek Zhou
*
*/
public class LevelParserExample {
	public static void main(String[] args)
	{
		LevelParser parse = new LevelParser();
		Game g = new Game();
		parse.getPlayfield("level1.xml", g);
	}
}
