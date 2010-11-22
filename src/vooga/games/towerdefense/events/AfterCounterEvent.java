package vooga.games.towerdefense.events;

import vooga.engine.core.PlayField;
import vooga.games.towerdefense.actors.EnemyGenerator;
import vooga.widget.counter.CounterEvent;
/**
 * 
 * @author Justin Goldsmith
 */
public class AfterCounterEvent extends CounterEvent{
	
	private PlayField myPlayField;
	private EnemyGenerator myEnemyGenerator;

	public AfterCounterEvent(PlayField playField, EnemyGenerator enemyGenerator) {
		myPlayField = playField;
		myEnemyGenerator = enemyGenerator;
	}

	@Override
	public void actionPerformed() {
		myPlayField.add(myEnemyGenerator);
	}

}
