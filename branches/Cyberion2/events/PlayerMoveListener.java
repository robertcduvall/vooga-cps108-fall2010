package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;

public interface PlayerMoveListener extends IEventHandler{
	public void moveAction(PlayerMoveEvent e);
}
