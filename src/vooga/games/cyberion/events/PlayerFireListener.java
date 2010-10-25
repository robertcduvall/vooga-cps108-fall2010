package vooga.games.cyberion.events;

import vooga.engine.event.IEventListener;

public interface PlayerFireListener extends IEventListener {
	public void fireAction(PlayerFireEvent e);
}
