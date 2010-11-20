package vooga.engine.factory.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.factory.NodeListProcessor;


public class LevelProcessor extends NodeListProcessor {

	
	public LevelProcessor(Document xmlDocument, NodeList children){
		super(xmlDocument, children);
	}
	
	public void process() {
		SpriteGroupsProcessor spriteGroupsProcessor = new SpriteGroupsProcessor(myXMLDocument, myXMLDocument.getElementsByTagName("SpriteGroups"));
		spriteGroupsProcessor.process();
		CollisionGroupsProcessor collisionGroupsProcessor = new CollisionGroupsProcessor(myXMLDocument, myXMLDocument.getElementsByTagName("CollisionGroups"));
		collisionGroupsProcessor.process();
	}

	
	
	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public Node item(int index) {
		return null;
	}

}
