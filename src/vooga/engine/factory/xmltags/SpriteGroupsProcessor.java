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

public class SpriteGroupsProcessor extends NodeListProcessor {

	
	
	
	public SpriteGroupsProcessor(Document xmlDocument, NodeList children) {
		super(xmlDocument, children);
	}
	
	
	public void process(){
		for(int i = 0; i < mySpriteGroups.getLength(); i++)
		{
			if (isElement(mySpriteGroups.item(i)))
			{
				Element spriteGroup = (Element) mySpriteGroups.item(i);
				String groupName = spriteGroup.getAttribute("name");

				SpriteGroup newSpriteGroup = new SpriteGroup(groupName);
//				NodeList spritesList = spriteGroup.getChildNodes();

				processSprite(spritesList, newSpriteGroup);
				voogaPlayField.addGroup(newSpriteGroup);
			}
		}
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