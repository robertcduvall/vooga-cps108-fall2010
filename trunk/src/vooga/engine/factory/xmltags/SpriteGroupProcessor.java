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
				String className = spriteElement.getAttribute("class");
				BetterSprite newSprite = null;
				try {
					Class userSprite = Class.forName(className);
					Constructor classConstructor = userSprite.getConstructor();
					Object returnObject = classConstructor.newInstance();
					newSprite = (BetterSprite)returnObject;
				} catch (Throwable e){
					e.printStackTrace();
				}

//				NodeList visualsList = spriteElement.getElementsByTagName("Visual");
//				processVisual(visualsList, newSprite);
//				NodeList statsList = spriteElement.getElementsByTagName("Stat");
//				processStats(statsList, newSprite);
//				NodeList controlsList = spriteElement.getElementsByTagName("Control");
//				processControls(controlsList, newSprite);

				if (!Boolean.parseBoolean(spriteElement.getAttribute("random"))) {
					double x = Double.parseDouble(spriteElement.getAttribute("x"));
					double y = Double.parseDouble(spriteElement.getAttribute("y"));
					double vx = Double.parseDouble(spriteElement.getAttribute("vx"));
					double vy = Double.parseDouble(spriteElement.getAttribute("vy"));
	
					int quantity = Integer.parseInt(spriteElement.getAttribute("quantity"));
					for(int j = 0; j < quantity; j++) {
						newSprite.setLocation(x, y);
						newSprite.setHorizontalSpeed(vx);
						newSprite.setVerticalSpeed(vy);	
						newSpriteGroup.add(newSprite);
					}
				} else if (Boolean.parseBoolean(spriteElement.getAttribute("random"))) {
					double xMin = Double.parseDouble(spriteElement.getAttribute("xMin"));
					double yMin = Double.parseDouble(spriteElement.getAttribute("yMin"));
					double vxMin = Double.parseDouble(spriteElement.getAttribute("vxMin"));
					double vyMin = Double.parseDouble(spriteElement.getAttribute("vyMin"));
					double xMax = Double.parseDouble(spriteElement.getAttribute("xMax"));
					double yMax = Double.parseDouble(spriteElement.getAttribute("yMax"));
					double vxMax = Double.parseDouble(spriteElement.getAttribute("vxMax"));
					double vyMax = Double.parseDouble(spriteElement.getAttribute("vyMax"));
					
					int quantity = Integer.parseInt(spriteElement.getAttribute("quantity"));
					for(int j = 0; j < quantity; j++) {
						newSprite.setLocation(Math.random()*(xMax-xMin) + xMin,
											  Math.random()*(yMax-yMin) + yMin);
						newSprite.setHorizontalSpeed(Math.random()*(vxMax-vxMin) + vxMin);
						newSprite.setVerticalSpeed(Math.random()*(vyMax-vyMin) + vyMin);	
						newSpriteGroup.add(newSprite);
					}
				}
//				if (spriteElement.getTagName().equals("RegularSprite")) {
//					RegularSpriteProcessor regularSpriteProcessor = 
//						new RegularSpriteProcessor(myXMLDocument, spriteElement.getChildNodes());
//				} else if (spriteElement.getTagName().equals("SpawnedSprite")) {
//					SpawnedSpriteProcessor spawnedSpriteProcessor = 
//						new SpawnedSpriteProcessor(myXMLDocument, spriteElement.getChildNodes());
//				}
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