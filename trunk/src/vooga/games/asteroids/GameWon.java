package vooga.games.asteroids;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.GameWonConditions;

public class GameWon extends GameWonConditions{
	
	private SpriteGroup myAsteroids;
	
	@Override
	public boolean GameWon() {
		if (myAsteroids == null)
			return true;
		return false;
	}

	public void getSpriteGroup(SpriteGroup asteroids){
		myAsteroids = asteroids;
	}
	
}
