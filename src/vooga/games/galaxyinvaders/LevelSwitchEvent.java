package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;

public class LevelSwitchEvent implements IEventHandler{

	Blah game;
	SpriteGroup enemies;
	
	public LevelSwitchEvent(Game g, SpriteGroup e){
		game = (Blah) g;
		enemies = e;
	}
	
	@Override
	public void actionPerformed() {
		game.switchLevel();
	}

	@Override
	public boolean isTriggered() {
		return enemies.getActiveSprite()==null;
	}

}
