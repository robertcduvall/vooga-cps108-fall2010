package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.games.galaxyinvaders.sprites.Ship;

public class GameLostEvent implements IEventHandler{

	Blah game;
	Ship ship;
	
	public GameLostEvent(Game g, SpriteGroup s){
		game = (Blah) g;
		ship = (Ship) s.getActiveSprite();
	}
	
	@Override
	public void actionPerformed() {
		game.gameOver();
	}

	@Override
	public boolean isTriggered() {
		return ship.getStat("livesStat").getStat().equals(new Integer(0));
	}

}
