package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;

public class Player extends BetterSprite implements IEventHandler {

	public Player(String label, BetterSprite sprite){
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
