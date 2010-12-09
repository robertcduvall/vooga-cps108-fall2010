package vooga.engine.networking.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class GameElement {
	
	private String name;
	private int chatPort = 0;
	private int port = 0;
	private int numberOfPlayers;
	private String clientHandler;
	private Element gameElement;
	private static Map<String,GameElement> mapOfGames = new HashMap<String, GameElement>();



	public GameElement (Node gameSection) {
		setIterableListofGames( gameSection);
	}
	

	private void setIterableListofGames (Node gamesection) {
		NodeList nodeListOfGames = null;	
		nodeListOfGames = getChildren(gamesection); 

		for(int i = 0; i < nodeListOfGames.getLength(); i++)
		{	
			if( nodeListOfGames.item(i).getNodeType() == Node.ELEMENT_NODE) { //TODO get rid of this
				gameElement = (Element) nodeListOfGames.item(i);
				name =  gameElement.getAttribute("name");
				port = Integer.parseInt(gameElement.getAttribute("port"));
				chatPort = Integer.parseInt(gameElement.getAttribute("chatPort"));
				numberOfPlayers = Integer.parseInt(gameElement.getAttribute("numberOfPlayers"));
				clientHandler = gameElement.getAttribute("clientHandler");	
				mapOfGames.put(name, this);
			}
		}
	}

	
	private NodeList getChildren (Node gameSection) {
		NodeList nl = null;
		try {
			nl =  gameSection.getChildNodes();
		}
		catch(NullPointerException e) {
			System.out.println("Put games in the XML file: ");
			e.printStackTrace();
		}	
		return nl;
	}

	/**
	 * Static method to return the XML document with the list of games 
	 * that can be run on the networking Vooga servers.
	 * 
	 * @param path the path of the XML document
	 * @return the XML document with the list of games
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	private static Document getXMLDocument(String path){
		
		Document xmlDocument = null;
		try {
			xmlDocument = new XMLFileParser(path).getDocument();
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return xmlDocument;
	}
	
	public String getName () {
		return name;
	}
	
	public static int getChatPort (String name) {
		return mapOfGames.get(name).chatPort;
	}
	
	public static int getPort (String name) {
		return mapOfGames.get(name).port;
	}
	
	public int getChatPort () {
		return chatPort;
	}
	
	public int getPort () {
		return port;
	}
	
	public int getNumberOfPlayers () {
		return numberOfPlayers;
	}
	
	public String getClientHandler () {
		return clientHandler;
	}




	public void createVoogaDaemons() {
		for (Map.Entry<String, GameElement> index : mapOfGames.entrySet()) {
			new VoogaDaemon(index.getValue()).start();
		}
		
	}
	
	/**
	 * Static method to return the port that the chat is run on.
	 * 
	 * @return the port that the game is run on
	 * @param gameName the name of the game whose chat port you want to find
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	/*
	public static int getChatPort(String gameName){
		Document xmlDocument = getXMLDocument("src/vooga/engine/networking/server/voogaGames.xml");
		Node gameSection = xmlDocument.getElementsByTagName("Games").item(0);
		if(gameSection != null){
			NodeList listOfGames = gameSection.getChildNodes();
			for(int i = 0; i < listOfGames.getLength(); i++)
			{
				if (listOfGames.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					Element gameElement = (Element) listOfGames.item(i);
					int port = Integer.parseInt(gameElement.getAttribute("chatPort"));
					String name = gameElement.getAttribute("name");
					if(name.equals(gameName))
						return port;
				}
			}
		}
		return -1;
	}
	*/




}
