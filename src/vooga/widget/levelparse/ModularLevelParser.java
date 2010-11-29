package vooga.widget.levelparse;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.widget.levelparse.modules.BackgroundModule;
import vooga.widget.levelparse.modules.SpriteModule;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Allows the creation of modules which describe how to 
 * parse any kind of BetterSprite. Currently only added a 
 * modular parsing structure to the parsing of BetterSprites 
 * from an XML file. It needs a moduleMapPathKey which is a 
 * the key to look up a String in Resources. That string is the 
 * file path of a properties file which maps the module attribute 
 * of the BetterSprite Element to a SpriteModule which will be used
 * to parse the BetterSprite. The module is created by reflection 
 * using the module map properties file You can write the Module to parse the 
 * BetterSprite however you want. The built in BetterSpriteModule replicates
 * the current functionality of LevelParser. For an example of this 
 * class being awesome, look in Tower Defense under the levelparse
 * package. LevelButtons, a custom class, are created using custom 
 * parameters.
 * <br><br>
 * <xmp>
 * ModularLevelParser layoutReader = new ModularLevelParser("moduleMapProperties");
 * addPlayField(layoutReader.getPlayfield(Resources.getString("mainMenuLayoutPath"), Resources.getGame()));
 *
 * </xmp>
 * @author Daniel Koverman
 *
 */
public class ModularLevelParser extends LevelParser{
	
	private ResourceBundle resources;
	private EventPool eventPool;
	
	public ModularLevelParser(String moduleMapPathKey){
		resources = ResourceBundle.getBundle(Resources.getString(moduleMapPathKey));
		eventPool = new EventPool();
		
	}
	
	public void setEventPool(EventPool eventPool){
		this.eventPool = eventPool;
	}
	
	/**
	 * Processes the Sprites within a SpriteGroup.
	 */
	@Override
	public void processSprite(NodeList spritesList, SpriteGroup group) {
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			if (isElement(spritesList.item(i))) {
				Element currentElement = (Element) spritesList.item(i);
				if (currentElement.getTagName().equals("Sprite")) {
					String moduleName = currentElement.getAttribute("module");
					
					try {
						Class<?> moduleClass = Class.forName(resources.getString(moduleName));
						Constructor<?> moduleConstructor = moduleClass.getConstructor();
						SpriteModule module = (SpriteModule) moduleConstructor.newInstance();
						module.setOverlayTracker(getOverlayTracker());
						module.setEventPool(eventPool);
						for(BetterSprite sprite: module.getSprites(currentElement)){
							group.add(sprite);
						}
						this.getIncompletePlayfield().addEventPool(eventPool);
					} catch (Throwable e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void processBackgrounds(NodeList backgrounds){
		for (int i = 0; i < backgrounds.getLength(); i++) {
			if (isElement(backgrounds.item(i))) {
				Element backgroundElement = ((Element) backgrounds.item((i)));
				if (backgroundElement.getTagName().equals("Background")) {
					String moduleName = backgroundElement.getAttribute("module");
					
					try {
						Class<?> moduleClass = Class.forName(resources.getString(moduleName));
						Constructor<?> moduleConstructor = moduleClass.getConstructor();
						BackgroundModule module = (BackgroundModule) moduleConstructor.newInstance();
						for(Background background: module.getBackgrounds(backgroundElement)){
							getIncompletePlayfield().setBackground(background);
						}
					} catch (Throwable e){
						e.printStackTrace();
					}
				}
			}
		}
	}

}
