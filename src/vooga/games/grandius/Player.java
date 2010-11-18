package vooga.games.grandius;

import vooga.engine.core.Sprite;
import vooga.engine.event.IEventHandler;

public class Player extends Sprite implements IEventHandler {

	public Player(String label, Sprite sprite){
		super(label,sprite);
	}
	
	@Override
	public boolean isTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		// TODO Auto-generated method stub

	}

}
