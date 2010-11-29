package vooga.widget.levelparse.modules;

import java.util.Collection;

import org.w3c.dom.Element;

import com.golden.gamedev.object.Background;

public abstract class BackgroundModule extends Module{
	
	public abstract Collection<Background> getBackgrounds(Element backgroundElement);
	
}
