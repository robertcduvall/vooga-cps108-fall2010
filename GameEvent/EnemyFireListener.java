package vooga.games.cyberion.GameEvent;

import vooga.engine.event.IEventListener;

public interface EnemyFireListener extends IEventListener {
	public void fireAction(EnemyFireEvent e);
}
