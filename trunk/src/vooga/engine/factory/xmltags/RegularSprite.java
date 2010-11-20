package vooga.engine.factory.xmltags;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.factory.LevelNodeListFactory;
import vooga.engine.factory.MapReader;

public class RegularSprite extends Sprite{



	/**
	 * Adds a regular, location-specified Sprite to the given SpriteGroup.
	 */
	public void process() {
		BetterSprite newSprite = new BetterSprite();

		NodeList visualsList = spriteElement.getElementsByTagName("Visual");
		processVisual(visualsList, newSprite);
		NodeList statsList = spriteElement.getElementsByTagName("Stat");
		processStats(statsList, newSprite);
		NodeList controlsList = spriteElement.getElementsByTagName("Control");
		processControls(controlsList, newSprite);

		double x = Double.parseDouble(spriteElement.getAttribute("x"));
		double y = Double.parseDouble(spriteElement.getAttribute("y"));
		double vx = Double.parseDouble(spriteElement.getAttribute("vx"));
		double vy = Double.parseDouble(spriteElement.getAttribute("vy"));

		int quantity = Integer.parseInt(spriteElement.getAttribute("quantity"));
		for(int j = 0; j < quantity; j++) {
			newSprite.setLocation(x, y);
			newSprite.setHorizontalSpeed(vx);
			newSprite.setVerticalSpeed(vy);	
			group.add(newSprite);
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