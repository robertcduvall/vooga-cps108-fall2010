package vooga.widget.levelparse.modules;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.core.BetterSprite;

public class BetterSpriteModule extends SpriteModule{
	

	@Override
	public Collection<BetterSprite> getSprites(Element spriteElement) {
		Collection<BetterSprite> betterSprites = new ArrayList<BetterSprite>();
		if (!Boolean.parseBoolean(spriteElement.getAttribute("random"))) {
			double x = Double.parseDouble(spriteElement.getAttribute("x"));
			double y = Double.parseDouble(spriteElement.getAttribute("y"));
			double vx = Double.parseDouble(spriteElement.getAttribute("vx"));
			double vy = Double.parseDouble(spriteElement.getAttribute("vy"));

			int quantity = Integer.parseInt(spriteElement.getAttribute("quantity"));
			for(int j = 0; j < quantity; j++) {
				BetterSprite spriteToAdd = new BetterSprite();
				
				NodeList listOfVisuals = spriteElement.getElementsByTagName("Visual");
				processVisual(listOfVisuals, spriteToAdd);
				
				NodeList listOfControls = spriteElement.getElementsByTagName("Control");
				processControl(listOfControls, spriteToAdd);
				
				NodeList listOfStats = spriteElement.getElementsByTagName("Stat");
				processStat(listOfStats, spriteToAdd);
				
				spriteToAdd.setLocation(x, y);
				spriteToAdd.setHorizontalSpeed(vx);
				spriteToAdd.setVerticalSpeed(vy);	
				betterSprites.add(spriteToAdd);
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
				BetterSprite spriteToAdd = new BetterSprite();
				
				NodeList listOfVisuals = spriteElement.getElementsByTagName("Visual");
				processVisual(listOfVisuals, spriteToAdd);
				
				NodeList listOfControls = spriteElement.getElementsByTagName("Control");
				processControl(listOfControls, spriteToAdd);
				
				NodeList listOfStats = spriteElement.getElementsByTagName("Stat");
				processStat(listOfStats, spriteToAdd);
				
				spriteToAdd.setLocation(Math.random()*(xMax-xMin) + xMin,
									  Math.random()*(yMax-yMin) + yMin);
				spriteToAdd.setHorizontalSpeed(Math.random()*(vxMax-vxMin) + vxMin);
				spriteToAdd.setVerticalSpeed(Math.random()*(vyMax-vyMin) + vyMin);	
				betterSprites.add(spriteToAdd);
			}	
		}
		return betterSprites;
	}

}
