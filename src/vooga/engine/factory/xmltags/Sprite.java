package vooga.engine.factory.xmltags;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * Separates RegularSprites from SpawnedSprites.
 * 
 * @author Cameron McCallie
 *
 */

public abstract class Sprite extends SpriteGroups {
	
	

	
	public Sprite(NodeList nodeList) {
		super(nodeList);
	}
	
	public void process(){
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			if (isElement(spritesList.item(i))) {
				Element currentNode = (Element) spritesList.item(i); //
				if (currentNode.getNodeName().equals("RegularSprite")) {
					processRegularSprite((Element)currentNode, group);
				} else if (currentNode.getNodeName().equals("SpawnedSprite")) {
					processSpawnedSprite((Element)currentNode, group);
				}
//				else
//				{
//					BetterSprite result = processX(currentNode.getTagName(), currentNode.getAttributes());
//					NodeList visualsList = currentNode.getElementsByTagName("Visual");
//					group.add(result);
//					processVisual(visualsList, result);
//				}
			}
		}
	}

}
