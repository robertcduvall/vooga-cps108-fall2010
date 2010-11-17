package vooga.games.asteroids;

import vooga.engine.level.GameWonConditions;

public class GameWon extends GameWonConditions{
	
	@Override
	public boolean GameWon() {
		if (getAsteroids() == 0)
			return true;
		return false;
	}

	
}
