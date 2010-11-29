package vooga.games.digger.parse;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.core.BetterSprite;
import vooga.widget.levelparse.modules.SpriteModule;

public class BetterSpriteModule extends SpriteModule {

	@Override
	public Collection<BetterSprite> getSprites(Element spriteElement) {
		Collection<BetterSprite> sprites = new ArrayList<BetterSprite>();
		double x = Double.parseDouble(spriteElement.getAttribute("x"));
		double y = Double.parseDouble(spriteElement.getAttribute("y"));
		BetterSprite sprite = new BetterSprite(x,y);
		NodeList listOfVisuals = spriteElement.getElementsByTagName("Visual");
		processVisual(listOfVisuals, sprite);
		sprites.add(sprite);
		return sprites;
	}

}
