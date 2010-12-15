package arcade.ads.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import arcade.ads.adscontent.BasicAd;
import arcade.ads.adscontent.RelatedAd;

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
		List<BasicAd> tempAds = new ArrayList<BasicAd>();

		Node basicAdSelection = xmlDocument.getElementsByTagName("Ads").item(0);
		if(basicAdSelection !=null){
			NodeList listOfBasicAds = basicAdSelection.getChildNodes();
			tempAds.addAll(processBasicAds(listOfBasicAds));
		}
		Node relatedAdSelection = xmlDocument.getElementsByTagName("RelatedAds").item(0);
		if(relatedAdSelection !=null){
			NodeList listOfRelatedAds = relatedAdSelection.getChildNodes();
			tempAds.addAll(processRelatedAds(listOfRelatedAds));
		}
		return tempAds;

	}
	
	private static List<BasicAd> processRelatedAds(NodeList listOfRelatedAds){
		List<BasicAd> tempAds = new ArrayList<BasicAd>();
		for(int i = 0; i < listOfRelatedAds.getLength(); i++)
		{
			if (isElement(listOfRelatedAds.item(i)))
			{
				Element ad = (Element) listOfRelatedAds.item(i);
				List<BasicAd> tempList = processBasicAds(listOfRelatedAds.item(i).getChildNodes());
				List<String> typeArray = Arrays.asList(ad.getAttribute("type").split(","));
				for (int j=0; j<tempList.size(); j++){
					tempAds.add(new RelatedAd(tempList.get(j), typeArray));
				}
			}
		}
		return tempAds;
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
					adInstance.setParameters(ad.getAttributes());
					tempAds.add(adInstance);
				}
				catch(Exception e)
				{
					throw LevelException.RULE_NOT_FOUND_EXCEPTION;
				}
			}
		}
		return tempAds;
	}

	private static boolean isElement(Node item) {
		return (item.getNodeType() == Node.ELEMENT_NODE);
	}

}
