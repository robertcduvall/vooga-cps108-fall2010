package vooga.engine.networking.server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class VoogaServer {
	public static void main(String args[ ]) {
		XMLDocumentCreator xmlCreator = new XMLFileParser("vooga/engine/networking/server/voogaGames.xml");
		Document xmlDocument = null;
		try {
			xmlDocument = xmlCreator.getDocument();
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		Node spriteGroupsSection = xmlDocument.getElementsByTagName("Games").item(0);
		if(spriteGroupsSection != null){
			NodeList listOfGames = spriteGroupsSection.getChildNodes();
			for(int i = 0; i < listOfGames.getLength(); i++)
			{
				if (listOfGames.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					Element gameElement = (Element) listOfGames.item(i);
					String game = gameElement.getAttribute("name");
					int numberOfPlayers = Integer.parseInt(gameElement.getAttribute("numberOfPlayers"));
					String serverGame = gameElement.getAttribute("serverGame");
					String serverPlayer = gameElement.getAttribute("serverPlayer");
					new VoogaDaemon(numberOfPlayers, serverGame, serverPlayer).start();
				}
			}
		}
		//new VoogaDaemon(2, "vooga.engine.networking.server.TicTacToeGame", "vooga.engine.networking.server.TicTacToePlayer").start();
		System.out.println("Vooga server up and running...");
	}
}
