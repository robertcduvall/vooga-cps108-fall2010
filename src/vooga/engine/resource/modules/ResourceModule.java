package vooga.engine.resource.modules;

import java.util.Collection;
import org.w3c.dom.Element;

import vooga.engine.core.Game;

public interface ResourceModule {
	
	public void setGame(Game game);
	
	public void setDefaultPath(String defaultFilePath);

	public void loadElements(Collection<Element> elements);
	
	public void clearElements();
	
}
