package vooga.engine.level;

import vooga.engine.event.*;

public abstract class LevelSwitch{
	private int priority;
	public abstract boolean switchLevel();
	public abstract int switchToLevel();
	public abstract void setPriority(int p);
	public int getPriority(){
		return priority;
	}
}
