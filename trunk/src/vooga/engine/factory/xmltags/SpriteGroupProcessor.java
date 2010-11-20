package vooga.engine.factory.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.factory.NodeListProcessor;

import com.golden.gamedev.object.SpriteGroup;


/**
 * 
 * Creates all SpriteGroups from <SpriteGroups>.
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
				Element sprite = (Element) myChildren.item(i);
				if (sprite.getTagName().equals("RegularSprite")) {
					RegularSpriteProcessor regularSpriteProcessor = 
						new RegularSpriteProcessor(myXMLDocument, sprite.getChildNodes());
				} else if (sprite.getTagName().equals("SpawnedSprite")) {
					SpawnedSpriteProcessor spawnedSpriteProcessor = 
						new SpawnedSpriteProcessor(myXMLDocument, sprite.getChildNodes());
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