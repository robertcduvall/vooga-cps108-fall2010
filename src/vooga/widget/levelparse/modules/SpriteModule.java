package vooga.widget.levelparse.modules;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.control.Control;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.factory.LevelException;
import vooga.engine.factory.LevelParser;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

/**
 * 
 * Abstract class which parses an Element representing a 
 * BetterSprite or a group a BetterSprites in a customized 
 * manner. getSprites() is the heart of the class where the 
 * actual parsing occurs. The other methods are utility 
 * methods for parsing elements within the Sprite element.
 * Ideally, these would be removed from LevelParser and placed 
 * in a Module class one level above SpriteModule in the level
 * parsing module hierarchy to facilitate moving all of LevelParser
 * to more extensible framework, but I didn't want to tear apart 
 * LevelParser so I had to repeat the functionality instead of 
 * moving it.
 * 
 * @author Daniel Koverman
 *
 */
public abstract class SpriteModule {

	private OverlayTracker overlayTracker;

	public abstract Collection<BetterSprite> getSprites(Element spriteElement);
	
	public void setOverlayTracker(OverlayTracker overlayTracker){
		this.overlayTracker = overlayTracker;
	}

	/**
	 * Processes Visuals within a Sprite.
	 */
	public void processVisual(NodeList visualsList, BetterSprite newSprite) {
		for (int imageLocation = 0; imageLocation < visualsList.getLength(); imageLocation++) {
			if (LevelParser.isElement(visualsList.item(imageLocation))) {
				Element visualElement = (Element) visualsList
						.item(imageLocation);
				String visualName = visualElement.getAttribute("name");
				BufferedImage[] visual = Resources.getVisual(visualName);

				newSprite.addAnimatedImages(visualName, visual);

				if (imageLocation == LevelParser.FIRST_IMAGE) {
					newSprite.setAsRenderedSprite(visualName);
				}
			}
		}
	}

	public void processStat(NodeList listOfStats, BetterSprite spriteToAdd) {

		for (int statCount = 0; statCount < listOfStats.getLength(); statCount++) {
			if (LevelParser.isElement(listOfStats.item(statCount))) {
				Element statElement = (Element) listOfStats.item(statCount);
				String statName = statElement.getAttribute("name");
				Stat<?> stat = overlayTracker.getStat(statName);

				spriteToAdd.setStat(statName, stat);
			}
		}
	}

	public void processControl(NodeList listOfControls,
			BetterSprite spriteToAdd) {

		for (int controlCount = 0; controlCount < listOfControls.getLength(); controlCount++) {
			if (LevelParser.isElement(listOfControls.item(controlCount))) {
				Element controlElement = (Element) listOfControls
						.item(controlCount);

				String controlName = controlElement.getAttribute("name");
				String controlsubclassname = controlElement
						.getAttribute("controlsubclass");
				String objectclassname = controlElement.getAttribute("class");
				String objectmethodname = controlElement
						.getAttribute("methodname");
				int controlkey = Integer.parseInt(controlElement
						.getAttribute("controlkey"));
				NodeList methodArguments = controlElement
						.getElementsByTagName("MethodArgument");
				Class<?>[] paramTypes = getParamTypes(methodArguments);
				try {
					Class controlClass = Class.forName(controlsubclassname);
					Constructor<Control> ct = controlClass.getConstructor(
							Object.class, Game.class);
					Control newControl = ct.newInstance(spriteToAdd, Resources
							.getGame());
					newControl.addInput(controlkey, objectmethodname,
							objectclassname, paramTypes);
				} catch (Exception e) {
					throw LevelException.CONTROL_PARSING_EXCEPTION;
				}
			}
		}

	}
	
	private Class[] getParamTypes(NodeList list) {
		Class[] returnArray = new Class[list.getLength()];
		for (int i = 0; i < list.getLength(); i++) {
			try {
				returnArray[i] = Class.forName(((Element)list.item(i)).getAttribute("class"));
			} catch (Throwable e) {
				throw LevelException.CONTROL_PARSING_EXCEPTION_CLASS_NOT_FOUND;
			}
		}
		return returnArray;
	}
}
