package vooga.engine.networking.server;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
	private static Document xmlDocument;
	public static Map<String, VoogaDaemon> daemonMap = new HashMap<String, VoogaDaemon>();

	

	/**
	 * Main method iterates through the list of games in the XML file and runs 
	 * them on the specified port with the specified ClientHandler subclass.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static void main(String args[]) {
		xmlDocument = getXMLDocument("src/vooga/engine/networking/server/voogaGames.xml");
		processXML(xmlDocument);
		System.out.println("Vooga server up and running...");
	}
	
	private static void processXML (Document xmlDoc) {
		Node gameSection = xmlDoc.getElementsByTagName("Games").item(0);
		if(gameSection != null){
			NodeList listOfGames = gameSection.getChildNodes();
			for(int i = 0; i < listOfGames.getLength(); i++)
			{
				if (listOfGames.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					Element gameElement = (Element) listOfGames.item(i);
					String name = gameElement.getAttribute("name");
					int port = Integer.parseInt(gameElement.getAttribute("port"));
					int chatPort = Integer.parseInt(gameElement.getAttribute("chatPort"));
					System.out.println("voogaServer/procXML: "+port+" "+chatPort);
					int numberOfPlayers = Integer.parseInt(gameElement.getAttribute("numberOfPlayers"));
					String clientHandler = gameElement.getAttribute("clientHandler");
					System.out.println("voogaServer/processXML: name: "+name+" port: "+port+" chatPort: "+chatPort);
					VoogaDaemon daemon = new VoogaDaemon(name, port, chatPort, numberOfPlayers, clientHandler);
					daemon.start();
					daemonMap.put(name, daemon);
					System.out.println("VoogaServer: Put into daemon : "+name +" "+daemonMap.get(name)+" "+daemonMap.get("TicTacToe"));
				}
			}
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
	//TODO can we abstract this method to clean up the VoogaServer class? --Cody (lol jk its Devon)
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
		/*for (Map.Entry<String, VoogaDaemon> daemon : daemonMap.entrySet()) {*/
			int port = -2;
			System.out.println("VoogaServer: "+portName +" gameName: "+gameName);
			//TODO is there a way to use reflection to call these ports? --Devon
			if (portName.equals("gamePort")) {
				System.out.println("VoogaSEERVER:  gamePort "+daemonMap.get("TicTacToe").portNumber);
				return port = daemonMap.get("TicTacToe").portNumber; //TODO WHAT THE FUCK
			}
			else if (portName.equals("chatPort")){
				System.out.println("just hecking  VoggaServer 125 "+daemonMap.get("TicTacToe"));
				System.out.println("VoogaSEERVER:  gamePort "+daemonMap.get("TicTacToe").chatPortNumber);
				return port = daemonMap.get(gameName).chatPortNumber;
			}
			else {
				System.out.println("VoogaSEERVER:  name didnt match");
				return -3;
			}
	}
	


	
}
