package vooga.games.towerdefense;

import org.w3c.dom.*;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.factory.*;
import vooga.games.towerdefense.buttons.*;

public class LayoutParser extends LevelParser {	

	@Override
	public void processSprite(NodeList spritesList, SpriteGroup spriteGroup) {
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			Node currentNode = spritesList.item(i);
			if(isElement(currentNode))
			{
				if (currentNode.getNodeName().equals("RegularSprite")) {
					processRegularSprite((Element)currentNode, spriteGroup);
				} else if (currentNode.getNodeName().equals("LevelButton")) {
					processLevelButton((Element)currentNode, spriteGroup);
				}
			}
		}
	}

	public void processLevelButton(Element buttonElement,
			SpriteGroup spriteGroup) {
		LevelButton button = new LevelButton(getGame());

		NodeList visualsList = buttonElement.getElementsByTagName("Visual");
		processVisual(visualsList, button);
		
		String levelName = buttonElement.getAttribute("LevelName");
		button.setLevel(levelName);
		
		double x = Double.parseDouble(buttonElement.getAttribute("x"));
		double y = Double.parseDouble(buttonElement.getAttribute("y"));
		button.setLocation(x, y);
		spriteGroup.add(button);
	}
}
