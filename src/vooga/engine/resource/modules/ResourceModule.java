package vooga.engine.resource.modules;

import java.util.Collection;
import org.w3c.dom.Element;

import vooga.engine.core.Game;

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
	
	public abstract void loadElements(Collection<Element> elements);
	
	public abstract void clearElements();
	
}
