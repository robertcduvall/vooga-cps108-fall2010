package vooga.games.towerdefense.levelparse;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.buttons.LevelButton;
import vooga.widget.levelparse.modules.SpriteModule;

public class LevelButtonModule extends SpriteModule{

	@Override
	public Collection<BetterSprite> getSprites(Element spriteElement) {
		Collection<BetterSprite> betterSprites = new ArrayList<BetterSprite>();
		LevelButton button = new LevelButton(Resources.getGame());
		double x = Double.parseDouble(spriteElement.getAttribute("x"));
		button.forceX(x);
		double y = Double.parseDouble(spriteElement.getAttribute("y"));
		button.forceY(y);
		String levelName = spriteElement.getAttribute("LevelName");
		button.setLevel(levelName);
		NodeList listOfVisuals = spriteElement.getElementsByTagName("Visual");
		processVisual(listOfVisuals, button);
		betterSprites.add(button);
		return betterSprites;
	}

}
