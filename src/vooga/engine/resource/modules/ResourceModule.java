package vooga.engine.resource.modules;

import java.util.Collection;
import org.w3c.dom.Element;

import vooga.engine.core.Game;

/**
 * ResourceModules specify how to parse different types 
 * of resources from an XML file, store the resources, 
 * and retrieve the resources using a key. The infrastructure 
 * for using ResourceModules are in place, but the problem 
 * of returning resources of different classes using some 
 * common method in the Resources class is unsolved.
 * 
 * @author Daniel Koverman
 *
 */
public abstract class ResourceModule {
	
	private Game game;
	private String defaultPath;
	
	public void setGame(Game game) {
		this.game = game;		
	}
	
	public Game getGame() {
		return game;		
	}
	
	public void setDefaultPath(String defaultFilePath) {
		this.defaultPath = defaultFilePath;		
	}

	public String getDefaultPath() {
		return defaultPath;		
	}
	
	/**
	 * Adds Elements to the resource map. Presumably, these elements 
	 * are part of a Document from parsing an XML file using the 
	 * Document Object Model
	 * @param elements Elements of the parsed XML document to be added
	 */
	public abstract void loadElements(Collection<Element> elements);
	
	/**
	 * Clears the current resource map. Resource maps should be cleared 
	 * when they are full of currently unneeded resources and the game 
	 * is starting to use too much memory.
	 */
	public abstract void clearElements();
	
}
