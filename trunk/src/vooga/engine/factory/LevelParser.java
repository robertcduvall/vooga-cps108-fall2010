package vooga.engine.factory;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.Playfield;

import com.golden.gamedev.object.PlayField;

public class LevelParser implements LevelFactory{

	
	@Override
	public PlayField getPlayfield(String filepath) {
		
		return makePlayFieldFromFile(filepath);
	
	}
	
	private Playfield createLevel(String xmlLevelFile)
	{
	
		Playfield godField = new Playfield();
		File file = new File(xmlLevelFile);
		DocumentBuilderFactory documentfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = documentfactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			
			NodeList levelList = getXMLList(xmlDocument, "Level");
			int levelToProcess = 0; // User changes this based on what level needs to be created.
			
			godField = levelList.processLevel(levelList, levelToProcess); // This should nest into specific cases and process a level
			
	
			
		} 
		catch (ParserConfigurationException e) 
		{
			
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}
		
		return godField;
		
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

	
	private Playfield processLevel(NodeList levelList, int levelToProcess)
	{
		Playfield godField = new Playfield();
		NodeList levelObjects = xmlDocument.getElementsByTagName("LevelObject");
	}

}
