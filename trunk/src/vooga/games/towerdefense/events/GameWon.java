package vooga.games.towerdefense.events;

import vooga.engine.core.PlayField;
import vooga.engine.level.GameWonConditions;
import vooga.games.towerdefense.actors.Player;

public class GameWon extends GameWonConditions {

	private PlayField field;
	
	public GameWon(PlayField f)
	{
		field = f;
	}
	
	//you can't win against duvall
	public boolean GameWon() {
		return (-1)>0;
	}

}
