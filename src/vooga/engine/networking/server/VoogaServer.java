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
 * This class should be running on the server constantly to ensure that games 
 * that use the networking API have connection to the server at all times. 
 * Makes and starts the VoogaDaemon for each game that uses the networking API. 
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class VoogaServer {

	/**
	 * Main method iterates through the list of games in the XML file and runs 
	 * them on the specified port with the specified ClientHandler subclass.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static void main(String args[]) {
		initializeDaemons();
		System.out.println("Vooga server up and running...");
	}
	
	/**
	 * The two suitable commands are "initialize" and "getPort"
	 * @param command
	 */
	private static List<Element> getGameArray(String filePath) {
		List<Element> gameArray = new ArrayList<Element>();
		Document xmlDocument = getXMLDocument(filePath);
		Node gameSection = xmlDocument.getElementsByTagName("Games").item(0);
		
		if(gameSection != null){
			
			NodeList listOfGames = gameSection.getChildNodes();
			for(int i = 0; i < listOfGames.getLength(); i++) {
				
				if (listOfGames.item(i).getNodeType() == Node.ELEMENT_NODE) {
					
					gameArray.add( (Element) listOfGames.item(i));
				}
			}
		}
		return gameArray;
	}
	
	private static void initializeDaemons() {
		List<Element> gameArray = getGameArray("vooga/engine/networking/server/voogaGames.xml");
		for (Element gameElement : gameArray) {
			String name = gameElement.getAttribute("name");
			int port = Integer.parseInt(gameElement.getAttribute("port"));
			int chatPort = Integer.parseInt(gameElement.getAttribute("chatPort"));
			int numberOfPlayers = Integer.parseInt(gameElement.getAttribute("numberOfPlayers"));
			String clientHandler = gameElement.getAttribute("clientHandler");
			new VoogaDaemon(name, port, chatPort, numberOfPlayers, clientHandler).start();
		}
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
		XMLDocumentCreator xmlCreator = new XMLFileParser(path);
		Document xmlDocument = null;
		try {
			xmlDocument = xmlCreator.getDocument();
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return xmlDocument;
	}

	/**
	 * Static method to return the port that the game is run on.
	 * 
	 * @param gameName the name of the game whose port you want to find
	 * @return port the port that the game is run on
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static int getGamePort(String gameName){
		return getPort(gameName, "port");
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
		return getPort(gameName, "chatPort");
	}


	private static int getPort(String gameName, String portName){
		List<Element> gameArray = getGameArray("src/vooga/engine/networking/server/voogaGames.xml");
		for (Element gameElement : gameArray) {
			int port = Integer.parseInt(gameElement.getAttribute(portName));
			String name = gameElement.getAttribute("name");
			if(name.equals(gameName))
				return port;
		}
		return -1;
	} 

}
