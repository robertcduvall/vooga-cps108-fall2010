package vooga.engine.factory.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.factory.NodeListProcessor;

public class LevelProcessor extends NodeListProcessor {
	
	public LevelProcessor(Document xmlDocument, NodeList nodeList) {
		super(xmlDocument, nodeList);
	}
	
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node item(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process() {
		SpriteGroupsProcessor spriteGroupsProcessor = 
			new SpriteGroupsProcessor(myXMLDocument, 
					myXMLDocument.getElementsByTagName("SpriteGroups").item(0).getChildNodes());
		spriteGroupsProcessor.process();
//		CollisionGroupsProcessor collisionGroupsProcessor = new CollisionGroupsProcessor(myXMLDocument, myChildren);
//		collisionGroupsProcessor.process()
	}

}

