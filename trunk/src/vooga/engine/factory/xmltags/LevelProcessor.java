package vooga.engine.factory.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.factory.NodeListProcessor;


public class LevelProcessor extends NodeListProcessor {

	
	public LevelProcessor(Document xmlDocument, Node node){
		myXMLDocument = xmlDocument;
		myChildren = node.getChildNodes();
	}
	
	public void process() {
		SpriteGroupsProcessor spriteGroupsProcessor = new SpriteGroupsProcessor(myXMLDocument, myXMLDocument.getElementsByTagName("SpriteGroups"));
		CollisionGroupsProcessor collisionGroupsProcessor = new CollisionGroupsProcessor(myXMLDocument, myXMLDocument.getElementsByTagName("CollisionGroups"));
	}

	
	
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node item(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
