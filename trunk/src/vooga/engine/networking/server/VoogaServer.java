package vooga.engine.networking.server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class VoogaServer {
	public static void main(String args[ ]) {
		Document xmlDocument = getXMLDocument("vooga/engine/networking/server/voogaGames.xml");
		Node gameSection = xmlDocument.getElementsByTagName("Games").item(0);
		int chatPort = Integer.parseInt(((Element)gameSection).getAttribute("chatPort"));
		if(gameSection != null){
			NodeList listOfGames = gameSection.getChildNodes();
			for(int i = 0; i < listOfGames.getLength(); i++)
			{
				if (listOfGames.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					Element gameElement = (Element) listOfGames.item(i);
					int port = Integer.parseInt(gameElement.getAttribute("port"));
					int numberOfPlayers = Integer.parseInt(gameElement.getAttribute("numberOfPlayers"));
					String clientHandler = gameElement.getAttribute("clientHandler");
					new VoogaDaemon(port, chatPort, numberOfPlayers, clientHandler).start();
				}
			}
		}
		//new VoogaDaemon(2, "vooga.engine.networking.server.TicTacToeGame", "vooga.engine.networking.server.TicTacToePlayer").start();
		System.out.println("Vooga server up and running...");
	}
	
	public static Document getXMLDocument(String path){
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
	
	public static int getPort(String gameName){
		Document xmlDocument = getXMLDocument("src/vooga/engine/networking/server/voogaGames.xml");
		Node gameSection = xmlDocument.getElementsByTagName("Games").item(0);
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
}
