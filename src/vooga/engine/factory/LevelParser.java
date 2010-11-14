package vooga.engine.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.Game;
import vooga.engine.core.VoogaPlayField;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

public class LevelParser implements LevelFactory{

	private static final int FIRST_IMAGE = 0;
	private static String xmlOverlayPath;
	private static String gameClassPath;
	private static Game currentGame;

	@Override
	public VoogaPlayField getPlayfield(String filepath, Game currentgame) {
	
		currentGame = currentgame;
		return createLevelPlayfield(filepath);

	}

	private VoogaPlayField createLevelPlayfield(String xmlLevelFile)
	{
		VoogaPlayField voogaPlayField = new VoogaPlayField();
		File file = new File(xmlLevelFile);
		DocumentBuilderFactory documentfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = documentfactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			processLevel(xmlDocument, voogaPlayField); // This should nest into specific cases and process a level
		} 
		catch (Exception e) {
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}

		return voogaPlayField;

	}

	/**
	 * Returns the list of Nodes associated with the given tag
	 * 
	 * @param doc a parsed XML file
	 * @param tag the name of a list of nodes
	 * @return a lit of Nodes
	 */
	private static NodeList getXMLList(Document doc, String tag)
	{
		return doc.getElementsByTagName(tag);
	}
	

	/**
	 * Returns the value for a given tag from a given Node.
	 * 
	 * @param node the node to gather information from
	 * @param tag the name of the tag which should be gathered
	 * @return a String of the appropriate value
	 */
	private static String getXMLValue(Node node, String tag)
	{
		Element element = (Element) node;
		return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
	}


	private void processLevel(Document xmlDocument, PlayField godfield)
	{
		Element level = (Element) xmlDocument.getFirstChild(); 
		gameClassPath = level.getAttribute("gameclasspath");
		xmlOverlayPath = level.getAttribute("xmloverlaypath");

		List<Element> levelObjectsList = loadSectionElements(xmlDocument, "LevelObjects");
		
		
		NodeList spritegrouplist = getXMLList(xmlDocument, "SpriteGroup");
		processLevelObjects(spritegrouplist, godfield);

		NodeList collisiongrouplist = getXMLList(xmlDocument, "CollisionGroup");
		processCollisionGroups(collisiongrouplist, godfield);

		NodeList rulelist = getXMLList(xmlDocument, "Rule");
		processRules(rulelist, godfield);

	}


	private void processLevelObjects(List<Element> levelObjectsList, PlayField godfield) {

		for(int i = 0; i < spritegroupslist.getLength(); i++)
		{
			if (isElement(spritegroupslist.item(i)))
			{
				Element spritegroup = (Element) spritegroupslist.item(i);
				String groupname = spritegroup.getAttribute("name");

				SpriteGroup newspritegroup = new SpriteGroup(groupname);
				NodeList spriteslist = spritegroup.getChildNodes();

				processSprites(spriteslist, spritegroup);
				godfield.addGroup(newspritegroup);
			}
		}
	}

	
	private void processSprites(NodeList spriteslist, Element spritegroup) {
		
		
		for(int i = 0; i < spriteslist.getLength(); i++)
		{
			if(isElement(spriteslist.item(i)))
			{
				Element sprite = (Element) spriteslist.item(i);
				Sprite newsprite = new Sprite();
				
				//process Images
				NodeList visualslist = sprite.getElementsByTagName("visual");
				processVisuals(visualslist, newsprite);
				
				
				//process Stats
				NodeList statslist = sprite.getElementsByTagName("stat");
				processStats(statslist, newsprite);
				
				
				//process Controls
				NodeList controlslist = sprite.getElementsByTagName("control");
				processControls(controlslist, newsprite);
				
				//process locations/velocities
//				double x = 
//				double y = 
//				double vx = 
//				double vy = 
						
			}
		}
	}

	private void processControls(NodeList controlslist, Sprite newsprite) {
		
		for(int i = 0; i < controlslist.getLength(); i++)
		{
			if (isElement(controlslist.item(i)))
			{
				
				
				
				
				
				
			}
		}
		
		
		
		
	}

	private void processStats(NodeList statslist, Sprite newsprite) {

		for(int i = 0; i < statslist.getLength(); i++)
		{
			if (isElement(statslist.item(i)))
			{
				Element statelement = (Element) statslist.item(i);
				String statname = statelement.getAttribute("name");
				
				//Stat<?> stat = (Stat<?>)(statname);
				//newsprite.setStat(statname, stat);
			}
		}
	}
	

	private void processVisuals(NodeList visualslist, Sprite newsprite) {
		
		for(int imagelocation = 0; imagelocation < visualslist.getLength(); imagelocation++)
		{
			if (isElement(visualslist.item(imagelocation)))
			{
				//getAnimation
				Element visual = (Element) visualslist.item(imagelocation);
				String imagename = visual.getAttribute("name");
				BufferedImage[] images = Resources.getAnimation(imagename);
				
				newsprite.addAnimatedsprite(imagename, images);
				
				if(imagelocation == FIRST_IMAGE)
				{
					newsprite.setToCurrentSprite(imagename);
				}
			}
		}
	}

	private static boolean isElement(Node node){
		return (node.getNodeType() == Node.ELEMENT_NODE);
	}

	private void processCollisionGroups(NodeList collisiongrouplist, 
			PlayField godfield) {
		
		
		
		
		
		
		
		
		
		
	}

	private void processRules(NodeList spritegrouplist,
			PlayField godfield) {
		// TODO Auto-generated method stub

	}

	/**
	 * Utility method for loadResourcesXMLFile - loads a Node's Elements and returns them in a List.
	 * @param doc
	 * @param sectionTag
	 * @return
	 */
	private static List<Element> loadSectionElements(Document doc, String sectionTag) {
    	Node section = doc.getElementsByTagName(sectionTag).item(0);
    	NodeList nodeList = section.getChildNodes();
    	int length = nodeList.getLength();
    	ArrayList<Element> elementList = new ArrayList<Element>();
    	for (int i = 0; i < length; i++) {
    		Node node = nodeList.item(i);
    		if (node.getNodeType() == Node.ELEMENT_NODE) {
    			Element element = (Element) node;
    			elementList.add(element);
    		}
    	}
    	return elementList;
    }
}
