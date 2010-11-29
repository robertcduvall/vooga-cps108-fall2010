package vooga.widget.levelparse.modules;

import org.w3c.dom.Element;

import vooga.engine.control.Control;
import vooga.engine.core.BetterSprite;

public abstract class ControlModule extends Module{
	
	public abstract Control getControl(Element controlElement, BetterSprite controlledSprite);
}
