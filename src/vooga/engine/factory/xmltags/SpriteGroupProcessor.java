package vooga.engine.factory.xmltags;

import java.lang.reflect.Constructor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.BetterSprite;
import vooga.engine.factory.NodeListProcessor;

import com.golden.gamedev.object.SpriteGroup;


/**
 * 
 * Creates all Sprites from <SpriteGroup>.
 * 
 * @author Cameron McCallie
 *
 */

public class SpriteGroupProcessor extends NodeListProcessor {
	
	private String myName;
	
	public SpriteGroupProcessor(Document xmlDocument, NodeList nodeList, String name) {
		super(xmlDocument, nodeList);
		myName = name;
	}
	
	public void process(){
		SpriteGroup newSpriteGroup = new SpriteGroup(myName);
		for(int i = 0; i < myChildren.getLength(); i++)
		{
			if (isElement(myChildren.item(i)))
			{
				Element spriteElement = (Element) myChildren.item(i);
				if (spriteElement.getTagName().equals("Sprite")) {
					SpriteProcessor spriteProcessor = new SpriteProcessor(myXMLDocument,
															spriteElement.getChildNodes());
					spriteProcessor.process();
				}
			}
		}
		myPlayField.addGroup(newSpriteGroup);
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