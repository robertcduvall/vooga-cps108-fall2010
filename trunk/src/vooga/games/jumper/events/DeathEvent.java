package vooga.games.jumper.events;

import com.golden.gamedev.object.Sprite;


import vooga.engine.event.IEventHandler;
import vooga.games.jumper.DropThis;
import vooga.games.jumper.states.PlayGameState;

public class DeathEvent implements IEventHandler {
	
	private PlayGameState playState;
	private DropThis myGame;
	
	public DeathEvent(DropThis dropThis, PlayGameState gamestate) {
		playState = gamestate;
		myGame = dropThis;
	}

	@Override
	public boolean isTriggered() {
		
		for (Sprite s : playState.getGroup("doodleSprite").getSprites()) {
			if (s!=null && s.getY() < 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed() {
		// TODO Auto-generated method stub
		myGame.deathGame();
	}

}
