package vooga.engine.factory.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.factory.NodeListProcessor;

public class CollisionGroupsProcessor extends NodeListProcessor {

	public CollisionGroupsProcessor(Document xmlDocument, NodeList children) {
		myChildren = children;
		myXMLDocument = xmlDocument;
	}
	
	@Override
	public void process() {
		
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
