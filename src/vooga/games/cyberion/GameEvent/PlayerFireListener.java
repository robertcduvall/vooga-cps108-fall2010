package vooga.games.cyberion.GameEvent;

import vooga.engine.event.IEventListener;

public interface PlayerFireListener extends IEventListener {
	public void fireAction(PlayerFireEvent e);
}
