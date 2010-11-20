package vooga.engine.factory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.xmltags.LevelProcessor;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

/**
 * 
 * LevelParser will reader a user-defined .xml file to produce a PlayField.
 * This PlayField can be used in order to create a level for a GameState to render/update.
 * 
 * 
 * @author Cameron McCallie, Jimmy Mu, John Kline, Derek Zhou
 * @refactored Cameron McCallie, John Kline
 * @thanks to Ben Getson, UTA, for refactoring advice
 *
 */

public class LevelParserBeta implements LevelFactory {
	
	
	private static final int FIRST_IMAGE = 0;
	private String gameClassPath;
	private Game currentGame;
	private PlayField voogaPlayField;
	private OverlayTracker overlayTracker;
	//private String[] tagsToProcess = {"Level", "SpriteGroups", "Map", "CollisionGroups", "Background", "Music", "Visual", "RegularSprite",
	//									"SpawnedSprite", "Stat", "Control"};
	
	
	
	
	/**
	 * Creates a VoogaPlayField and stores it in the field myVoogaPlayField.
	 */
	private void createLevelPlayfield(String xmlLevelFile)
	{
		try {
			XMLDocumentCreator xmlCreator = new XMLFileParser(xmlLevelFile);
			Document xmlDocument = xmlCreator.getDocument();
			voogaPlayField = processDocument(xmlDocument); 
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}
	}
	
	/**
	 * Processes the entire document by processing each unique tag.
	 * In order to add more tags to be processed, there are 3 steps to be taken:
	 * 
	 * 1. Add the string name of the tag in the tagsToProcess array
	 * 2. Add the case to the LevelNodeListFactory class
	 * 3. Write a class with a process method in the xmltags package
	 */
	public PlayField processDocument(Document xmlDocument){
		//for(String tagName : tagsToProcess)
	   // {
	        NodeList levelNodeList = xmlDocument.getElementsByTagName("Level");
	        NodeListProcessor levelProcessor = new LevelProcessor(xmlDocument, levelNodeList);
	        levelProcessor.process();
	        return levelProcessor.getPlayField();
	    //}
	}
	
	
	
	@Override
	public PlayField getPlayfield(String filepath, Game currentgame) {
		this.currentGame = currentgame;
		voogaPlayField = new PlayField();
		createLevelPlayfield(filepath);
		return voogaPlayField;
	}	

}
