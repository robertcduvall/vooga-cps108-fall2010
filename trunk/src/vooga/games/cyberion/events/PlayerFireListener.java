package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;;

public interface PlayerFireListener extends IEventHandler {
	public void fireAction(PlayerFireEvent e);
}
