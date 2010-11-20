package vooga.engine.factory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.PlayField;
import vooga.engine.factory.xmltags.MapProcessor;
import vooga.engine.factory.xmltags.SpriteGroupsProcessor;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;


/**
 * This abstract class should be extended by subclasses in order to process
 * each type of unique object differently.
 * 
 * @author Cameron McCallie
 * @thanks to Ben Getson for providing refactoring suggestions
 */

public abstract class NodeListProcessor implements NodeList{
	
	protected PlayField myPlayField;
	protected Document myXMLDocument;
	protected NodeList myChildren;
	protected OverlayTracker myTracker;
	
	public NodeListProcessor(Document xmlDocument, NodeList children) {
		myXMLDocument = xmlDocument;
		myChildren = children;	
		createOverlayTracker(xmlDocument);
	}

	private void createOverlayTracker(Document xmlDocument) {
		Node level = xmlDocument.getChildNodes().item(0);
		Element levelElement = (Element) level;
		String xmloverlaypath = levelElement.getAttribute("xmloverlaypath");
		myTracker = OverlayCreator.createOverlays(xmloverlaypath);
	}
	
	public abstract void process();
	
	public static boolean isElement(Node node){
		return (node.getNodeType() == Node.ELEMENT_NODE);
	}
	
	public PlayField getPlayField() {
		return myPlayField;
	}


}
