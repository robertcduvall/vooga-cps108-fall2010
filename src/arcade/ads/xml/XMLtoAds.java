package arcade.ads.xml;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import arcade.ads.adscontent.BasicAds;

import vooga.engine.factory.LevelException;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;
import vooga.engine.core.BetterSprite;

public class XMLtoAds {
	
	public static List<BasicAds> convert(String xmlLevelFile){
		try {
			//System.out.println(xmlLevelFile);
			XMLDocumentCreator xmlCreator = new XMLFileParser(xmlLevelFile);
			Document xmlDocument = xmlCreator.getDocument();
			return processAds(xmlDocument); // This nests into specific cases and processes a level
		}
		catch (Exception e) {
			e.printStackTrace();
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}		
	}

	private static List<BasicAds> processAds(Document xmlDocument) {
		Element adGroup = (Element) xmlDocument.getFirstChild(); 

		Node basicAdSelection = xmlDocument.getElementsByTagName("BasicAds").item(0);
		if(basicAdSelection !=null){
			NodeList listOfBasicAds = basicAdSelection.getChildNodes();
			System.out.println("* "+listOfBasicAds.getLength());
			return processBasicAds(listOfBasicAds);
		}
		return null;

	}

	private static List<BasicAds> processBasicAds(NodeList listOfBasicAds) {
		List<BasicAds> tempAds = new ArrayList<BasicAds>();
		for(int i = 0; i < listOfBasicAds.getLength(); i++)
		{
			if (isElement(listOfBasicAds.item(i)))
			{
				Element ad = (Element) listOfBasicAds.item(i);
				String implementor = ad.getAttribute("class");
				System.out.println(implementor);
				
				try {
					Class<?> adClass = Class.forName(implementor);
					Constructor<?> ct = adClass.getConstructor();
					System.out.println(ct);
					BasicAds adInstance = (BasicAds) ct.newInstance();
					System.out.println("!");

					adInstance.setParameters(ad.getAttributes());
					tempAds.add(adInstance);
				}
				catch(Exception e)
				{
					throw LevelException.RULE_NOT_FOUND_EXCEPTION;//TODO: Change Exception
				}
			}
		}
		return tempAds;
	}

	private static boolean isElement(Node item) {
		return (item.getNodeType() == Node.ELEMENT_NODE);
	}

}
