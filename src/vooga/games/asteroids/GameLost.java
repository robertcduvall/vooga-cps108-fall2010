package vooga.games.asteroids;

import vooga.engine.level.GameLostConditions;
import vooga.engine.overlay.Stat;


public class GameLost extends GameLostConditions{
	
	private Stat<Integer> myStat;
	
	@Override
	public boolean GameLost() {
		if (myStat.getStat() == 0)
			return true;
		return false;
	}
	
	public void addStat(Stat<Integer> stat){
		myStat = stat;
	}
}
