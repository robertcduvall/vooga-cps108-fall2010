package vooga.games.asteroids;

import vooga.engine.level.GameLostConditions;


public class GameLost extends GameLostConditions{
	
	@Override
	public boolean GameLost() {
		if (getLives() == 0)
			return true;
		return false;
	}
}
