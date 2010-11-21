package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;;

public interface EnemyFireListener extends IEventHandler {
	public void fireAction(EnemyFireEvent e);
}
