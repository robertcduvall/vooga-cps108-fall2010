package vooga.engine.factory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class LevelParserBeta implements LevelFactory {
	
	
	private static final int FIRST_IMAGE = 0;
	private String gameClassPath;
	private Game currentGame;
	private PlayField voogaPlayField;
	private OverlayTracker overlayTracker;
	private String[] tagsToProcess = {"Level", "SpriteGroups", "Map", "CollisionGroups", "Background", "Music", "Visual", "RegularSprite",
										"SpawnedSprite", "Stat", "Control"};
	
	
	
	
	
	
	/**
	 * Creates a VoogaPlayField and stores it in the field myVoogaPlayField.
	 */
	private void createLevelPlayfield(String xmlLevelFile)
	{
		try {
			XMLDocumentCreator xmlCreator = new XMLFileParser(xmlLevelFile);
			Document xmlDocument = xmlCreator.getDocument();
			processDocument(xmlDocument); 
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
	public void processDocument(Document xmlDocument){
		for(String tagName : tagsToProcess)
	    {
	        Node node = xmlDocument.getElementsByTagName(tagName).item(0);
	        LevelNodeListFactory tagProcessor = LevelNodeListFactory.CreateNewLevelNodeList(node, tagName);
	        tagProcessor.process();
	    }
	}
	
	@Override
	public PlayField getPlayfield(String filepath, Game currentgame) {
		this.currentGame = currentgame;
		voogaPlayField = new PlayField();
		createLevelPlayfield(filepath);
		return voogaPlayField;
	}	

}
