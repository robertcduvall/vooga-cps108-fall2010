package vooga.engine.factory.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.factory.NodeListProcessor;
import vooga.engine.overlay.Stat;

/**
 * 
 * Separates RegularSprites from SpawnedSprites.
 * 
 * @author Cameron McCallie
 *
 */

public abstract class SpriteProcessor extends NodeListProcessor{



	public SpriteProcessor(Document xmlDocument, NodeList children) {
		super(xmlDocument, children);
	}

	public void process(){
		for(int i = 0; i < myChildren.getLength(); i++)
		{
			if (isElement(myChildren.item(i))) {
				Element currentNode = (Element) myChildren.item(i); 
				
				
				


			}
		}
	}

	
	
	
	
	
	
	
	
	
	
}
