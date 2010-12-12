package vooga.games.digger.parse;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.digger.buttons.SwitchStateButton;
import vooga.widget.Button;
import vooga.widget.levelparse.modules.SpriteModule;

public class SwitchStateButtonModule extends SpriteModule {

	@Override
	public Collection<BetterSprite> getSprites(Element spriteElement) {
		Collection<BetterSprite> sprites = new ArrayList<BetterSprite>();
		double x = Double.parseDouble(spriteElement.getAttribute("x"));
		double y = Double.parseDouble(spriteElement.getAttribute("y"));
		String stateName = spriteElement.getAttribute("stateName");
		Button button = new SwitchStateButton(stateName);
		button.setLocation(x, y);
		button.setLabel("");
		NodeList listOfVisuals = spriteElement.getElementsByTagName("Visual");
		processVisual(listOfVisuals, button);
		sprites.add(button);
		getEventPool().addEvent(button);
		return sprites;
	}

}
