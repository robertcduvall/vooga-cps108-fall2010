package vooga.engine.networking.server;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

/**
 * This class should be running constantly to ensure that games 
 * that use the networking API have connection to the server at all times. 
 * Makes and starts the VoogaDaemon for each game that uses the networking API. 
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class VoogaServer {
	
	private static String voogaGamesPath ="src/vooga/engine/networking/server/voogaGames.xml";
	private static String gameTag = "Games";
	private String name;
	private int chatPort = 0;
	private int port = 0;
	private int numberOfPlayers;
	private String clientHandler;
	private static Node gameSection;
	private static Document xmlDocument;
	private static List<GameElement> listOfGames = new ArrayList<GameElement>();

	
	/**
	 * Main method iterates through the list of games in the XML file and runs 
	 * them on the specified port with the specified ClientHandler subclass.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static void main(String args[]) {
				
		xmlDocument = getXMLDocument(voogaGamesPath);
		gameSection = xmlDocument.getElementsByTagName("Games").item(0);
		GameElement gameElement = new GameElement(gameSection);

		System.out.println("Vooga server up and running...");
	}



	/**
	 * Static method to return the port that the game is run on.
	 * 
	 * @param gameName the name of the game whose port you want to find
	 * @return port the port that the game is run on
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	/*
	public static int getGamePort(String gameName){
		Document xmlDocument = getXMLDocument(voogaGamesXMLpath);
		Node gameSection = xmlDocument.getElementsByTagName(gameTag).item(0);
		if(gameSection != null){
			NodeList listOfGames = gameSection.getChildNodes();
			for(int i = 0; i < listOfGames.getLength(); i++)
			{
				if (listOfGames.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					Element gameElement = (Element) listOfGames.item(i);
					int port = Integer.parseInt(gameElement.getAttribute("port"));
					String name = gameElement.getAttribute("name");
					if(name.equals(gameName))
						return port;
				}
			}
		}
		return -1;
	}
	*/
	
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
	}*/
	



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
		for (GameElement element : listOfGames) {	
			new VoogaDaemon(element).start();
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




}

