package arcade.ads.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import arcade.ads.adscontent.BasicAd;

import vooga.engine.factory.LevelException;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;
import vooga.engine.core.BetterSprite;

public class XMLtoAds {
	
	public static List<BasicAd> convertAds(File xmlLevelFile){
		List<BasicAd> myAds = new ArrayList<BasicAd>();
		try {
			Scanner scanner = new Scanner(xmlLevelFile);
			while(scanner.hasNextLine()){
				myAds.addAll(convert(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			System.out.println("!");
		}
		return myAds;
	}
	
	public static List<BasicAd> convert(String xmlLevelFile){
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

	private static List<BasicAd> processAds(Document xmlDocument) {
		Element adGroup = (Element) xmlDocument.getFirstChild(); 

		Node basicAdSelection = xmlDocument.getElementsByTagName("BasicAds").item(0);
		if(basicAdSelection !=null){
			NodeList listOfBasicAds = basicAdSelection.getChildNodes();
			System.out.println("* "+listOfBasicAds.getLength());
			return processBasicAds(listOfBasicAds);
		}
		return null;

	}

	private static List<BasicAd> processBasicAds(NodeList listOfBasicAds) {
		List<BasicAd> tempAds = new ArrayList<BasicAd>();
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
					BasicAd adInstance = (BasicAd) ct.newInstance();
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
