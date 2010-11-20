package vooga.engine.factory.xmltags;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.golden.gamedev.object.SpriteGroup;


/**
 * 
 * Creates all SpriteGroups from <SpriteGroups>.
 * 
 * @author Cameron McCallie
 *
 */

public abstract class SpriteGroups {

	public NodeList mySpriteGroups;
	
	
	public SpriteGroups(NodeList nodeList) {
		mySpriteGroups = nodeList;
	}
	
	public void process(){
		for(int i = 0; i < mySpriteGroups.getLength(); i++)
		{
			if (isElement(mySpriteGroups.item(i)))
			{
				Element spriteGroup = (Element) mySpriteGroups.item(i);
				String groupName = spriteGroup.getAttribute("name");

				SpriteGroup newSpriteGroup = new SpriteGroup(groupName);
				NodeList spritesList = spriteGroup.getChildNodes();

				processSprite(spritesList, newSpriteGroup);
				voogaPlayField.addGroup(newSpriteGroup);
			}
		}
	}
	
	
}